#include <gtest.h>
#include "lab1.cpp"

using namespace std;

TEST(CheckRandom, SimpleTest) {
  for (int n = 1; n < 100; n += 10) {
    int* A = new int[n * n];
    FillMatrixWithRand(A, n);
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        EXPECT_TRUE(A[i * n + j] >= -100 && A[i * n + j] <= 100);
      }
    }
    delete[] A;
  }
}

bool CheckIfEqual(int* A, int* B, int N) {
  for (int i = 0; i < N; ++i) {
    for (int j = 0; j < N; ++j) {
      if (A[i * N + j] != B[i * N + j]) {
        return false;
      }
    }
  }
  return true;
}

TEST(CheckMultiplication, SimpleTests) {
  for (int n = 10; n <= 100; n += 10) {
    int* A = new int[n * n];
    int* B = new int[n * n];

    FillMatrixWithRand(A, n);
	  FillMatrixWithRand(B, n);

    int* C1 = new int[n * n];
    fill(C1, C1 + n * n, 0);
    PointMultiple(A, B, C1, n);

    int* C2 = new int[n * n];
    fill(C2, C2 + n * n, 0);

    PointMultipleParallel(A, B, C2, n);
    EXPECT_TRUE(CheckIfEqual(C1, C2, n));

    vector<int> rs = {1, 2, 5};
    for (auto r : rs) {
      fill(C2, C2 + n * n, 0);
      BlockMultiple(A, B, C2, n, r);
      EXPECT_TRUE(CheckIfEqual(C1, C2, n));

      fill(C2, C2 + n * n, 0);
      BlockMultipleParallel(A, B, C2, n, r);
      EXPECT_TRUE(CheckIfEqual(C1, C2, n));
    }

    delete[] A;
    delete[] B;
    delete[] C1;
    delete[] C2;
  }
}
