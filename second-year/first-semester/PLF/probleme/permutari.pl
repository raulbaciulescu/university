%insereaza(E: element, L: list, Rez: list)
%(i, i, o) nedeterminist, mai multe modele de flux
insereaza(E, L, [E|L]).
insereaza(E, [H|T], [H|Rez]):-
    insereaza(E, T, Rez).

%perm(L: list, Rez: list)
%(i, o) nedeterminist
perm([], []).
perm([E|T], Rez):-
    perm(T, L),
    insereaza(E, L, Rez).



aranj([E|_], 1, [E]).
aranj([_|T], K, Rez):-
    aranj(T, K, Rez).
aranj([H|T], K, Rez):-
    K =\= 1,
    K1 is K - 1,
    aranj(T, K1, Rez1),
    insereaza(H, Rez1, Rez).



