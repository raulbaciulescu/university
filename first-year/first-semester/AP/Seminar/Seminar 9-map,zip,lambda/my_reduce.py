from functools import reduce

print(reduce(lambda acumulator, elem: acumulator+elem, [1, 2, 3, 4]))

def my_reduce(f, lst):
    acumulator = lst[0]
    for i in range(1, len(lst)):
        acumulator = f(acumulator, lst[i])
    return acumulator

print(my_reduce(lambda acc, e: acc+e, [1,2,3,4]))