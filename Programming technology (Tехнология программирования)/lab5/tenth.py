num = int(input("Введите натуральное число: "))
sum = 0
count = 0

while num > 0:
    digit = num % 10
    sum += digit
    count += 1
    num //= 10

print("Сумма цифр: ", sum)
print("Количество цифр: ", count)