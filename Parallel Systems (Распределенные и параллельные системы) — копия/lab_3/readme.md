# Задание 3

### C++11: поддержка многопоточности

[Энтони Уильямс С++. Практика многопоточного программирования СПб.: Питер, 2020](https://github.com/IBetULookGood/bsu/blob/master/Parallel%20Systems%20(%D0%A0%D0%B0%D1%81%D0%BF%D1%80%D0%B5%D0%B4%D0%B5%D0%BB%D0%B5%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%B8%20%D0%BF%D0%B0%D1%80%D0%B0%D0%BB%D0%BB%D0%B5%D0%BB%D1%8C%D0%BD%D1%8B%D0%B5%20%D1%81%D0%B8%D1%81%D1%82%D0%B5%D0%BC%D1%8B)%20%E2%80%94%20%D0%BA%D0%BE%D0%BF%D0%B8%D1%8F/lab_3/theory.pdf)

__Задание 3.1.__ Вычисление числа $\pi$:
+ Выполнить вычислительные эксперименты для определения ускорения и эффективности параллельной программы.
+ Сравнить с реализацией на *WinAPI*.

C++11: создание и ожидание завершения дочерних потоков
`````C++
    void main(){
        std::vector<std::thread> threads(p);
        int k;
        // создание дочерних потоков
        for(k = 0; k < p; ++k)
            therads[k] = std::thread(ThreadFunction, k);
            // ожидание завершения дочерних потоков
        for(k = 0; k < p; ++k)
            threads[k].join();
        double sum = 0;
        for(k = 0; k< p; ++k)
            sum += pi[k];
        prinf("pi = %.16f\n", sum);
    }
`````
C++11: измерение времени
`````C++
    
    auto start = std::chrono::high_resolution_clock::now();
    do_work();
    auto stop = std::chrono::high_resolution_clock::now();
    std::chrono::duration<double> diff = stop - start;
    std::cout<< diff.start() << " seconds\n";
    std::cout<< std::chrono::duration_cast<std::chrono::milliseconds>
    (diff).count() << " milliseconds\n";
`````
__Задание 3.2__ Параллельное суммирование

Пример из книги Энтони Уильямса «Простейшая параллельная версия 
std::accumulate» [(стр. 60)](https://github.com/IBetULookGood/bsu/blob/master/Parallel%20Systems%20(%D0%A0%D0%B0%D1%81%D0%BF%D1%80%D0%B5%D0%B4%D0%B5%D0%BB%D0%B5%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%B8%20%D0%BF%D0%B0%D1%80%D0%B0%D0%BB%D0%BB%D0%B5%D0%BB%D1%8C%D0%BD%D1%8B%D0%B5%20%D1%81%D0%B8%D1%81%D1%82%D0%B5%D0%BC%D1%8B)%20%E2%80%94%20%D0%BA%D0%BE%D0%BF%D0%B8%D1%8F/lab_3/theory.pdf)
+ Выполнить вычислительные эксперименты для определения ускорения и эффективности параллельной программы.

__Отчет__
+ По Заданию 3.1 - таблица с экспериментами.
+ По Заданию 3.2 - исходные тексты программ и таблица с экспериментами.
+ Разместить на портале.
