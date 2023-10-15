import math

x1 = float(input("Введите параметр х1: "))
x2 = float(input("Введите параметр x2: "))

if x2< x1:
    print("x2 должно быть больше x1")
    exit()

dx = 0.1
k = 13

def F(x,a,b,c):
    if(x<0) and (a!=0):
        return c*(k+3) - (k*(x**(k-1)) + a*b*math.sin(k+1))**2
    elif x> 0 and a==0:
        return 2*math.sqrt(c) + (-a + k*b*x)/(x+b)
    else:
        return ((k+1)**2)*a*b + 1/(k*c*b)

a = float(input("Введите параметр а: "))
b = float(input("Введите параметр b: "))
c = float(input("Введите параметр c: "))

print("x\tx2\tdx\ta\tb\tc\tF(x)")

x:int = x1

while(x < x2):
    x+=dx
    print(str(x1) + '\t' + str(x2) + '\t' + str(dx) + '\t' + str(a) + '\t' + str(b) + '\t' + str(c) + '\t' + str(F(x,a,b,c)))
