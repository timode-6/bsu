#include <algorithm>
#include <fstream>
#include <iostream>
#include <sstream>
#include <thread>
#include "Header.h"

threadsafe_queue<std::string> queue;

void producer() {
    std::ifstream fin("input.txt");
    while (!fin.eof()) {
        std::string line;
        std::getline(fin, line);
        queue.push(line);
    }
    queue.push("");
    fin.close();
}

void consumer() {
    std::ofstream fout("output.txt");
    bool is_first_line = true;
    while (true) {
        std::string line = *queue.wait_and_pop();
        if (line.empty()) {
            break;
        }
        if (!is_first_line) {
            fout << "\n";
        }
        std::stringstream ss(line);
        std::string word;
        bool is_first_word = true;
        while (ss >> word) {
            if (!is_first_word) {
                fout << " ";
            }
            std::reverse(word.begin(), word.end());
            fout << word;
            is_first_word = false;
        }
        is_first_line = false;
    }
    fout.close();
}

int main() {
    auto start = std::chrono::high_resolution_clock::now();
    std::thread producer_thread(producer);
    std::thread consumer_thread(consumer);
    producer_thread.join();
    consumer_thread.join();
    std::chrono::duration<double> diff =
        std::chrono::high_resolution_clock::now() - start;
    std::cout << diff.count() << " seconds\n";
    return 0;
}