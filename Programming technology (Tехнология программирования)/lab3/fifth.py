import datetime

#запрашиваем дату рождения поользователя
birthday_str = input("Введиет дату рождения (в формате ГГГГ-ММ-ДД): ")

#преобразуем строку даты рождения в объект datetime
birthday = datetime.datetime.strptime(birthday_str, "%Y-%m-%d")

#вычисляем количество прожитых секунд
age_in_seconds = (datetime.datetime.now() - birthday).total_seconds()

#выводим результат на экран
print("Ваш возраст в секундах: ", int(age_in_seconds))