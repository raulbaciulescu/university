% list=integer*
% candidat(list, integer) (i, o) - nedeterminist
% un element posibil a fi adaugat in lista solutie
candidat([E|_],E).
candidat([_|T],E):-candidat(T,E).
% solutie(list,integer,list) (i,i,o) nedeterminist
% solutie_aux(list,integer,list,list,integer) (i,i,o,i,i) nedeterminist
% al patrulea argument colecteaza solutia, al cincilea argument
% reprezinta suma elementelor din colectoare
solutie(L, N, Rez) :-
candidat(L, E),
    E =< N,
    solutie_aux(L, N, Rez, [E], E).


solutie_aux(_, N, Rez, Rez, N):-!.
solutie_aux(L, N, Rez, [H | Col], S) :-
    candidat(L, E),
    E < H,
    S1 is S+E,
    S1 =< N,
    solutie_aux(L, N, Rez, [E | [H | Col]], S1).
