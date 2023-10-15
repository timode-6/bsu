import random as r

num_flips = int(input("Введите число подбросов: "))
heads = 0
tails = 0

for i in range(num_flips):
    flip = r.randint(0, 1)
    if flip == 0:
        heads +=1
    else: 
        tails +=1
    
print("Heads: ", heads)
print("Tails: ", tails)