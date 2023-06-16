%subm(L: list, Rez: list)
%(i, o) - nedeterminist
subm([], []).
subm([_|T], Rez):-
    subm(T, Rez).
subm([H|T], [H|Rez]):-
    subm(T, Rez).


%toate_subm(L: list, Rez: list)
%(i, o) - determinist
toate_subm(L, Rez):-
    findall(X, subm(L, X), Rez).
