#include <fstream>
#include <string> 
#include <time.h>
#include <process.h>
#include <windows.h>
#include <iostream>
#include <chrono>

const int n = 1000;
const int p = 5;

struct SThreadParam
{
    int a, b;
    std::string s;
};

std::string str[2000];
int kol[2000];
int pos[2000][20000];
std::string s;

DWORD ThreadFunction(LPVOID pvParam)
{
    SThreadParam* param = (SThreadParam*)pvParam;
    int a = param->a;
    int b = param->b;
    std::string s = param->s;
    for (int i = a; i < b; i++) {
        std::ifstream finloc(str[i]);
        std::string sloc;
        int j = 0;
        while (getline(finloc, sloc)) {
            ++j;
            auto temp = sloc.find(s);
            if (sloc.find(s) != std::string::npos) {
                pos[i][kol[i]] = j;
                kol[i]++;
            }
        }
    }
    return 0;
}

int main()
{
    std::ifstream fin("input.txt");
    std::ofstream fout("output.txt");
    HANDLE hThreads[p];
    SThreadParam params[p];
    auto start = std::chrono::high_resolution_clock::now();
    fin >> s;
    for (int i = 0; i < n; i++) {
        getline(fin, str[i]);
        kol[i] = 0;
    }
    int t = 0;
    for (int i = 0; i < n; i += n / p) {
        params[t].a = i;
        params[t].b = i + n / p;
        params[t].s = s;
        hThreads[t] = (HANDLE)_beginthreadex(NULL, 0,
            (_beginthreadex_proc_type)ThreadFunction, (LPVOID) & (params[t]), 0, NULL);
        t++;
    }
    WaitForMultipleObjects(p, hThreads, TRUE, INFINITE);
    for (int k = 0; k < p; ++k)
        CloseHandle(hThreads[k]);
    auto finish = std::chrono::high_resolution_clock::now();
    std::chrono::duration<double> diff = finish - start;
    std::cout << diff.count() << " seconds\n";
    std::cout << std::chrono::duration_cast<std::chrono::milliseconds> (diff).count() << " milliseconds\n";

    for (int i = 0; i < n; i++) {
        fout << str[i] << " ";
        for (int j = 0; j < kol[i]; j++) {
            fout << pos[i][j] << " ";
        }
        fout << "\n";
    }
}