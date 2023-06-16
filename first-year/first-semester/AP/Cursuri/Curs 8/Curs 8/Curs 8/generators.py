# https://www.python-course.eu/python3_generators.php#:~:text=%22yield%20from%22%20is%20available%20since,it%20encounters%20a%20StopIteration%20exception.

def permutations(items):
    n = len(items)
    if n==0:
        yield []
    else:
        for i in range(len(items)):
            for cc in permutations(items[:i]+items[i+1:]):
                yield [items[i]]+cc

for p in permutations(['r','e','d']):
    print('|'.join(p))