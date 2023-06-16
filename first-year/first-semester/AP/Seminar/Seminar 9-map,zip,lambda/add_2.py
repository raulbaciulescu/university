def add_lists_v1(lst1, lst2):
    # [3, 5, -1] + [4, 3, 2] = [7, 8, 1]
    return [lst1[i] + lst2[i] for i in range(len(lst1))]

def add_lists_v2(lst1, lst2):
    return list(map(lambda x, y: x+y, lst1, lst2))

def add_lists_v3(lst1, lst2):
    # return list(map(lambda x: x[0]+x[1], zip(lst1, lst2)))
    return list(map(lambda x: sum(x), zip(lst1, lst2)))

assert add_lists_v1([3, 5, -1], [4, 3, 2]) == [7, 8, 1]
assert add_lists_v2([3, 5, -1], [4, 3, 2]) == [7, 8, 1]
assert add_lists_v3([3, 5, -1], [4, 3, 2]) == [7, 8, 1]