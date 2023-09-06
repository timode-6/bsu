#include <ctime>
#include <fstream>
#include <iostream>
#include <random>

#define SYMBOLS_PER_FILE 50000000

int main() {
    std::mt19937 engine(time(nullptr));
    std::uniform_int_distribution<int> distr(97, 122);
    std::ofstream fout = std::ofstream("input.txt");
    std::string buf;
    fout << char(distr(engine));
    for (int i = 1; i < SYMBOLS_PER_FILE; ++i) {
        if (i % 10 == 0) {
            if (i % 100 == 0) {
                buf += '\n';
                fout << buf;
                buf.clear();
            }
            else {
                buf += ' ';
            }
        }
        buf += char(distr(engine));
    }
    if (!buf.empty()) {
        fout << buf;
    }
    fout.close();
    return 0;
}