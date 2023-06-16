def f(n):
    if n == 0:
        return []

    save = f(n-1)
    return [save, [save]]

def f_inv(lst):
    if lst == []:
        return 0
    return 1 + f_inv(lst[0])

print(f(5))
for i in range(100):
    assert f_inv(f(i)) == i