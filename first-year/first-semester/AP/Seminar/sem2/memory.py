# 1
lst1 = [1, 2, 3]
lst2 = lst1
lst2[0] = 100
print(lst1) # [100, 2, 3]

# 2
lst1 = [1, 2, 3]
lst2 = lst1[:] # shallow copy
lst2[0] = 100
print(lst1) # [1, 2, 3]

lst1 = [[1], [2], [3]]
lst2 = lst1[:] # shallow copy
lst2[0][0] = 100
print(lst1) # [[100], [2], [3]]

# 3
from copy import deepcopy
lst1 = [[1], [2], [3]]
lst2 = deepcopy(lst1)
lst2[0][0] = 100
print(lst1) # [[1], [2], [3]]