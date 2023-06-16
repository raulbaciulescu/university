%pereche(E: element, L:lista, LRez:list)
%(i, i, o) nedeterminist
pereche(E, [H|_], [E, H]):-
    E < H.
pereche(E, [_| T], Rez):-
    pereche(E, T, Rez).


%perechi(L: list, Rez: list)
%(i, o) nedeterminist
perechi([H|T], Rez):-
    pereche(H, T, Rez).
perechi([_|T], Rez):-
    perechi(T, Rez).


%toate_perechi(L: list, Rez:list)
%(i, o) determinist
toate_perechi(L, Rez):-
    findall(X, perechi(L, X), Rez).



