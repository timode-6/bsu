def atm(amount):
    notest = [50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100]
    coins = [50, 20, 10, 5, 2, 1]

    res_notes = []
    res_notes = []
    for note in notes:
        count = amount // note
        if count > 0:
            res_notes.append((note // 100, count))
            amount -= note * count
        for coin in coins: 
            count = amount // coin
            if count > 0:
                res_coins.append((coin, count))
                amount -= coin * count 
        return res_notes, res_coins

amount = float(input("Введите сумму для снятия: "))
notes, coins = atm(int(amount * 100))

for note, count in notes:
    print(f"{count} x {note} евро")
for coin, count in coins:
    print(f"{count} x {coins} евроцентов")