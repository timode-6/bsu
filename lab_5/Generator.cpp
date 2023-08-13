

#include <fstream>
#include <string> 

const int n = 1000;

int main()
{
    for (int i = 0; i < n; i++) {
        std::ofstream fout("input"+std::to_string(i)+".txt");
        int beg = rand();
        int end = rand();
        for (int j = 0; j < beg; j++) {
            fout << "aaa\n";
        }
        fout << "ooo\n";
        for (int j = 0; j < end; j++) {
            fout << "aaa\n";
        }
    }
    std::ofstream fout("input.txt");
    fout << "ooo\n";
    for (int i = 0; i < n; i++) {
        fout << "input" + std::to_string(i) + ".txt\n";
    }
}

