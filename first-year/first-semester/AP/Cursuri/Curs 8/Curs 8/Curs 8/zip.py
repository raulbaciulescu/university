lst1 = [2, 4, 2, 7, 5, 9, 23, -3, 4, -5]
lst2 = [4, 5, 2, 3, 5, 6,  1,  2, 3,  4]
lst3 = [str(x) for x in lst2]

zip_12 = list(zip(lst1, lst2))
print(zip_12)

zip_123 = list(zip(lst1, lst2, lst3))
print(zip_123)
print(zip_123[1][2])

unzipped_12_1 = list(map(lambda x: x[1], zip_12))
unzipped_12_2 = list(map(lambda x: x[1], zip_12))

unzipped_12 = list(zip(*zip_12)) # zip((2, 4), (4, 5), (2, 2), ...) = (2, 4, 2, ...) (4, 5, 2, ...)
unzipped_12_1 = list(unzipped_12[0])
unzipped_12_2 = list(unzipped_12[1])
print(tuple([1, 2, 3]))
