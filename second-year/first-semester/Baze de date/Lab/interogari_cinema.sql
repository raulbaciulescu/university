GO
USE ArtaCinematografica
GO
--relatie m la n dintre Film si Sala, 
--afiseaza filmele care sunt proiectate intr-o sala mai mare de 30 de locuri
--afiseaza mai multe daca pun >=
SELECT f.IdFilm, f.Nume, s.IdSala, s.NrLocuri FROM Film f INNER JOIN Proiectare p ON f.IdFilm = p.IdFilm
INNER JOIN Sala s ON s.IdSala = p.IdSala
WHERE s.NrLocuri > 30

--relatie m la n dintre Utilizator si Film
--Utilizatori care se uita la film 3d
SELECT u.Nume, f.Nume, f.Dimensiune FROM 
Utilizator u INNER JOIN Rezervare r ON u.IdUtilizator = r.IdUtilizator 
INNER JOIN Film f ON f.IdFilm = r.IdFilm
WHERE f.Dimensiune = '3D'


--afisez cinema-ul in care salariul unui angajat e in euro
SELECT * FROM
Cinema c INNER JOIN Angajat a ON c.IDCinema = a.IdCinema 
INNER JOIN Salariu s ON s.IdSalariu = a.IDAngajat
WHERE s.Moneda = 'EURO'



--Varsta medie a angajatilor grupati dupa cinema, having afiseaza doar de la primul cinema
SELECT DISTINCT IdCinema, AVG(varsta) AS VarstaMedie
FROM Angajat
GROUP BY IdCinema
--HAVING AVG(varsta) >= 35



--Durata maxima a filmelor grupate dupa limba vorbita, unde media sa fie mai mare decat 90
--daca nu e having apare si spaniola
SELECT MAX(Durata), Limba
FROM Film
WHERE Dimensiune = '2D'
GROUP BY Limba
HAVING AVG(Durata) > 90


--Numarul de locuri dupa idSala
--in sala 1 am 12 locuri
--in sala 2 am 11 locuri
SELECT COUNT(Numar) AS NumarLocuri
FROM Loc
GROUP BY IdSala


--afisez utilizatorul, data proiectarii filmului la care are rezervare
--sa fie dupa luna a 6-a
SELECT u.Nume, f.Nume, p.DataProiectare
FROM Utilizator u INNER JOIN Rezervare r ON r.IdUtilizator = u.IdUtilizator
INNER JOIN Film f ON f.IdFilm = r.IdFilm
INNER JOIN Proiectare p ON p.IdFilm = f.IdFilm
WHERE MONTH(p.DataProiectare) > 6


--afisez in ce sali sunt facute rezervarile
SELECT r.IdUtilizator, r.DataRezervare, s.IdSala  
FROM Rezervare r INNER JOIN Film f ON f.IdFilm = r.IdFilm 
INNER JOIN Proiectare p ON p.IdFilm = f.IdFilm
INNER JOIN Sala s ON s.IdSala = p.IdSala

--afisez cinema-ul, data la care proiecteaza un film si sala
SELECT c.Nume, p.DataProiectare, s.IdSala
FROM Cinema c INNER JOIN Sala s ON s.IdCinema = c.IDCinema
INNER JOIN Proiectare p ON p.IdSala = s.IdSala

--relatia dintre angajat si manager
SELECT DISTINCT m.Nume, a.Nume 
FROM Manager m INNER JOIN Cinema c ON c.IDCinema = m.IdManager
INNER JOIN Angajat a ON a.IdCinema = c.IDCinema

select * from Film

SELECT Limba FROM Film
GROUP BY Limba
HAVING COUNT(*) = 12

INSERT INTO Film(Nume, Durata, Limba, Dimensiune) VALUES ('da', 24, 'maghiara', '2d')


select limba, count(*) as nr_pe_limba from Film
group by Limba
