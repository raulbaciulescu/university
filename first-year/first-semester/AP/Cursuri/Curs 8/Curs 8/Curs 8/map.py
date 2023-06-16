lst = [2, 4, 2, 7, 5, 9, 23, -3, 4, -5]

mapped_lst = list(map(lambda x: x*x if x > 0 else x, lst))
print(mapped_lst)
print([x*x if x > 0 else x for x in lst])