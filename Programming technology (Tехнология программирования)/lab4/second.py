import math as m

def G(x, a, f):
    if a * x > 0:
        return  m.pow(m.sin(f(x) + a), 2) - m.sqrt(abs(f(x)))
    elif a * x < 0:
        return m.pow(f(x) + 2*a, 2) - m.sqrt(abs(f(x)))
    elif a * x == 0:
        return m.pow(f(x) + a, 2) + 1

a = float(input("Введите параметр а: "))
choice = input("Выберите функицю f(x) (e^x или x^2): ")

if choice == "e^x":
    f = lambda x: m.exp(x)
elif choice == "x^2":
    f = lambda x: x**2
else:
    print("Ошибка ввода")
    exit()

print("x\t\tf(x)\tG(x)")
for x in range(-5, 6):
    print(str(x) + '\t' + str(a) + str(f(x)) + '\t' + str(G(x,a,f)))
    