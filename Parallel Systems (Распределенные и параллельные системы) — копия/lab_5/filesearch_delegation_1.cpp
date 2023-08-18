// filesearch_delegation_1.cpp : This file contains the 'main' function. Program execution begins and ends there.
//


#include <fstream>
#include <string> 
#include <time.h>
#include <process.h>
#include <windows.h>

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
        std::ifstream finloc("../Generator/" +str[i]);
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
    std::ifstream fin("../Generator/input.txt");
    std::ofstream fout("output.txt");
    std::string s, name;
    HANDLE hThreads[p];
    SThreadParam params[p];
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
    for (int i = 0; i < n; i++) {
        fout << str[i] << " ";
        for (int j = 0; j < kol[i]; j++) {
            fout << pos[i][j] << " ";
        }
        fout << "\n";
    }
}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
