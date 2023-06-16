def my_zip(lst1, lst2):
    # [1, 2, 3], [5, 6, 7] => [(1, 5), (2, 6), (3, 7)]
    return [(lst1[i], lst2[i]) for i in range(len(lst1))]



def my_zip2(lst1, lst2):
    return [(lst1[x], lst2[x]) for x in range(0, min(len(lst1), len(lst2)))]

print(my_zip([1, 2, 3], [5, 6, 7]))
print(my_zip2([1, 2, 3], [5, 6, 7, 5]))



#exercitii pentru test
def a():
    return [x for x in range(-10, 11) if x > 0]


print(a())