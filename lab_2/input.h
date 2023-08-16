#pragma once

#include <cmath>

std::vector<int> ns = {10'000'000, 20'000'000, 30'000'000, 40'000'000};

double F1(double x) {
    return 4 / (1 + x * x);
}

double F2(double x) {
    return std::sin(x);
}

double F3(double x) {
    return std::exp(x);
}

std::vector<double (*)(double)> functions = {F1, F2, F3};

struct Input {
    int func_ind;
    double a;
    double b;
};
