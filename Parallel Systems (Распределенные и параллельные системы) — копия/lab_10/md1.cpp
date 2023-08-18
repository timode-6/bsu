#include <fstream>
#include <string> 
#include <time.h>
#include <process.h>
#include <windows.h>
#include <queue>
#include <mutex>
#include <condition_variable>
#include <map>
#include <iostream>
#include <chrono>

const int n = 1000;
const int p = 5;

struct SThreadParam
{
    int a, b;
    std::string s;
};

std::map<std::string, int> m;
std::string names[2000];
int kol[2000];
int pos[2000][20000];
std::string s;
bool stop = false;

std::queue<std::string> queue;
std::mutex mutex;
std::condition_variable qcheck;


DWORD ThreadFunction(LPVOID pvParam)
{
    while (!stop || !queue.empty()) {
        std::unique_lock<std::mutex> ulock(mutex);
        while (queue.empty()) {
            qcheck.wait(ulock);
        }
        std::string name = queue.front();
        std::ifstream finloc("" + name);
        queue.pop();
        ulock.unlock();
        std::string sloc;
        int j = 0;
        while (getline(finloc, sloc)) {
            ++j;
            auto temp = sloc.find(s);
            if (sloc.find(s) != std::string::npos) {
                pos[m[name]][kol[m[name]]] = j;
                kol[m[name]]++;
            }
        }
    }
    return 0;
}

int main()
{
    HANDLE hThreads[p];
    std::ifstream fin("input.txt");
    std::ofstream fout("output.txt");
    auto start = std::chrono::high_resolution_clock::now();
    getline(fin, s);
    int t = 0;
    for (int i = 0; i < n; i += n / p) {
        hThreads[t] = (HANDLE)_beginthreadex(NULL, 0,
            (_beginthreadex_proc_type)ThreadFunction, NULL, 0, NULL);
        t++;
    }
    for (int i = 0; i < n; i++) {
        std::string str;
        getline(fin, str);
        m[str] = i;
        names[i] = str;
        kol[i] = 0;
        mutex.lock();
        queue.push(str);
        mutex.unlock();
        qcheck.notify_one();
    }
    stop = true;
    qcheck.notify_all();
    WaitForMultipleObjects(p, hThreads, TRUE, INFINITE);
    for (int k = 0; k < p; ++k)
        CloseHandle(hThreads[k]);
    auto finish = std::chrono::high_resolution_clock::now();
    std::chrono::duration<double> diff = finish - start;
    std::cout << diff.count() << " seconds\n";
    std::cout << std::chrono::duration_cast<std::chrono::milliseconds> (diff).count() << " milliseconds\n";
    for (int i = 0; i < n; i++) {
        fout << names[i] << " ";
        for (int j = 0; j < kol[i]; j++) {
            fout << pos[i][j] << " ";
        }
        fout << "\n";
    }
}