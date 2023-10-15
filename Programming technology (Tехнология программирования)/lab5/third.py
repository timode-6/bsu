import math as m

def actual_func(x):
    return m.log((x+1)/(x-1))

def func(x):
    sum = float()
    n = 0
    while n < 500:
        sum +=(1/((2*n + 1)* (x**(2*n + 1))))
        n+=1
    return sum * 2

print(x, '\t', n, '\t', func(x), '\t', actual_func(x), '\t', str(eps))