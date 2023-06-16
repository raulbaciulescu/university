def my_map(f, lst):
    return [f(x) for x in lst]

def my_map_rec(f, lst):
    if lst == []:
        return []

    return [f(lst[0])] + my_map_rec(f, lst[1:])

print(my_map(lambda x: x**2, [2, 3, 4, 5]))
print(my_map_rec(lambda x: x**2, [2, 3, 4, 5]))


'''
def map_rec(numbers, fun):
    if numbers == []:
        r eturn []
    return [fun(numbers[0])] + map_rec(numbers[1:], fun)]
'''

d = {'asd': 12, 'dsd': 13, 'sdds': 1}
d3= [(121, 2323), (13,232) , (1, 22), (-23, -12)]
print(sorted(d3))
print(quicksort(d3))
a = sorted([1, 3, -1, 36, 8, 23, 11, 56, 23, 56, 12, 65])