def get_primes():
    yield 2
    nr_curent = 3
    while True:
        ok = True
        for i in range(3, int(nr_curent**0.5)+1, 2):
            if nr_curent % i == 0:
                ok = False
                break
        if ok:
            yield nr_curent
        nr_curent += 2



primes = get_primes()
for i in range(100):
    print(next(primes))


