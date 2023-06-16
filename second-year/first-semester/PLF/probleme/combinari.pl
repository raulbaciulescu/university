%comb(L: list, K: interger, Rez: list)
%(i, i, o) - nedeterminist
comb([H|_], 1, [H]).
comb([_|T], K, Rez):-
    comb(T, K, Rez).
comb([H|T], K, [H|Rez]):-
    K > 1,
    K1 is K - 1,
    comb(T, K1, Rez).




%toate_comb(L: list, K: integer, Rez: list)
%(i, i, o) - determinist
toate_comb(L, K, Rez):-
    findall(X, comb(L, K, X), Rez).
