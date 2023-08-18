#include <windows.h>
#include <process.h>
#include <ctime>
#include <iostream>
#include <stdio.h>
#define p 2 // amount of child threads
double pi[p];
int n = 1000000;
// kinda function
double f(double x) {
	return 4 / (1 + x * x);
}
// parameter type to be handed over to thread function 	
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
	h = (b-a)/ n;
	sum = 0.;
	param->f = f;
	start = param->k;
	for (i = start; i < n; i += p)
	{
		x = h * i;
		sum += param->f(x);
	}
	// you should not address to global variable frequently
	param->sum = h * sum;
	return 0;
}

void second(double a, double b) {
	clock_t start, end;
	HANDLE hThreads[p]; // array of thread descriptors
	// array of stream functions parameters
	SThreadParam params[p];
	int k;
	double sum;
	// creation child threads
	for (k = 0; k < p; ++k)
	{
		params[k].k = k;
		params[k].a = a;
		params[k].b = b;
		hThreads[k] = (HANDLE)_beginthreadex(NULL, 0,(_beginthreadex_proc_type)ThreadFunction2, (LPVOID) & (params[k]), 0, NULL);
		if (hThreads[k] == NULL) // handling error
		{
			printf("Create Thread %d Error=%d\n", k, GetLastError());
			break;
		}
	}
	// waiting for child threads to complete
	start = clock();
	WaitForMultipleObjects(p, hThreads, TRUE, INFINITE);
	for (k = 0; k < p; ++k)
		CloseHandle(hThreads[k]);
	sum = 0.;
	for (k = 0; k < p; ++k)
		sum += params[k].sum;
	end = clock();
	printf("PI = %.16f\n", sum);
	printf("time = %f\n", (end - start)/(double)CLOCKS_PER_SEC);
}

/////////////////////////////////////////////////////////////////


int main()
{
	second(0, 1);
	printf("\n");

	return 0;
}