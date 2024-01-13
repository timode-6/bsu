#include <iostream>
#include <fstream>
#include <random>
#include <iomanip>
#include <cassert>
#include <sys/time.h>

#include "mpi.h"

int* MultiplySimple(int* a, int* b, int N, int M, int K) {
  int* c = new int[N * M];
  for (int i = 0; i < N; ++i) {
    for (int j = 0; j < M; ++j) {
      for (int k = 0; k < K; ++k) {
        c[i * N + j] += a[i * N + k] * b[k * M + j];
      }
    }
  }
  return c;
}

int* GenerateMatrix(int size) {
  int* a = new int[size];
  std::random_device rd;
  static std::mt19937 gen(rd());
  static std::uniform_int_distribution<> distr(-100, 100);
  for (int i = 0; i < size; ++i) {
    a[i] = distr(gen);
  }
  return a;
}

int main(int argc, char** argv) {
  MPI_Init(&argc, &argv);

  int myrank;
  int ranksize;
  MPI_Comm_rank(MPI_COMM_WORLD, &myrank);
  MPI_Comm_size(MPI_COMM_WORLD, &ranksize);

  int N = atoi(argv[1]);
  int K = N;
  int M = N;

  int r1 = atoi(argv[2]);
  int Q1 = (N + r1 - 1) / r1;

  int Q2 = atoi(argv[3]);
  int r2 = (M + Q2 - 1) / Q2;

  assert(ranksize == Q2);

  int* Ap = new int[r1 * 1]{};
  int* Bp = new int[K * r2]{};
  int* Cp = new int[N * r2]{};

  int* A = GenerateMatrix(N * K);
  int* B = GenerateMatrix(K * M);
  int* C = new int[N * M]{};

  struct timeval tv_start{}, tv_finish{};

  if (myrank == 0) {
    gettimeofday(&tv_start, nullptr);

    for (int i = 0; i < K; i++) {
      for (int j = 0; j < r2; j++) {
        Bp[i * r2 + j] = B[i * M + j];
      }
    }

    for (int send_rank = 1; send_rank < Q2; ++send_rank) {
      int* Bp_temp = new int[K * r2]{};
      for (int i = 0; i < K; ++i) {
        for (int j = send_rank * r2; j < std::min((send_rank + 1) * r2, M); ++j) {
          Bp_temp[i * r2 + j - send_rank * r2] = B[i * M + j];
        }
      }
      MPI_Send(Bp_temp, K * r2, MPI_INTEGER,
               send_rank, 0, MPI_COMM_WORLD);
    }
  } else {
    MPI_Recv(Bp, K * r2, MPI_INTEGER, 0,
             0, MPI_COMM_WORLD, nullptr);
  }

  for (int i_gl = 0; i_gl < Q1; ++i_gl) {
    for (int k_gl = 0; k_gl < K; ++k_gl) {
      if (myrank == 0) {
        for (int i = i_gl * r1; i < std::min((i_gl + 1) * r1, N); ++i) {
          int ip = i - i_gl * r1;
          Ap[ip] = A[i * K + k_gl];
        }
      } else {
        MPI_Recv(Ap, r1 * 1, MPI_INTEGER, myrank - 1, 0, MPI_COMM_WORLD, nullptr);
      }

      for (int i = i_gl * r1; i < std::min((i_gl + 1) * r1, N); ++i) {
        int ip = i - i_gl * r1;
        for (int j = myrank * r2; j < std::min((myrank + 1) * r2, M); ++j) {
          int jp = j - myrank * r2;
          Cp[i * r2 + jp] += Ap[ip] * Bp[k_gl * r2 + jp];
        }
      }
      if (myrank < Q2 - 1) {
        MPI_Send(Ap, r1 * 1, MPI_INTEGER, myrank + 1, 0, MPI_COMM_WORLD);
      }
    }
  }

  if (myrank > 0) {
    MPI_Send(Cp, N * r2, MPI_INTEGER,
             0, 0, MPI_COMM_WORLD);
  }

  if (myrank == 0) {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < r2; j++) {
        C[i * M + j] = Cp[i * r2 + j];
      }
    }
  
    for (int recv_rank = 1; recv_rank < Q2; ++recv_rank) {
      int* Cp_temp = new int[N * r2]{};
      MPI_Recv(Cp_temp, N * r2, MPI_INTEGER, recv_rank, 0, MPI_COMM_WORLD, nullptr);
      for (int i = 0; i < N; ++i) {
        for (int j = recv_rank * r2; j < std::min((recv_rank + 1) * r2, M); ++j) {
          int jp = j - recv_rank * r2;
          C[i * M + j] = Cp_temp[i * r2 + jp];
        }
      }
    }
    gettimeofday(&tv_finish, nullptr);
    double elapsed_time_ms = (tv_finish.tv_sec - tv_start.tv_sec) * 1000 + (double)(tv_finish.tv_usec - tv_start.tv_usec) / 1000;

    int* C_correct = MultiplySimple(A, B, N, M, K);
    for (int i = 0; i < N; ++i) {
      for (int j = 0; j < M; ++j) {
        assert(C[i * M + j] == C_correct[i * M + j]);
      }
    }

    std::cout << std::fixed << N << ' ' << r1 << ' ' << Q2 << ' ' << std::setprecision(4) << elapsed_time_ms << std::endl;
    std::ofstream fout("logs.txt", std::ios_base::app);
    fout << std::fixed << N << ' ' << r1 << ' ' << Q2 << ' ' << std::setprecision(4) << elapsed_time_ms << std::endl;
    fout.close();
  }

  MPI_Finalize();
  return 0;
}
