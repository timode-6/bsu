# Задание 2
### Разработка Многопоточных приложений на языке C POSIX-потоки

__Задача__ 

Текстовый файл содержит задание на вычисление определенных интеграллов. 

Строки файла могут иметь, например, формат: 

    <function number/name> <left bound> <right bound>

Вычислить значения определенных интегралов.

*Вход:* текстовый файл с [заданием.](https://github.com/IBetULookGood/bsu/blob/master/Parallel%20Systems%20(%D0%A0%D0%B0%D1%81%D0%BF%D1%80%D0%B5%D0%B4%D0%B5%D0%BB%D0%B5%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%B8%20%D0%BF%D0%B0%D1%80%D0%B0%D0%BB%D0%BB%D0%B5%D0%BB%D1%8C%D0%BD%D1%8B%D0%B5%20%D1%81%D0%B8%D1%81%D1%82%D0%B5%D0%BC%D1%8B)%20%E2%80%94%20%D0%BA%D0%BE%D0%BF%D0%B8%D1%8F/lab_2/input.txt)

*Выход:* текстовый файл с [результатами вычислений.](https://github.com/IBetULookGood/bsu/blob/master/Parallel%20Systems%20(%D0%A0%D0%B0%D1%81%D0%BF%D1%80%D0%B5%D0%B4%D0%B5%D0%BB%D0%B5%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%B8%20%D0%BF%D0%B0%D1%80%D0%B0%D0%BB%D0%BB%D0%B5%D0%BB%D1%8C%D0%BD%D1%8B%D0%B5%20%D1%81%D0%B8%D1%81%D1%82%D0%B5%D0%BC%D1%8B)%20%E2%80%94%20%D0%BA%D0%BE%D0%BF%D0%B8%D1%8F/lab_2/output.txt)

__Задание__
+ Последовательная программа
+ Праллельная программа: модель «изготовитель-потребитель», POSIX-потоки, условные переменные.
+ Выполнить вычислительные эксперименты.

__Отчет__
+ Исходыне тексты программ.
+ Таблица с экспериментами. 
+ Разместить на портале.

__Литература__ 

1. [Методическое пособие](https://github.com/IBetULookGood/bsu/blob/master/Parallel%20Systems%20(%D0%A0%D0%B0%D1%81%D0%BF%D1%80%D0%B5%D0%B4%D0%B5%D0%BB%D0%B5%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%B8%20%D0%BF%D0%B0%D1%80%D0%B0%D0%BB%D0%BB%D0%B5%D0%BB%D1%8C%D0%BD%D1%8B%D0%B5%20%D1%81%D0%B8%D1%81%D1%82%D0%B5%D0%BC%D1%8B)%20%E2%80%94%20%D0%BA%D0%BE%D0%BF%D0%B8%D1%8F/lab_2/theory.pdf)
2. [POSIX THREADS](https://hpc-tutorials.llnl.gov/posix/)
3. [Conditional Variables](https://learn.microsoft.com/en-us/windows/win32/sync/using-condition-variables)
