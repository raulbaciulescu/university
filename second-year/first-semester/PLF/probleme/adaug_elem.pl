%adaug(e: element, L:list, LRez:list)
%(i,i,o) - determinist
adaug(E,[],Rez):-Rez=[E].
adaug(E,[H|T],Rez):-
    adaug(E,T,L),Rez=[H|L].

