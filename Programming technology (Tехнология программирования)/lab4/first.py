import math as m

print("Quadric Equation Solver")
print("Enter coefficients for the equation a*x^2 + b*x + c = 0")

a = float(input("Enter a: "))
b = float(input("Enter b: "))
c = float(input("Enter c: "))

if a==0:
    print("Ошибка: а не может быть равно нулю!")
else: 
    #Вычисляем дискриминант
    discriminant = b ** 2 - 4*a*c
    #Проверяем, имеет ли уравнение действительные корни
    if discriminant < 0:
        print("Уравнение не имеет действительных корней!")
    elif discriminant == 0:
        x = -b /(2*a)
    else:
        #Вычисляем корни уравнения
        root1 = (-b + m.sqrt(discriminant))/ (2*a)
        root2 = (-b - m.sqrt(discriminant))/ (2*a)

        #Выводим результат
        print("Root 1 = ", root1)
        print("Root 2 = ", root2)
        