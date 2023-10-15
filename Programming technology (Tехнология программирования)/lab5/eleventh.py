def is_perfect_number(number):
    #Перебираем все возможные делители числа
    divisors_sum = 0
    for i in range(1, number):
        if number % i == 0:
            divisors_sum +=i
    #проверяем, является ли число совершенным
    return divisors_sum == number

#Пример использования программы
number = int(input("Введите число: "))
if is_perfect_number(number):
    print(f"{number} - совершенное число")
else:
    print(f"{number} - не совершенное число")        