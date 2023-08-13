#include <windows.h>
#include <process.h>
#include <ctime>
#include <iostream>
#include <vector>
#include <thread>
#include <stdio.h>
#define p 4// количество дочерних потоков
double pi[p];
int n = 1000000000;
DWORD WINAPI ThreadFunction1(LPVOID pvParam)
{
	int nParam = (int)pvParam;
	int i, start;
	double h, sum, x;
	h = 1. / n;
	sum = 0.;
	start = nParam;
	for (i = start; i < n; i += p)
	{
		x = h * i;
		sum += 4. / (1. + x * x);
	}
	pi[nParam] = h * sum;
	return 0;
}

void first() {
	clock_t start, end;
	HANDLE hThreads[p];
	int k;
	double sum;
	start = clock();
	// создание дочерних потоков
	for (k = 0; k < p; ++k)
	{
		hThreads[k] = (HANDLE)_beginthreadex(NULL, 0, (_beginthreadex_proc_type)ThreadFunction1, (LPVOID)k, 0, NULL);
		if (hThreads[k] == NULL) // обработка ошибки
		{
			printf("Create Thread %d Error=%d\n", k, GetLastError());
			break;
		}
	}
	// ожидание завершения дочерних потоков
	// ожидание завершения дочерних потоков

	WaitForMultipleObjects(p, hThreads, TRUE, INFINITE);
	for (k = 0; k < p; ++k)
		CloseHandle(hThreads[k]);
	sum = 0.;
	for (k = 0; k < p; ++k)
		sum += pi[k];
	end = clock();
	printf("PI = %.16f\n", sum);
	//printf("time = %f\n", (end - start) / (double)CLOCKS_PER_SEC);
}


/////////////////////////////////////////////////////////////////////

double f(double x) {
	return 4 * (1 / (x * x + 1));
}
// тип параметра, передаваемого функции потока
struct SThreadParam
{
	int k;
	double sum;
	double a;
	double b;
	double(*f)(double x);
};

DWORD WINAPI ThreadFunction2(LPVOID pvParam)
{
	SThreadParam* param = (SThreadParam*)pvParam;
	int i, start;
	double h, sum, x;
	double a = param->a, b = param->b;
	h = (b - a) / n;
	sum = 0.;
	param->f = f;
	start = param->k;
	for (i = start; i < n; i += p)
	{
		x = h * i;
		sum += param->f(x);
	}
	// к глобальной переменной не следует часто обращаться
	param->sum = h * sum;
	return 0;
}

void second(double a, double b) {
	clock_t start, end;
	HANDLE hThreads[p]; // массив дескрипторов потоков
	// массив параметров потоковых функций
	SThreadParam params[p];
	int k;
	double sum;
	// создание дочерних потоков
	for (k = 0; k < p; ++k)
	{
		params[k].k = k;
		params[k].a = a;
		params[k].b = b;
		hThreads[k] = (HANDLE)_beginthreadex(NULL, 0, (_beginthreadex_proc_type)ThreadFunction2, (LPVOID) & (params[k]), 0, NULL);
		if (hThreads[k] == NULL) // обработка ошибки
		{
			printf("Create Thread %d Error=%d\n", k, GetLastError());
			break;
		}
	}
	// ожидание завершения дочерних потоков
	start = clock();
	WaitForMultipleObjects(p, hThreads, TRUE, INFINITE);
	for (k = 0; k < p; ++k)
		CloseHandle(hThreads[k]);
	sum = 0.;
	for (k = 0; k < p; ++k)
		sum += params[k].sum;
	end = clock();
	printf("PI = %.16f\n", sum);
	//printf("time = %f\n", (end - start) / (double)CLOCKS_PER_SEC);
}

/////////////////////////////////////////////////////////////////


void WilliamsFunction(int pvParam)
{
	int nParam = (int)pvParam;
	int i, start;
	double h, sum, x;
	h = 1. / n;
	sum = 0.;
	start = nParam;
	for (i = start; i < n; i += p)
	{
		x = h * i;
		sum += 4. / (1. + x * x);
	}
	pi[nParam] = h * sum;
}

void williams() {
	std::vector<std::thread> threads(p);
	int k;
	// создание дочерних потоков
	for (k = 0; k < p; ++k)
		threads[k] = std::thread(WilliamsFunction, k);
	// ожидание завершения дочерних потоков
	for (k = 0; k < p; ++k)
		threads[k].join();
	double sum = 0.;
	for (k = 0; k < p; ++k)sum += pi[k];
	printf("PI = %.16f\n", sum);

}

int main()
{
	auto start = std::chrono::high_resolution_clock::now();
	first();
	auto stop = std::chrono::high_resolution_clock::now();
	std::chrono::duration<double> diff = stop - start;
	std::cout << diff.count() << " seconds\n";
	std::cout << std::chrono::duration_cast<std::chrono::milliseconds>
		(diff).count() << " milliseconds\n";
	printf("\n");

	////////////////////////////////
	start = std::chrono::high_resolution_clock::now();
	second(0, 1);
	stop = std::chrono::high_resolution_clock::now();
	diff = stop - start;
	std::cout << diff.count() << " seconds\n";
	std::cout << std::chrono::duration_cast<std::chrono::milliseconds>
		(diff).count() << " milliseconds\n";
	printf("\n");

	////////////////////////////////////
	start = std::chrono::high_resolution_clock::now();
	williams();
	stop = std::chrono::high_resolution_clock::now();
	diff = stop - start;
	std::cout << diff.count() << " seconds\n";
	std::cout << std::chrono::duration_cast<std::chrono::milliseconds>
		(diff).count() << " milliseconds\n";

	return 0;
}