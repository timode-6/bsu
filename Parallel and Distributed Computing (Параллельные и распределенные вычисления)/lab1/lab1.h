#pragma once
#include <omp.h>
#include <iostream>
#include <random>
#include <vector>
#include <chrono>
#include <fstream>
#define MIN -100
#define MAX 100

std::vector<int> N{ 500,1500,2000 };
std::vector<int> R{ 1,2,5,10,15,20,30,50,100,200,500 };
// std::vector<int> R{ 50,55,60,65,70,75,80,85,90,95,100 };
const int THREADS(4);
std::random_device rd;
std::mt19937 gen(rd());
std::uniform_int_distribution<> distr(MIN, MAX);
std::ofstream out("../output_inner_dopar.txt");

void FillMatrixWithRand(int* A, int N);
void PrintMatrix(int* A, int N);
void PointMultiple(int* A, int* B, int* C, int N);
void BlockMultiple(int* A, int* B, int* C, int N, int r);
void PointMultipleParallel(int* A, int* B, int* C, int N);
void BlockMultipleParallel(int* A, int* B, int* C, int N, int r);