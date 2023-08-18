// Consistent.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

int a, b;

vector <int> ans;

int main()
{
    ofstream fout("output.txt");
    cin >> a >> b;
    auto start = clock();
    for (int i = a; i < b; i++) {
        bool b = false;
        for (int j = 2; j < min((int)sqrt(i) + 1, i); j++) {
            if (i % j == 0) {
                b = true;
                break;
            }
        }
        if (!b) ans.push_back(i);
    }
    auto finish = clock();
    double time = ((double)(finish - start)) / CLOCKS_PER_SEC;
    std::cout << time;
    for (auto i : ans) {
        fout << i << ' ';
    }
}