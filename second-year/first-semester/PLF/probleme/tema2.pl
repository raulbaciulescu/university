%maxim(L:list, C: Integer, Max:Integer)
%L: lista in care cautam maximul
%C: variabila colectoare pentru maxim
%Max: maximul listei L
%(i,i), (i,o) - determinist
maxim([], R, R).
maxim([H|T], C,R):-
    H >= C,
    maxim(T, H, R),
    !.
maxim([_|T], C, R):-
    maxim(T, C, R).
maxim([H|T], R):-
    maxim(T, H, R).


%poz_aux(L: list, I: integer, Maxim: Integer, Rez: list)
%L: lista din care se determina pozitiile
%I: numarul pozitiei pe care putem gasi maximul
%Rez: lista cu pozitii rezultata
%(i,i,i,o) - determinist
poz_aux([], _, _, []):-!.
poz_aux([H|T], I, M, [I|Rez]):-
    M == H,
    I1 is I + 1,
    poz_aux(T, I1, M, Rez),!.
poz_aux([_|T], I, M, Rez):-
    I1 is I + 1,
    poz_aux(T, I1, M, Rez).

%poz(L: list, Rez: list)
%L: lista din care cautam pozitiile maximului
%Rez: lista cu pozitiile maxime
poz(L, Rez):-
    maxim(L, M),
    poz_aux(L, 1, M, Rez).


%inlocuire(L: list eterogena, Rez: lista etero)
%L: lista pe care se face operatiunea de inlocuire
%Rez: lista rezultata operatiei
%(i,o), (i, i) - determinist
inlocuire([], []):-!.
inlocuire([H|T], [H|Rez]):-
    number(H),
    inlocuire(T, Rez).
inlocuire([H|T], [X|Rez]):-
    poz(H, X),
    inlocuire(T, Rez).

