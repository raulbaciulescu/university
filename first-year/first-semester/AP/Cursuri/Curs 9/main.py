import sys
from time import time

sys.setrecursionlimit(6000)


def quicksort(lst, key=lambda x: x):
    if lst == []:
        return []
    pivot = lst[0]
    stanga = [x for x in lst if key(x) < key(pivot)]
    egale = [x for x in lst if key(x) == key(pivot)]
    dreapta = [x for x in lst if key(x) > key(pivot)]
    return quicksort(stanga, key) + egale + quicksort(dreapta, key)

def quicksort_cmp(lst, cmp=lambda x, y: x - y):
    if lst == []:
        return []
    pivot = lst[0]
    stanga = [x for x in lst if cmp(x, pivot) < 0]
    egale = [x for x in lst if cmp(x, pivot) == 0]
    dreapta = [x for x in lst if cmp(x, pivot) > 0]
    return quicksort_cmp(stanga, cmp) + egale + quicksort_cmp(dreapta, cmp)

# lst = ['sadfdsff', 'asdgdfgdfgdfgdfgdffgdf', 'bfdafsdfsd']
# print(quicksort(lst, key=lambda x: len(x)))
# print(quicksort_cmp(lst, cmp=lambda x, y: len(x) - len(y)))
# print(sorted(lst, key=lambda x: len(x)))

#start = time()
#lst = [i for i in range(5000, 0, -1)]
#print(quicksort(lst))
#print(sorted(lst))
#end = time()

#print(f'Took {end - start} seconds')


