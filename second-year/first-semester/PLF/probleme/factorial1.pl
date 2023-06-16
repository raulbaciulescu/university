%fact1(N:integer, F:integer)
%(i,i)(i, o)
fact1(0, 1).
fact1(N, F):- N > 0,
    N1 is N - 1,
    fact1(N1, F1),
    F is N * F1.

