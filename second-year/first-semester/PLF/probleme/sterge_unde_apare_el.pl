%sterge(E: element, L: list, Rez: list)
%(i, i, o) nedeterminist, mai multe modele de flux
sterge(E, [E|T], T).
sterge(E, [H|T], [H|Rez]):-
    sterge(E, T, Rez).
