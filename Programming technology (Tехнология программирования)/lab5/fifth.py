print("АТМ (банкомат)")
play_again = True
while play_again:
    amount = float(input("Введите сумму для снятия: "))
    notes, coins = atm(int(amount * 100))
    for note, count in notes:
         print(f"{count} x  {note} евро")
    for coin, count in coins:
        print(f"{count} x {coin} евроцентов")
    play_again = input("Хотите рассчитать еще раз? (y/n) ").lower() == 'y'
    
    