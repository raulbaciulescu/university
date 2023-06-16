insert_everywhere([], E, [E]).
insert_everywhere([H|T], E, [E,H|T]).
insert_everywhere([H|L], E, [H|R]) :-
	insert_everywhere(L, E, R).

% arr(L:list, N:int, R:list)
% flow model: (i i o), (i i i)

arr([E|_], 1, [E]).
arr([_|L], N, R) :-
	arr(L, N, R).
arr([A|L], N, R) :-
	N =\= 1,
	N1 is N - 1,
	arr(L, N1, R1),
	insert_everywhere(R1, A, R).

arrangements(L, N, R) :-
	findall(X, arr(L, N, X), R).



ultimul([_,_,_,L4], L4).
solutie_pronostic():-
	solutie_pronostic_aux([1, 'x', 2], 0, [], 0).
solutie_pronostic_aux(_, _, Col, 4, Col):-
	ultimul(Col, E),
	E =\= 2.
solutie_pronostic_aux(L, Nrx, Col, LgCol, Rez):-
	candidat1(L, E),
	Nrx < 2,
	LgCol < 4,
	E == "x",
	LgCol1 is LgCol + 1,
	Nrx1 is Nrx + 1,
	solutie_pronostic_aux(L, Nrx1, [E|Col], LgCol1, Rez).
solutie_pronostic_aux(L, Nrx, Col, LgCol, Rez):-
	candidat(L, E),
	E \== "x",
	LgCol < 4,
	LgCol1 is LgCol + 1,
	solutie_pronostic_aux(L, Nrx, [E|Col], LgCol1, Rez).
