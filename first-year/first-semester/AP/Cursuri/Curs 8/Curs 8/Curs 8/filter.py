lst = [2, 4, 2, 7, 5, 9, 23, -3, 4, -5]

filtered_lst = list(filter(lambda x: x < 0, lst))
print(filtered_lst)
print([x for x in lst if x < 0])