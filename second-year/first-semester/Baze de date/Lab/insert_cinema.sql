INSERT INTO Utilizator(Nume, Parola) VALUES 
('Raul', 'parola1'),
('Ana', 'parola2'), 
('Andrei', 'parola3'), 
('Ion', 'parola4')
SELECT * FROM Utilizator

INSERT INTO Film(Nume, Durata, Limba, Dimensiune) VALUES
('IT', 90, 'engleza', '2D'), --1
('Venom', 90, 'engleza', '2D'), --2
('Spiderman', 80, 'engleza', '3D'), --3
('Old guard', 100, 'engleza', '2D'), --4
('Shrek', 95, 'engleza', '3D'), --5
('Hobbit', 120, 'engleza', '2D'), --6
('Primul rege', 100, 'romana', '2D') –7
INSERT INTO Film(Nume, Durata, Limba, Dimensiune) VALUES
('Film ro', 150, 'romana', '2D')
INSERT INTO Film(Nume, Durata, Limba, Dimensiune) VALUES
('Film spania', 60, 'spaniola', '2D') 
SELECT * FROM Film

INSERT INTO Rezervare(IdFilm, IdUtilizator, DataRezervare) VALUES
(1, 2, convert(datetime,'10-01-21 10:30:00 PM',5)), --dd-mm-yy hh:mm:ss xm
(4, 1, convert(datetime,'12-05-21 10:30:00 PM',5)), 
(3, 2, convert(datetime,'4-06-21 10:30:00 PM',5)),
(2, 3, convert(datetime,'3-02-21 10:30:00 PM',5)), 
(6, 3, convert(datetime,'23-02-21 10:30:00 PM',5)), 
(1, 4, convert(datetime,'12-04-21 10:30:00 PM',5)),
(7, 4, convert(datetime,'17-02-21 10:30:00 PM',5)), 
(5, 1, convert(datetime,'18-03-21 10:30:00 PM',5))
SELECT * FROM Rezervare

INSERT INTO Cinema(Nume) VALUES 
('Cinema City'), --1
('Cineplexx') --2 
SELECT * FROM Cinema

INSERT INTO Manager(IdManager, Nume, Prenume, varsta) VALUES
(1, 'Popescu', 'Anca', 34),
(2, 'Bogdan', 'Radu', 43)
SELECT * FROM Manager

INSERT INTO Angajat(Nume, Prenume, varsta, IdCinema) VALUES
('Bob', 'Mihai', 20, 2),
('Gal', 'Andreea', 21, 2)
INSERT INTO Angajat(Nume, Prenume, varsta, IdCinema) VALUES
('Mircea', 'Florin', 32, 1), --1
('Florea', 'Georgiana', 24, 1), --2
('Cozma', 'Rares', 25, 1) --3
SELECT * FROM Angajat
INSERT INTO Angajat(Nume, Prenume, varsta, IdCinema) VALUES
('Dumitru', 'Dan', 45, 1),
('Dumitrache', 'Ana', 50, 1)

INSERT INTO Salariu(IdSalariu, Suma, Moneda) VALUES
(1, 2500, 'RON'),
(2, 3000, 'RON'),
(3, 650, 'EURO')

INSERT INTO Sala(NrLocuri, IdCinema) VALUES
(30, 1), --1
(30, 1), --2
(33, 1), --3
(20, 1), --4
(15, 1), --5
(15, 2), --6
(20, 2) --7
SELECT * FROM Sala

INSERT INTO Loc(Numar, Randd, IdSala) VALUES
(1, 1, 1),
(2, 1, 1),
(3, 1, 1),
(4, 1, 1),
(5, 2, 1),
(6, 2, 1),
(7, 2, 1),
(8, 3, 1),
(9, 3, 1),
(10, 3, 1),
(11, 3, 1)

INSERT INTO Loc(Numar, Randd, IdSala) VALUES
(12, 4, 1),
(1, 1, 2),
(2, 1, 2),
(3, 1, 2),
(4, 1, 2),
(5, 2, 2),
(6, 2, 2),
(7, 2, 2),
(8, 3, 2),
(9, 3, 2),
(10, 3, 2),
(11, 3, 2)

SELECT * FROM Loc

INSERT INTO Proiectare(IdFilm, IdSala, DataProiectare) VALUES 
(1, 1, convert(datetime,'21-06-21 8:00:00 PM',5)),
(2, 1, convert(datetime,'2-10-21 8:00:00 PM',5)),
(1, 2, convert(datetime,'1-06-21 8:00:00 PM',5)),
(3, 1, convert(datetime,'8-06-21 8:00:00 PM',5)),
(4, 2, convert(datetime,'3-06-21 8:00:00 PM',5)),
(5, 2, convert(datetime,'10-12-21 8:00:00 PM',5)),
(6, 2, convert(datetime,'21-06-21 8:00:00 PM',5)),
(3, 2, convert(datetime,'30-06-21 8:00:00 PM',5)),
(1, 3, convert(datetime,'2-10-21 8:00:00 PM',5))

SELECT * FROM Proiectare

--salile cu numar diferite de locuri
SELECT DISTINCT NrLocuri
FROM Sala 