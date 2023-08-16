#include <stdlib.h>
#include <stdio.h>
#include <iostream>

#include <unistd.h>
#include <pthread.h>
#include <vector>
#include <fstream>

#include "input.h"

#define STORAGE_MAX 5

struct ConsumerData {
    std::ofstream* out;
    pthread_mutex_t* mutex;
    pthread_cond_t* condition;
    std::vector<Input>* input;
    int n;
    bool* eof;
};

void* Consumer(void* args) {
    auto* consumer_data = reinterpret_cast<ConsumerData*>(args);
    auto* out = consumer_data->out;
    auto* mutex = consumer_data->mutex;
    auto* condition = consumer_data->condition;
    auto* input = consumer_data->input;
    int n = consumer_data->n;
    auto* eof = consumer_data->eof;
    int func_ind = 0;
    double a = 0;
    double b = 0;
    bool exit_from_cycle = false;

    while (!exit_from_cycle) {
        pthread_mutex_lock(mutex);
        while (input->size() < STORAGE_MAX && !*eof) {
            pthread_cond_wait(condition, mutex);
        }
        if (*eof) {
            exit_from_cycle = true;
        }
        std::vector<Input> cur_input = *input;
        input->clear();
        pthread_mutex_unlock(mutex);
        for (auto line : cur_input) {
            func_ind = line.func_ind;
            a = line.a;
            b = line.b;

            double h = (b - a) / n;
            double result = 0;
            for (int i = 0; i < n; ++i) {
                double x = a + i * h;
                result += h * functions[func_ind](x);
            }
            *out << "n: " << n << " a: " << a << " b: " << b << " func_ind: " << func_ind
                 << " integral: " << result << "\n";
        }
    }
    return nullptr;
}

struct ProducerData {
    std::ifstream* in;
    pthread_mutex_t* mutex;
    pthread_cond_t* condition;
    std::vector<Input>* input;
    bool* eof;
};

void* Producer(void* args) {
    auto* producer_data = reinterpret_cast<ProducerData*>(args);
    auto* in = producer_data->in;
    auto* mutex = producer_data->mutex;
    auto* condition = producer_data->condition;
    auto* input = producer_data->input;
    auto* eof = producer_data->eof;
    int func_ind = 0;
    double a = 0;
    double b = 0;
    while (!*eof) {
        *in >> func_ind >> a >> b;
        pthread_mutex_lock(mutex);
        if (in->eof()) {
            *eof = true;
        } else {
            input->push_back({func_ind, a, b});
        }
        if (input->size() >= STORAGE_MAX || *eof) {
            pthread_cond_signal(condition);
        }
        pthread_mutex_unlock(mutex);
    }
    return nullptr;
}

int main(int argc, char* argv[]) {
    std::ofstream out("../threads_output.txt");

    for (auto n : ns) {
        clock_t start = clock();
        std::vector<Input> input;
        std::ifstream in("../input.txt");

        pthread_mutex_t mutex;
        pthread_cond_t condition;

        int res = 0;
        pthread_t producer_thread;
        pthread_t consumer_thread;

        pthread_mutex_init(&mutex, nullptr);
        pthread_cond_init(&condition, nullptr);

        bool eof = false;
        ProducerData producer_data = {&in, &mutex, &condition, &input, &eof};
        res = pthread_create(&producer_thread, nullptr, Producer, &producer_data);
        if (res != 0) {
            perror("pthread_create");
            exit(EXIT_FAILURE);
        }

        ConsumerData consumer_data = {&out, &mutex, &condition, &input, n, &eof};
        res = pthread_create(&consumer_thread, nullptr, Consumer, &consumer_data);
        if (res != 0) {
            perror("pthread_create");
            exit(EXIT_FAILURE);
        }

        pthread_join(producer_thread, nullptr);
        pthread_join(consumer_thread, nullptr);

        clock_t end = clock();
        out << "time: " << (end - start) / static_cast<double>(CLOCKS_PER_SEC) << "\n";
    }
    return EXIT_SUCCESS;
}