num = input("Введите число: ")
n = int(input("Введите число, которое нужно вставлять: "))

result = num[0]
for i in range(1, len(num)):
    result += str(n) + num[i]

print(result)