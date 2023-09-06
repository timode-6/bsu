#include <iostream>
#include <fstream>
#include <vector>
#include <future>
#include <set>
using namespace std;
const int n = 50;

int a, b;

vector <int> ans[n];
future<int> fut[n];

set <int> primes_between(int x, int y) {
    set <int> ans, left;
    if (y < x) return set <int>();
    int mid = (x + y) / 2;
    future<set<int>> right = async(&primes_between, mid + 1, y);
    for (int j = x; j <= mid; j++) {
        bool b = false;
        for (int k = 2; k < min((int)sqrt(j) + 1, j); k++) {
            if (j % k == 0) {
                b = true;
                break;
            }
        }
        if (!b) left.insert(j);
    }
    set <int> rightset = right.get();
    merge(left.begin(), left.end(), rightset.begin(), rightset.end(), inserter(ans, ans.begin()));
    return ans;
}

int main()
{
    ofstream fout("output.txt");
    cin >> a >> b;
    auto start = clock();
    future<set<int>> ans = async(&primes_between, a, b);
    set<int> s = ans.get();
    auto finish = clock();
    double time = ((double)(finish - start)) / CLOCKS_PER_SEC;
    std::cout << time;
    for (int j : s) {
        fout << j << '\n';
    }

}