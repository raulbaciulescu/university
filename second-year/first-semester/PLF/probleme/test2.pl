%contine(L: lista, X: simbol)
%L - lista la care verificam apartenenta lui X
%X - simbolul a carui apartenenta o verificam
%model de flux (i,i) sau (i,o)

contine1([Cap|_],X):-X is Cap,!.
contine1([_|Coada],X):-contine1(Coada,X).

%diferenta(L1: lista, L2: lista, L3: lista)
%L1 - mutlimea careia ii facem diferenta
%L2 - multimea care face diferenta
%L3 - multimea rezultata din L2\L3
%model de flux (i,i,i) sau (i,i,o)

diferenta1(L,L,[]):-!.
diferenta1([Cap|Coada],L,X):-contine1(L,Cap),diferenta1(Coada,L,X),!.
diferenta1([H|T],_,[H|X]):-
    diferenta1(T,_,X).


diffSet([], X, X).

diffSet([H|T1],Set,Z):-
 member(H, Set),       % NOTE: arguments swapped!
 !, delete(T1, H, T2), % avoid duplicates in first list
 delete(Set, H, Set2), % remove duplicates in second list
 diffSet(T2, Set2, Z).

diffSet([H|T], Set, [H|Set2]) :-
 diffSet(T,Set,Set2).
