#include <iostream>
#include <fstream>
#include <vector>
#include <future>
using namespace std;
const int n = 5;

int a, b;

vector <int> ans[n];
future<int> fut[n];

int main()
{
    ofstream fout("output.txt");
    cin >> a >> b;
    auto start = clock();
    for (int i = 0; i < n; i++) {
        int p = (b - a) / 5;
        fut[i] = async(launch::async, [p, i] {
                for (int j = max(a + p * i, 2); j < ((i == n - 1) ? b : a + p * (i + 1)); j++) {
                    bool b = false;
                    for (int k = 2; k < min((int)sqrt(j) + 1, j); k++) {
                        if (j % k == 0) {
                            b = true;
                            break;
                        }
                    }
                    if (!b) ans[i].push_back(j);
                }
                return 0;
            });
    }
    for (int i = 0; i < n; i++) {
        fut[i].get();
    }
    auto finish = clock();
    double time = ((double)(finish - start)) / CLOCKS_PER_SEC;
    std::cout << time;
    for (int i = 0; i < n; i++) {
        for (auto j : ans[i]) {
            fout << j << ' ';
        }
    }
    
}