# https://www.python-course.eu/python3_memoization.php

def f_memoized(n):
    d = {}
    def inner(n):
        if n < 2:
            return n

        if n in d:
            return d[n]
        d[n] = inner(n - 1) + inner(n - 2)
        return d[n]

    return inner(n)

def memoize(f):
    memo = {}
    def inner(n):
        if n not in memo:
            memo[n] = f(n)
        return memo[n]
    return inner

@memoize
def f(n):
    if n < 2:
        return n

    return f(n-1) + f(n-2)

print(f(40))