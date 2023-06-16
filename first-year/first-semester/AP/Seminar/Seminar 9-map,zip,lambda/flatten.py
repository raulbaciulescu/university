def flatten(lst):
    # [3, 5, [2, 3, [4, [5]], 6], [[[2]]], 3] => [3, 5, 2, 3, 4, 5, 6, 2, 3]
    result = []
    for elem in lst:
        if isinstance(elem, list):
            result.extend(flatten(elem))
        else:
            result.append(elem)
    return result

lst = [3, 5, [2, 3, [4, [5]], 6], [[[2]]], 3]
print(flatten(lst))
assert flatten(lst) == [3, 5, 2, 3, 4, 5, 6, 2, 3]