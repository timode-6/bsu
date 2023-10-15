import random as r

def guess_number():
    number = r.randint(1,100)
    num_of_guesses = 0

    while True:
        guess = int(input("Введите число от 1 до 100: "))
        num_of_guesses +=1

        if guess< number:
            print("Загаданное число больше.")
        elif guess > number:
            print("Загаданное число меньше.")
        else:
            print("Вы угадали число {} за {} попыток.".format(number, num_of_guesses))
            break

        play_again = input("Хотите сыграть еще раз? (д/н): ")
        if play_again.lower() == "да":
            guess_number()
        else:
            print("Спасибо за игру. До свидания!")

guess_number()