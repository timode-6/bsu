import math as m

sum = float()

while True:
    temp = float(input("Введите число: "))
    sum+=(temp**2)
    if temp == 0:
        break
print("Сумма квадратов введенных чисел равна: ", sum)
