%insereaza(E: element, L: list, Rez: list)
%(i, i, o) nedeterminist, mai multe modele de flux
insereaza(E, L, [E|L]).
insereaza(E, [H|T], [H|Rez]):-
    insereaza(E, T, Rez).
