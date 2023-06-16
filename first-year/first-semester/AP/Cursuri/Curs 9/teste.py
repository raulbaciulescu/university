def quicksort(lst, key=lambda x: x, reverse=False):
    if lst == []:
        return []
    pivot = lst[0]
    stanga = [x for x in lst if key(x) < key(pivot)]
    egale = [x for x in lst if key(x) == key(pivot)]
    dreapta = [x for x in lst if key(x) > key(pivot)]
    if reverse == True:
        aux = stanga
        stanga = dreapta
        dreapta = aux
    return quicksort(stanga, key) + egale + quicksort(dreapta, key)




