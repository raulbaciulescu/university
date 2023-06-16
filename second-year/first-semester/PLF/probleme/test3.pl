%candidat(L: list, E: element)
%(i, o) - nedeterminist
%L: lista din care se determina candidatul
%E: candidatul ales
candidat([E|_],E).
candidat([_|T],E):-candidat(T,E).

%ultimul(L: list, E: element)
%(i, o) - determinist
%L: lista din care se determina elementul cerut
%E: ultimul element al listei
ultimul([_, _, _, L4], L4).


%solutie_aux(L: list, Rez:list, Nrx: integer, Col: list, LgCol: integer)
%(i, o, i, i, i) - nedeterminist
%L: lista [1, 'x', 2] cu variantele de pronosticuri
%Rez: Rezultatul operatiei, variantele de a se paria pe 4 meciuri
%Nrx: numarul de meciuri la care s-a ales 'x'
%Col: lista colectoare a pronosticurilor
%LgCol: lungimea listei colectoare
solutie_aux(_, Col, _, Col, 4):-
    ultimul(Col,E),
    E =\= 2.
solutie_aux(L, Rez, Nrx, Col, LgCol):-
    candidat(L, E),
    E == "x",
    Nrx1 is Nrx + 1,
    Nrx < 2,
    LgCol < 4,
    LgCol1 is LgCol + 1,
    solutie_aux(L, Rez, Nrx1, [E|Col], LgCol1).
solutie_aux(L, Rez, Nrx, Col, LgCol):-
    candidat(L, E),
    E \== "x",
    LgCol < 4,
    LgCol1 is LgCol + 1,
    solutie_aux(L, Rez, Nrx, [E|Col], LgCol1).


%solutie(Rez: list)
%(o) - nedeterminist
%Rez: pronosticurile pentru cele 4 meciuri
solutie(Rez):-
    solutie_aux([1, "x", 2], Rez, 0, [], 0).

sol(Rez):-
    findall(X, solutie(X), Rez).
