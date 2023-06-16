%combSuma(L: lista, K: integer, S: integer, Rez: lista)
%(i, i, i, o)
combSuma([S|_], 1, S, [S]).
combSuma([_|T], K, S, Rez):-
    combSuma(T, K, S, Rez).
combSuma([H|T], K, S, [H|Rez]):-
    K > 1,
    S1 is S - H,
    S1 > 0,
    K1 is K - 1,
    combSuma(T, K1, S1, Rez).
