#include <iostream>
#include <fstream>
#include <vector>
#include <future>
using namespace std;
const int n = 5;

int a, b;

vector <int> ans[n];
future<void> fut[n];
thread t[n];

void primes_between(int x, int y, int i) {
    for (int j = max(x, 2); j < y; j++) {
        bool b = false;
        for (int k = 2; k < min((int)sqrt(j) + 1, j); k++) {
            if (j % k == 0) {
                b = true;
                break;
            }
        }
        if (!b) ans[i].push_back(j);
    }
}

int main()
{
    ofstream fout("output.txt");
    using task_type = void(int, int, int);
    cin >> a >> b;
    auto start = clock();
    for (int i = 0; i < n; i++) {
        int p = (b - a) / n;
        packaged_task<task_type> pt{ primes_between };
        fut[i] = pt.get_future();
        t[i] = thread(move(pt), a + p * i, (i == n - 1) ? b : a + p * (i + 1), i);
    }
    for (int i = 0; i < n; i++) {
        fut[i].get();
    }
    auto finish = clock();
    double time = ((double)(finish - start)) / CLOCKS_PER_SEC;
    std::cout << time;
    for (int i = 0; i < n; i++) {
        for (int j : ans[i]) {
            fout << j << '\n';
        }
        t[i].join();
    }

}