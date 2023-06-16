%stergeElement(E:element, L:list, Rez:list)
%(i, i, o) - determinist sau (i,i,i)
%E - elementul care trebuie eliminat din lista L
%L - lista din care trebuie eliminat elementul E
% R - lista rezultata dupa eliminarea tuturor aparitiilor elementului E
% in lista L
stergeElement(_, [], []):-!.
stergeElement(E, [E|T], Rez):-
    !,
    stergeElement(E, T, Rez).
stergeElement(E, [H|T], [H|Rez]):-
    stergeElement(E, T, Rez).


%numarAparitii(E: element, L:list, Nr:integer)
%(i,i,o),(i,i,i) determinist
%E - elementul pentru care numar aparitiile
%L - lista in care verific numarul de aparitii
%Nr - numarul de aparitiile ale lui E in lista L
numarAparitii(_, [], 0):-!.
numarAparitii(E, [E|T], Nr):-
    numarAparitii(E, T, Nr1),
    Nr is Nr1 + 1.
numarAparitii(E, [H|T], Nr):-
    E\=H,
    numarAparitii(E, T, Nr).


%stergeDublicate(L: list, LRez: list)
%(i, o), (i, i) - determinist
%L - lista din care vrem sa stergem dublicatele
%LRez - lista rezultata acestei operatii
stergeDublicate([], []):-!.
stergeDublicate([H|T], LRez):-
    numarAparitii(H, [H|T], Nr),
    Nr >= 2,
    stergeElement(H, T , Rez),
    stergeDublicate(Rez, LRez).
stergeDublicate([H|T], [H|LRez]):-
    numarAparitii(H, [H|T], Nr),
    Nr < 2,
    stergeDublicate(T, LRez).

%stergeDublicate([H|T], LRez):-
%numarAparitii(Hm [H|T], Nr),
%Nr > 2,
%stergeDublicate(T, Lrez1),
%LRez = [H|LRez1].
