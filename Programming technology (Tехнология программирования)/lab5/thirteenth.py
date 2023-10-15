n = int(input("Введите натуральное число: "))
max_digit = max(str(n))
result = ""
for digit in str(n):
    if digit == max_digit:
        result +=digit*2
    else:
        result += digit
print("Результат: ", result)