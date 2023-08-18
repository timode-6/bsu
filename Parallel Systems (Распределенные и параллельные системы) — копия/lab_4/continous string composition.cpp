#include <windows.h>
#include <process.h>
#include <ctime>
#include <iostream>
#include <vector>
#include <thread>
#include <stdio.h>
#include <iomanip>
#include <fstream>

int** initRandMatrix(int n, int m) {

	srand(time(0));
	int** a = new int* [m];

	for (int i = 0; i < m; i++) {
		a[i] = new int[n];
	}

	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			a[i][j] = rand();
		}
	}

	return a;
}

int* initRandVector(int n) {
	int* a = new int[n];
	srand(time(0));

	for (int i = 0; i < n; i++) {
		a[i] = rand();
	}

	return a;
}


int n(1000), m(1000);
const int p = 3;//количество дочерних потоков

int** a = initRandMatrix(n, m);
int* b = initRandVector(n);

struct SThreadParam {
	int start, finish;
};

int** readMatrixFromFile(std::istream& in, int n, int m) {

	int** a = new int* [m];

	for (int i = 0; i < m; i++) {
		a[i] = new int[n];
	}

	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			in >> a[i][j];
		}
	}

	return a;
}

int* readVectorFromFile(std::ifstream& in, int n) {
	int* a = new int[n];

	for (int i = 0; i < n; i++) {
		in >> a[i];
	}

	return a;
}

DWORD multipleMatrixOnVector(LPVOID pvParam) {

	SThreadParam* param = (SThreadParam*)pvParam;
	int i, start = param->start, finish = param->finish;

	for (i = start; i <= finish; i+=p) {
		for (int j = 0; j < m; j++) {
			a[i][j] = a[i][j] * b[j];
		}
	}

}

void showMatrix(int** a, int n, int m) {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			std::cout << std::setw(10) << a[i][j];
		}
		std::cout << std::endl;
	}
}




int main() {
	
	auto start = std::chrono::high_resolution_clock::now();
	HANDLE hTreads[p];
	SThreadParam params[p];
	int k;

	// создание дочерних потоков
	for (k = 0; k < p; ++k) {
		params[k].start = k * n /p;
		params[k].finish = (k + 1) * n / p - 1;
		if (k == p - 1) {
			params[k].finish = n - 1;
		}
		hTreads[k] = (HANDLE)_beginthreadex(NULL, 0, (_beginthreadex_proc_type)multipleMatrixOnVector, (LPVOID) & (params[k]), 0, NULL);
		if (hTreads[k] == NULL) {
			return -1;

		}
	}
	// ожидание завершения дочерних потоков
	WaitForMultipleObjects(p, hTreads,TRUE, INFINITE);
	for (k = 0; k < p; ++k) {
		CloseHandle(hTreads[k]);
	}
	auto stop = std::chrono::high_resolution_clock::now();
	std::chrono::duration<double> diff = stop - start;
	std::cout << diff.count() << " seconds\n";
	std::cout << std::chrono::duration_cast<std::chrono::milliseconds>
		(diff).count() << " milliseconds\n";
	printf("\n");

}