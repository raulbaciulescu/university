def aplica_functie(lst, functie):
    init = lst[0]
    for i in range(1, len(lst)):
        init = functie(init, lst[i])
    return init

def suma_2(x, y):
    return x + y

lst = [2, 4, 2, 7, 5, 9, 23, -3, 4, -5]
# print(aplica_functie(lst, suma_2), sum(lst))
# print(aplica_functie(lst, lambda x, y: x+y))

print(sorted(lst, key=lambda x: x.pret / x.ore_lucrate))
