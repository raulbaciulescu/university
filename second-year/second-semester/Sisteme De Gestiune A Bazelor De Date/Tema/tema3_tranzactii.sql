USE ArtaCinematografica
GO

CREATE OR ALTER FUNCTION dbo.testLimba(@Limba VARCHAR(50))
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

CREATE OR ALTER FUNCTION dbo.testDimensiune(@Dimensiune VARCHAR(50))
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

CREATE OR ALTER FUNCTION dbo.testDurata(@Durata INT)
RETURNS INT
AS
BEGIN
	IF @Durata > 0 
		RETURN 1;
	RETURN 0;
END
GO

CREATE OR ALTER FUNCTION dbo.testDataRezervare(@DataRezervare DATETIME)
RETURNS INT
AS
BEGIN
	IF @DataRezervare > '20210101'
		RETURN 1;
	RETURN 0;
END
GO

GO
CREATE OR ALTER FUNCTION dbo.testParolaUtilizator(@Parola VARCHAR(50))
RETURNS INT
AS
BEGIN
	IF @Parola LIKE '%[0-9]%'
		RETURN 1;
	RETURN 0;
END
GO

GO
CREATE OR ALTER FUNCTION dbo.testNumeUtilizator(@nume VARCHAR(50))
RETURNS INT
AS
BEGIN
	IF @nume = ''
		RETURN 0;
	RETURN 1;
END
GO

GO
CREATE OR ALTER FUNCTION dbo.testNumeFilm(@nume VARCHAR(50))
RETURNS INT
AS
BEGIN
	IF @nume = ''
		RETURN 0;
	RETURN 1;
END
GO


GO
CREATE OR ALTER FUNCTION dbo.testIdFilm(@IdFilm INT)
RETURNS INT
AS
BEGIN
	IF EXISTS (SELECT * FROM Film WHERE IdFilm = @IdFilm)
		RETURN 1;
	RETURN 0;
END
GO

GO
CREATE OR ALTER FUNCTION dbo.testIdUtilizator(@IdUtilizator INT)
RETURNS INT
AS
BEGIN
	IF EXISTS (SELECT * FROM Utilizator WHERE IdUtilizator = @IdUtilizator)
		RETURN 1;
	RETURN 0;
END
GO

GO
CREATE OR ALTER PROCEDURE dbo.procedureRollBack
@numeUtilizator VARCHAR(50),
@parolaUtilizator VARCHAR(50),
@numeFilm VARCHAR(50),
@durataFilm VARCHAR(50),
@limbaFilm VARCHAR(50),
@dimensiuneFilm VARCHAR(50),
@dataRezervare DATETIME
AS
BEGIN
SET NOCOUNT ON;
	
	BEGIN TRAN
	BEGIN TRY
		DECLARE @idFilm INT;
		DECLARE @idUtilizator INT;
		SET @idFilm = (select max(IdFilm) from Film);
		SET @idUtilizator = (select max(IdUtilizator) from Utilizator);

		IF (dbo.testParolaUtilizator(@parolaUtilizator) = 0 OR dbo.testNumeUtilizator(@numeUtilizator) = 0)
		BEGIN
			SELECT 'Eroare la datele utilizatorului!'
			RAISERROR('Eroare la datele utilizatorului!',14,1)
		END
		INSERT INTO Utilizator(Nume, Parola) VALUES (@numeUtilizator, @parolaUtilizator)
		SELECT 'Am inserat un utilizator nou!'

		IF (dbo.testNumeFilm(@numeFilm) = 0 OR dbo.testDurata(@durataFilm) = 0 OR dbo.testLimba(@limbaFilm) = 0 OR
			dbo.testDimensiune(@dimensiuneFilm) = 0)
		BEGIN
			SELECT 'Eroare la datele filmului!'
			RAISERROR('Eroare la datele filmului!',14,1)
		END
		INSERT INTO Film(Nume, Durata, Limba, Dimensiune) VALUES (@numeFilm, @durataFilm, @limbaFilm, @dimensiuneFilm)
		SELECT 'Am inserat un film nou!'
		
		IF (dbo.testDataRezervare(@dataRezervare) = 0)
		BEGIN
			SELECT 'Eroare la datele rezervare!'
			RAISERROR('Eroare la datele rezervare!',14,1)
		END
		INSERT INTO Rezervare(IdFilm, IdUtilizator, DataRezervare) VALUES
		((select max(IdFilm) from Film), (select max(IdUtilizator) from Utilizator), @dataRezervare)
		SELECT 'Am inserat un film nou!'

		COMMIT TRAN
		SELECT 'Tranzactie terminata'
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
		SELECT 'Rollback'
	END CATCH
END
GO

--exemplu functional
exec dbo.procedureRollBack
@numeUtilizator = 'Andrei', @parolaUtilizator = 'parola123', 
@numeFilm = 'Film', @durataFilm = 60, @limbaFilm = 'romana', @dimensiuneFilm = '3D', 
@dataRezervare = '10-01-22 10:30:00 PM'
SELECT * FROM Film WHERE IdFilm = (select max(IdFilm) FROM Film)
SELECT * FROM Utilizator WHERE IdUtilizator = (select max(IdUtilizator) FROM Utilizator)
SELECT * FROM Rezervare WHERE IdUtilizator = (select max(IdUtilizator) FROM Utilizator) AND IdFilm = (select max(IdFilm) FROM Film)



--exemplu nefunctional pe user
exec dbo.procedureRollBack
@numeUtilizator = 'Bob', @parolaUtilizator = 'parola', 
@numeFilm = 'Film neadaugat', @durataFilm = 60, @limbaFilm = 'romana', @dimensiuneFilm = '3D', 
@dataRezervare = '10-01-22 10:30:00 PM'
SELECT * FROM Film WHERE IdFilm = (select max(IdFilm) FROM Film)
SELECT * FROM Utilizator WHERE IdUtilizator = (select max(IdUtilizator) FROM Utilizator)
SELECT * FROM Rezervare WHERE IdUtilizator = (select max(IdUtilizator) FROM Utilizator) AND IdFilm = (select max(IdFilm) FROM Film)

--exemplu nefunctional pe film
exec dbo.procedureRollBack
@numeUtilizator = 'Bob', @parolaUtilizator = 'parola123', 
@numeFilm = 'Film neadaugat', @durataFilm = 60, @limbaFilm = 'romanaddd', @dimensiuneFilm = '3D', 
@dataRezervare = '10-01-22 10:30:00 PM'
SELECT * FROM Film WHERE IdFilm = (select max(IdFilm) FROM Film)
SELECT * FROM Utilizator WHERE IdUtilizator = (select max(IdUtilizator) FROM Utilizator)
SELECT * FROM Rezervare WHERE IdUtilizator = (select max(IdUtilizator) FROM Utilizator) AND IdFilm = (select max(IdFilm) FROM Film)


GO
CREATE OR ALTER PROCEDURE dbo.procedureSaveProgress
@numeUtilizator VARCHAR(50),
@parolaUtilizator VARCHAR(50),
@numeFilm VARCHAR(50),
@durataFilm VARCHAR(50),
@limbaFilm VARCHAR(50),
@dimensiuneFilm VARCHAR(50),
@dataRezervare DATETIME
AS
BEGIN
SET NOCOUNT ON;
	
	BEGIN TRAN
	BEGIN TRY
		SET XACT_ABORT ON
		DECLARE @idFilm INT;
		DECLARE @idUtilizator INT;
		SET @idFilm = (select max(IdFilm) from Film);
		SET @idUtilizator = (select max(IdUtilizator) from Utilizator);

		IF (dbo.testParolaUtilizator(@parolaUtilizator) = 0 OR dbo.testNumeUtilizator(@numeUtilizator) = 0)
		BEGIN
			SELECT 'Eroare la datele utilizatorului!'
			RAISERROR('Eroare la datele utilizatorului!',14,1)
		END
		INSERT INTO Utilizator(Nume, Parola) VALUES (@numeUtilizator, @parolaUtilizator)
		SELECT 'Am inserat un utilizator nou!'
		SAVE TRAN savepoint1

		IF (dbo.testNumeFilm(@numeFilm) = 0 OR dbo.testDurata(@durataFilm) = 0 OR dbo.testLimba(@limbaFilm) = 0 OR
			dbo.testDimensiune(@dimensiuneFilm) = 0)
		BEGIN
			SELECT 'Eroare la datele filmului!'
			ROLLBACK TRAN savepoint1
			COMMIT TRAN
			RAISERROR('Eroare la datele filmului!',14,1)
		END
		INSERT INTO Film(Nume, Durata, Limba, Dimensiune) VALUES (@numeFilm, @durataFilm, @limbaFilm, @dimensiuneFilm)
		SELECT 'Am inserat un film nou!'
		SAVE TRAN savepoint2

		IF (dbo.testDataRezervare(@dataRezervare) = 0)
		BEGIN
			SELECT 'Eroare la datele rezervare!'
			ROLLBACK TRAN savepoint2
			COMMIT TRAN
			RAISERROR('Eroare la datele rezervare!',14,1)
		END
		INSERT INTO Rezervare(IdFilm, IdUtilizator, DataRezervare) VALUES
		((select max(IdFilm) from Film), (select max(IdUtilizator) from Utilizator), @dataRezervare)
		SELECT 'Am inserat un film nou!'

		COMMIT TRAN
		SELECT 'Tranzactie terminata'
	END TRY
	BEGIN CATCH
		--ROLLBACK TRAN savepoint
		SELECT 'Rollback'
	END CATCH
END
GO

--exemplu functional
exec dbo.procedureSaveProgress
@numeUtilizator = 'AndreiSaveProgress', @parolaUtilizator = 'parolaProgress123', 
@numeFilm = 'FilmSaveProgress', @durataFilm = 60, @limbaFilm = 'romana', @dimensiuneFilm = '3D', 
@dataRezervare = '10-01-22 10:30:00 PM'
SELECT * FROM Film WHERE IdFilm = (select max(IdFilm) FROM Film)
SELECT * FROM Utilizator WHERE IdUtilizator = (select max(IdUtilizator) FROM Utilizator)
SELECT * FROM Rezervare WHERE IdUtilizator = (select max(IdUtilizator) FROM Utilizator) AND IdFilm = (select max(IdFilm) FROM Film)

--exemplu nefunctional pe film
exec dbo.procedureSaveProgress
@numeUtilizator = 'Bob2', @parolaUtilizator = 'parola123', 
@numeFilm = 'Film neadaugat', @durataFilm = 60, @limbaFilm = 'romanadddddddd', @dimensiuneFilm = '3D', 
@dataRezervare = '10-01-22 10:30:00 PM'
SELECT * FROM Film WHERE IdFilm = (select max(IdFilm) FROM Film)
SELECT * FROM Utilizator WHERE IdUtilizator = (select max(IdUtilizator) FROM Utilizator)
SELECT * FROM Rezervare WHERE IdUtilizator = (select max(IdUtilizator) FROM Utilizator) AND IdFilm = (select max(IdFilm) FROM Film)

GO
CREATE OR ALTER PROCEDURE dbo.procedureSaveProgressV2
@numeUtilizator VARCHAR(50),
@parolaUtilizator VARCHAR(50),
@numeFilm VARCHAR(50),
@durataFilm VARCHAR(50),
@limbaFilm VARCHAR(50),
@dimensiuneFilm VARCHAR(50),
@dataRezervare DATETIME
AS
BEGIN
SET NOCOUNT ON;
	
	BEGIN TRAN
	BEGIN TRY
		SET XACT_ABORT ON
		DECLARE @idFilm INT;
		DECLARE @idUtilizator INT;
		SET @idFilm = (select max(IdFilm) from Film);
		SET @idUtilizator = (select max(IdUtilizator) from Utilizator);

		IF (dbo.testParolaUtilizator(@parolaUtilizator) = 0 OR dbo.testNumeUtilizator(@numeUtilizator) = 0)
		BEGIN
			SELECT 'Eroare la datele utilizatorului!'
			RAISERROR('Eroare la datele utilizatorului!',14,1)
		END
		INSERT INTO Utilizator(Nume, Parola) VALUES (@numeUtilizator, @parolaUtilizator)
		SELECT 'Am inserat un utilizator nou!'
		COMMIT TRAN
		SELECT 'Tranzactie terminata'

		BEGIN TRAN
		IF (dbo.testNumeFilm(@numeFilm) = 0 OR dbo.testDurata(@durataFilm) = 0 OR dbo.testLimba(@limbaFilm) = 0 OR
			dbo.testDimensiune(@dimensiuneFilm) = 0)
		BEGIN
			SELECT 'Eroare la datele filmului!'
			RAISERROR('Eroare la datele filmului!',14,1)
		END
		INSERT INTO Film(Nume, Durata, Limba, Dimensiune) VALUES (@numeFilm, @durataFilm, @limbaFilm, @dimensiuneFilm)
		SELECT 'Am inserat un film nou!'
		COMMIT TRAN
		SELECT 'Tranzactie terminata'

		BEGIN TRAN
		IF (dbo.testDataRezervare(@dataRezervare) = 0)
		BEGIN
			SELECT 'Eroare la datele rezervare!'
			ROLLBACK TRAN savepoint2
			COMMIT TRAN
			RAISERROR('Eroare la datele rezervare!',14,1)
		END
		INSERT INTO Rezervare(IdFilm, IdUtilizator, DataRezervare) VALUES
		((select max(IdFilm) from Film), (select max(IdUtilizator) from Utilizator), @dataRezervare)
		SELECT 'Am inserat un film nou!'

		COMMIT TRAN
		SELECT 'Tranzactie terminata'
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
		SELECT 'Rollback'
	END CATCH
END
GO

--exemplu functional
exec dbo.procedureSaveProgressV2
@numeUtilizator = 'AndreiSaveProgress', @parolaUtilizator = 'parolaProgress123', 
@numeFilm = 'FilmSaveProgress', @durataFilm = 60, @limbaFilm = 'romana', @dimensiuneFilm = '3D', 
@dataRezervare = '10-01-22 10:30:00 PM'
SELECT * FROM Film WHERE IdFilm = (select max(IdFilm) FROM Film)
SELECT * FROM Utilizator WHERE IdUtilizator = (select max(IdUtilizator) FROM Utilizator)
SELECT * FROM Rezervare WHERE IdUtilizator = (select max(IdUtilizator) FROM Utilizator) AND IdFilm = (select max(IdFilm) FROM Film)

--exemplu nefunctional pe film
exec dbo.procedureSaveProgressV2
@numeUtilizator = 'Bob3', @parolaUtilizator = 'parola123', 
@numeFilm = 'Film neadaugat', @durataFilm = 60, @limbaFilm = 'romanadddddddd', @dimensiuneFilm = '3D', 
@dataRezervare = '10-01-22 10:30:00 PM'
SELECT * FROM Film WHERE IdFilm = (select max(IdFilm) FROM Film)
SELECT * FROM Utilizator WHERE IdUtilizator = (select max(IdUtilizator) FROM Utilizator)
SELECT * FROM Rezervare WHERE IdUtilizator = (select max(IdUtilizator) FROM Utilizator) AND IdFilm = (select max(IdFilm) FROM Film)


SELECT * FROM Film
SELECT * FROM Utilizator
SELECT * FROM Rezervare