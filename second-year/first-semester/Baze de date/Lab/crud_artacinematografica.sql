USE ArtaCinematografica
GO

CREATE OR ALTER FUNCTION dbo.TestLimba(@Limba VARCHAR(50))
RETURNS INT
AS
BEGIN
	IF @Limba = 'romana'
		RETURN 1;
	IF @Limba = 'engleza'
		RETURN 1;
	RETURN 0;
END
GO

CREATE OR ALTER FUNCTION dbo.TestDimensiune(@Dimensiune VARCHAR(50))
RETURNS INT
AS
BEGIN
	IF @Dimensiune = '3D'
		RETURN 1;
	IF @Dimensiune = '2D'
		RETURN 1;
	RETURN 0;
END
GO

CREATE OR ALTER PROCEDURE dbo.CRUD_Film
@nume VARCHAR(50),
@Durata INT,
@Limba VARCHAR(50),
@Dimensiune VARCHAR(50)
AS
BEGIN
SET NOCOUNT ON;
	-- verific parametrii
	IF (dbo.TestLimba(@Limba) = 1 AND dbo.TestDimensiune(@Dimensiune) = 1)
	BEGIN
		--INSERT
		INSERT INTO Film(Nume, Durata, Limba, Dimensiune) VALUES (@nume, @Durata, @Limba, @Dimensiune)
		--SELECT
		SELECT * FROM Film
		--UPDATE
		UPDATE Film SET Nume = @nume
		WHERE IdFilm = (SELECT MAX(IdFilm) FROM Film)

		--DELETE
		DELETE FROM Film WHERE Nume = @nume

		print 'opearatiile crud s-au efectuat pentru tabelul Film'
	END
	ELSE
	BEGIN
		PRINT 'parametrii nu sunt corecti!'
	END
END

GO
CREATE OR ALTER FUNCTION dbo.TestParolaUtilizator(@Parola VARCHAR(50))
RETURNS INT
AS
BEGIN
	IF @Parola LIKE '%[0-9]%'
		RETURN 1;
	RETURN 0;
END
GO

GO
CREATE OR ALTER FUNCTION dbo.TestNumeUtilizator(@nume VARCHAR(50))
RETURNS INT
AS
BEGIN
	IF @nume = ''
		RETURN 0;
	RETURN 1;
END
GO
--SELECT dbo.TestParolaUtilizator('da')
--SELECT dbo.TestParolaUtilizator('da1')

GO
CREATE OR ALTER PROCEDURE dbo.CRUD_Utilizator
@nume VARCHAR(50),
@parola VARCHAR(50)
AS
BEGIN
SET NOCOUNT ON;
	-- verific parametrii
	IF (dbo.TestParolaUtilizator(@parola) = 1 AND dbo.TestNumeUtilizator(@nume) = 1)
	BEGIN
		--INSERT
		INSERT INTO Utilizator(Nume, Parola) VALUES (@nume, @parola)
		--SELECT
		SELECT * FROM Utilizator
		--UPDATE
		UPDATE Utilizator SET Nume = @nume
		WHERE IdUtilizator = (SELECT MIN(IdUtilizator) FROM Utilizator)

		--DELETE
		DELETE FROM Utilizator WHERE Nume = @nume

		print 'opearatiile crud s-au efectuat pentru tabelul Utilizator'
	END
	ELSE
	BEGIN
		PRINT 'parametrii nu sunt corecti!'
	END
END
GO
--UPDATE Utilizator SET Nume = 'da'
--		WHERE IdUtilizator = (SELECT MIN(IdUtilizator) FROM Utilizator)
--SELECT * FROM Utilizator
GO
CREATE OR ALTER FUNCTION dbo.TestIdFilm(@IdFilm INT)
RETURNS INT
AS
BEGIN
	IF EXISTS (SELECT * FROM Film WHERE IdFilm = @IdFilm)
		RETURN 1;
	RETURN 0;
END
GO

GO
CREATE OR ALTER FUNCTION dbo.TestIdUtilizator(@IdUtilizator INT)
RETURNS INT
AS
BEGIN
	IF EXISTS (SELECT * FROM Utilizator WHERE IdUtilizator = @IdUtilizator)
		RETURN 1;
	RETURN 0;
END
GO

GO
CREATE OR ALTER PROCEDURE dbo.CRUD_Rezervare
@IdFilm INT,
@IdUtilizator INT,
@DataRezervare DATETIME
AS
BEGIN
SET NOCOUNT ON;
	-- verific parametrii
	IF (dbo.TestIdFilm(@IdFilm) = 1 AND dbo.TestIdUtilizator(@IdUtilizator) = 1)
	BEGIN
		--INSERT
		INSERT INTO Rezervare(IdFilm, IdUtilizator, DataRezervare) VALUES (@IdFilm, @IdUtilizator, @DataRezervare)
		--SELECT
		SELECT * FROM Rezervare
		--UPDATE
		UPDATE Rezervare SET DataRezervare = '20210102'
		WHERE DataRezervare > '20210101'

		--DELETE
		DELETE FROM Rezervare WHERE DataRezervare = @DataRezervare

		print 'opearatiile crud s-au efectuat pentru tabelul Rezervare'
	END
	ELSE
	BEGIN
		PRINT 'parametrii nu sunt corecti!'
	END
END
GO


GO
CREATE OR ALTER VIEW View_1 AS 
	--Utilizatori care se uita la film 3d
	SELECT u.Nume as NumeUser, f.Dimensiune FROM 
	Utilizator u INNER JOIN Rezervare r ON u.IdUtilizator = r.IdUtilizator 
	INNER JOIN Film f ON f.IdFilm = r.IdFilm
	WHERE f.Dimensiune = '2D'
GO
CREATE OR ALTER VIEW View_2 AS
	SELECT Nume FROM Film
	WHERE Nume LIKE 'N%'
GO



IF EXISTS (SELECT NAME FROM sys.indexes WHERE name='N_idx_film_nume')
DROP INDEX N_idx_film_nume ON Film
CREATE NONCLUSTERED INDEX N_idx_film_nume ON Film(Nume)

IF EXISTS (SELECT NAME FROM sys.indexes WHERE name='N_idx_utilizator_nume')
DROP INDEX N_idx_utilizator_nume ON Utilizator
CREATE NONCLUSTERED INDEX N_idx_utilizator_nume ON Utilizator(Nume)


IF EXISTS (SELECT NAME FROM sys.indexes WHERE name='N_idx_film_dimensiune')
DROP INDEX N_idx_film_dimensiune ON Film
CREATE NONCLUSTERED INDEX N_idx_film_dimensiune ON Film(Dimensiune)


SELECT * FROM Film

GO
CREATE OR ALTER PROCEDURE test AS
BEGIN
	EXEC CRUD_Film @nume = 'film1', @Durata = 60, @Limba = 'romana', @Dimensiune = '3D';
	EXEC CRUD_Utilizator @nume = 'new_user', @parola = 'pass1';
	EXEC CRUD_Rezervare @IdFilm = 29097, @IdUtilizator = 30, @DataRezervare = '20210601';
	
	SELECT * from View_1
	SELECT * from View_2
END

INSERT INTO Utilizator(Nume, Parola) VALUES ('user', 'psdd')
select * from Utilizator
DELETE FROM Rezervare where IdUtilizator = 30
EXEC test


select * from Rezervare
select * from Utilizator
select * from Film


INSERT INTO Rezervare(IdFilm, IdUtilizator, DataRezervare) VALUES
(29049, 2, convert(datetime,'10-01-21 10:30:00 PM',5)), --dd-mm-yy hh:mm:ss xm
(29059, 1, convert(datetime,'12-05-21 10:30:00 PM',5)), 
(29058, 2, convert(datetime,'4-06-21 10:30:00 PM',5)),
(29050, 3, convert(datetime,'3-02-21 10:30:00 PM',5)), 
(29051, 3, convert(datetime,'23-02-21 10:30:00 PM',5)), 
(29055, 4, convert(datetime,'12-04-21 10:30:00 PM',5)),
(29056, 4, convert(datetime,'17-02-21 10:30:00 PM',5)), 
(29057, 1, convert(datetime,'18-03-21 10:30:00 PM',5))

INSERT INTO Rezervare(IdFilm, IdUtilizator, DataRezervare) VALUES
(29049, 16, convert(datetime,'10-01-21 10:30:00 PM',5)), --dd-mm-yy hh:mm:ss xm
(29059, 17, convert(datetime,'12-05-21 10:30:00 PM',5)), 
(29058, 18, convert(datetime,'4-06-21 10:30:00 PM',5)),
(29050, 19, convert(datetime,'3-02-21 10:30:00 PM',5)), 
(29051, 20, convert(datetime,'23-02-21 10:30:00 PM',5)), 
(29055, 21, convert(datetime,'12-04-21 10:30:00 PM',5)),
(29056, 22, convert(datetime,'17-02-21 10:30:00 PM',5)), 
(29057, 23, convert(datetime,'18-03-21 10:30:00 PM',5))

ALTER TABLE dbo.Rezervare   
DROP CONSTRAINT FK_IdFilm;   
GO  

ALTER TABLE Rezervare
   ADD CONSTRAINT FK_IdFilm2 FOREIGN KEY (IdFilm)
      REFERENCES Film (IdFilm)
      ON DELETE CASCADE
