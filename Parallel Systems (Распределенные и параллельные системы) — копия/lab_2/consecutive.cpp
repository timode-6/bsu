#include <iostream>
#include <fstream>
#include <vector>

#include "input.h"

int main() {
    std::ofstream out("../output.txt");

    for (auto n : ns) {
        clock_t start = clock();
        std::ifstream in("../input.txt");
        int func_ind = 0;
        double a = 0;
        double b = 0;
        std::vector<Input> input;
        while (in >> func_ind >> a >> b) {
            input.push_back({func_ind, a, b});
        }
        for (auto line : input) {
            func_ind = line.func_ind;
            a = line.a;
            b = line.b;
            double h = (b - a) / n;
            double result = 0;
            for (int i = 0; i < n; ++i) {
                double x = a + i * h;
                result += h * functions[func_ind](x);
            }
            out << "n: " << n << " a: " << a << " b: " << b << " func_ind: " << func_ind
                << " integral: " << result << "\n";
        }
        clock_t end = clock();
        out << "time: " << (end - start) / static_cast<double>(CLOCKS_PER_SEC) << "\n";
    }
    return 0;
}
