%adaug(e: element, L:list, LRez:list)
%(i,i,o) - determinist
adaug(E,[],Rez):-Rez=[E].
adaug(E,[H|T],Rez):-
    adaug(E,T,L),Rez=[H|L].

%invers(L:list, Rez:list)
%(i,o) - determinist
invers([], []).
invers([H|T], Rez):-
    invers(T, L),
    adaug(H, L, Rez).
