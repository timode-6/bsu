import random as r

print("Mood Sensor (датчик настроения)")
play_again=True

while play_again:
    #Генерируем случайнео число от 1 до 10
    number = r.randint(1, 10)

    #Определяем настроение на основе сгенерированного числа
    if number == 1:
        mood = "Ужасное"
    elif number <=3:
        mood = "Плохое"
    elif number <=6:
        mood = "Нормальное"
    elif number <=8:
        mood = "Хорошее"
    else:
        mood = "Отличное"

#Выводим настроение вместе с соответствующим графическим символом
print("Ваше настроение: " + mood)
print(moods[mood])
play_again = input("Хотите узнать свое настроение еще раз? (д/н)").lower() == 'д'