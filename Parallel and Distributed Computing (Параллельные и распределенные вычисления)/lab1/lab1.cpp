#include "lab1.h"
#include "debugging.h"

void FillMatrixWithRand(int* A, int N) {
  for (int i = 0; i < N; ++i)
    for (int j = 0; j < N; ++j)
      A[i * N + j] = distr(gen);
}

void PrintMatrix(int* A, int N) {
  for (int i = 0; i < N; ++i) {
    for (int j = 0; j < N; ++j) {
      printf("%-15d", A[i * N + j]);
    }
    printf("\n\n");
  }
  printf("\n\n");
}

void PointMultiple(int* A, int* B, int* C, int N) {
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; ++j) {
      for (int k = 0; k < N; ++k) {
        C[i * N + j] = C[i * N + j] + A[i * N + k] * B[k * N + j];
      }
    }
  }
}

void PointMultipleParallel(int* A, int* B, int* C, int N) {
  for (int i = 0; i < N; ++i) {
    #pragma omp parallel for
    for (int j = 0; j < N; ++j) {
      for (int k = 0; k < N; ++k) {
        C[i * N + j] = C[i * N + j] + A[i * N + k] * B[k * N + j];
      }
    }
  }
}

void BlockMultiple(int* A, int* B, int* C, int N, int r) {
  int Q = N / r;
  for (int i1 = 0; i1 < Q; ++i1) {
    for (int j1 = 0; j1 < Q; ++j1) {
      for (int k1 = 0; k1 < Q; ++k1) {
        for (int i2 = 0; i2 < r; ++i2) {
          for (int j2 = 0; j2 < r; ++j2) {
            for (int k2 = 0; k2 < r; ++k2) {
              int i = i1 * r + i2;
              int j = j1 * r + j2;
              int k = k1 * r + k2;
              C[i * N + j] = C[i * N + j] + A[i  * N + k] * B[k * N + j];
            }
          }
        }
      }
    }
  }
}

void BlockMultipleParallel(int* A, int* B, int* C, int N, int r) {
  int Q = N / r;
  for (int i1 = 0; i1 < Q; ++i1) {
    #pragma omp parallel for
    for (int j1 = 0; j1 < Q; ++j1) {
      for (int k1 = 0; k1 < Q; k1++) {
        for (int i2 = 0; i2 < r; i2++) {
          for (int j2 = 0; j2 < r; j2++) {
            for (int k2 = 0; k2 < r; ++k2) {
              int i = i1 * r + i2;
              int j = j1 * r + j2;
              int k = k1 * r + k2;
              C[i * N + j] = C[i * N + j] + A[i * N + k] * B[k * N + j];
            }
          }
        }
      }
    }
  }
}

void PrintToFile(const std::vector<std::vector<double>>& times, const std::vector<int>& rs, int N) {
  out << "N = " << N << "\n";
  out << "rs = " << rs << "\n";
  out << "point_multiple = " << times[0] << "\n";
  out << "point_multiple_parallel = " << times[1] << "\n";
  out << "block_multiple = " << times[2] << "\n";
  out << "block_multiple_parallel = " << times[3] << "\n\n";
}

// int main() {
//   omp_set_dynamic(0);
//   omp_set_num_threads(THREADS);
//   for (auto& n : N) {
//     std::vector<std::vector<double>> temp(4);
// 	std::vector<int> rs_for_n;
//     for (auto& r : R) {
// 	  if (n % r != 0) {
// 		continue;
// 	  }
// 	  rs_for_n.push_back(r);

//       int* B = new int[n * n];
//       int* A = new int[n * n];
//       int* C = new int[n * n];
//       FillMatrixWithRand(A, n);
//       FillMatrixWithRand(B, n);

//       auto start = std::chrono::high_resolution_clock::now();
//       PointMultiple(A, B, C, n);
//       auto end = std::chrono::high_resolution_clock::now();
//       std::chrono::duration<double> duration = end - start;
//       //PrintMatrix(C,n);
//       printf("\nTime of point multiplication with N = %d & r = %d: %3.10f", n, r, duration.count());
//       temp[0].push_back(duration.count());
//       delete[] C;
//       C = new int[n * n]{};

// 	  start = std::chrono::high_resolution_clock::now();
//       PointMultipleParallel(A, B, C, n);
//       end = std::chrono::high_resolution_clock::now();
//       std::chrono::duration<double> duration1 = end - start;
//       //PrintMatrix(C, n);
//       printf("\nTime of parallel multiplication with N = %d & r = %d & num of threads - %d: %3.10f", n, r, THREADS, duration1.count());
//       printf("\nSpeedUp: %3.10f\n\n", (duration - duration1).count());
//       temp[1].push_back(duration1.count());
//       delete[] C;
//       C = new int[n * n]{};

//       start = std::chrono::high_resolution_clock::now();
//       BlockMultiple(A, B, C, n, r);
//       end = std::chrono::high_resolution_clock::now();
//       std::chrono::duration<double> duration2 = end - start;
//       //PrintMatrix(C, n);
//       printf("\nTime of point multiplication with N = %d & r = %d: %3.10f",n,r, duration2.count());
//       temp[2].push_back(duration2.count());
//       delete[] C;
//       C = new int[n * n]{};

//       start = std::chrono::high_resolution_clock::now();
//       BlockMultipleParallel(A, B, C, n, r);
//       end = std::chrono::high_resolution_clock::now();
//       std::chrono::duration<double> duration3 = end - start;
//       //PrintMatrix(C, n);
//       printf("\nTime of parallel multiplicationwith N = %d & r = %d & num of threads - %d: %3.10f", n, r, THREADS, duration3.count());
//       temp[3].push_back(duration3.count());
//       printf("\nSpeedUp: %3.10f\n\n", (duration2 - duration3).count());
//       delete[] A;
//       delete[] B;
//       delete[] C;
//     }
//   	PrintToFile(temp, rs_for_n, n);
//   }
//   out.close();
// }