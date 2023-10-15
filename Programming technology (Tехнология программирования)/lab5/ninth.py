import random as r

print("Загадайте число от 1 до 100")

min_number = 1
max_number = 100
guesses = 0

while True:
    guess = (min_number + max_number) // 2
    print(f"Компьютер предполагает, что загаданное число равно {guess}")
    answer = input("Это число больше (>), меньше (<) или равно (=) загаданному? ")
    if answer == ">":
        min_number = guess + 1
    elif answer == "<":
        max_number = guess - 1
    elif answer == "=":
        print(f"Компьютер угадал число за {guesses} попыток")
        break
    else:
        print("Некорректный ввод, попробуйте снова")
    guess +=1
