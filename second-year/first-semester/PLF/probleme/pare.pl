%pare(L:list, Rez:list)
%(i,o) - determinist
pare([], []).
pare([H|T], [H|Rez]):-
    H mod 2 =:= 0,
    !,
    pare(T, Rez).
pare([_|T], Rez):-
    pare(T,Rez).
