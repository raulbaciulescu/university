USE ArtaCinematografica

CREATE TABLE Comenzi (comanda varchar(50))
SELECT * FROM Film
SELECT * FROM Comenzi

--exemplu dirty reads 1 
UPDATE Film SET Durata=90 WHERE Nume='IT';
DELETE FROM Comenzi

INSERT INTO Comenzi(comanda) VALUES ('begin transaction')
BEGIN TRAN
UPDATE Film SET Durata=200 WHERE Nume='IT';
INSERT INTO Comenzi(comanda) VALUES ('update film')
WAITFOR DELAY '00:00:07';
ROLLBACK TRAN;
INSERT INTO Comenzi(comanda) VALUES ('rollback')

--exemplu unrepeatable 1 
DELETE FROM Comenzi
UPDATE Film SET Durata=90 WHERE Nume='IT';

SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
BEGIN TRANSACTION;
INSERT INTO Comenzi(comanda) VALUES ('begin transaction')
SELECT * FROM Film;
INSERT INTO Comenzi(comanda) VALUES ('select all 1')
WAITFOR DELAY '00:00:7';
SELECT * FROM Film;
INSERT INTO Comenzi(comanda) VALUES ('select all 2')
COMMIT TRANSACTION;

--solutie unrepeatable 
DELETE FROM Comenzi
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
BEGIN TRANSACTION;
INSERT INTO Comenzi(comanda) VALUES ('begin transaction')
SELECT * FROM Film;
INSERT INTO Comenzi(comanda) VALUES ('select all 1')
WAITFOR DELAY '00:00:7';
SELECT * FROM Film;
INSERT INTO Comenzi(comanda) VALUES ('select all 2')
COMMIT TRANSACTION;


--exemplu phantom reads 1 
DELETE FROM Film WHERE nume='Film phantom'
DELETE FROM Comenzi


SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
INSERT INTO Comenzi(comanda) VALUES ('begin transaction')
BEGIN TRANSACTION;
SELECT * FROM Film;
INSERT INTO Comenzi(comanda) VALUES ('select all 1')
WAITFOR DELAY '00:00:7';
SELECT * FROM Film;
INSERT INTO Comenzi(comanda) VALUES ('select all 2')
COMMIT TRANSACTION;

--solutie phantom reads 1 
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE ;
BEGIN TRANSACTION;
INSERT INTO Comenzi(comanda) VALUES ('begin transaction')
SELECT * FROM Film;
INSERT INTO Comenzi(comanda) VALUES ('select all 1')
WAITFOR DELAY '00:00:7';
SELECT * FROM Film;
INSERT INTO Comenzi(comanda) VALUES ('select all 2')
COMMIT TRANSACTION;


--exemplu deadlock 1
DELETE FROM Comenzi
UPDATE Film SET Limba = 'romana' WHERE Nume = 'Venom'
UPDATE Utilizator SET Parola = 'parola123' WHERE Nume = 'Bob3'

SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
BEGIN TRY
	BEGIN TRAN;
		INSERT INTO Comenzi(comanda) VALUES ('begin transaction')
		UPDATE Film SET Limba = 'tran1' WHERE Nume = 'Venom'
		INSERT INTO Comenzi(comanda) VALUES ('update in film 1');
		WAITFOR DELAY '00:00:7'
		UPDATE Utilizator SET Parola = 'tran1' WHERE Nume = 'Bob3'
		INSERT INTO Comenzi(comanda) VALUES ('update in utilizator 1');
	COMMIT TRAN;
END TRY
BEGIN CATCH
	ROLLBACK TRANSACTION
	INSERT INTO Comenzi(comanda) VALUES ('deadlock in tranzactia 1');
END CATCH

select * from Comenzi

--solutie deadlock
SET DEADLOCK_PRIORITY HIGH;
BEGIN TRY
	BEGIN TRAN;
		INSERT INTO Comenzi(comanda) VALUES ('begin transaction')
		UPDATE Utilizator SET Parola = 'tran1' WHERE Nume = 'Bob3'
		INSERT INTO Comenzi(comanda) VALUES ('update in utilizator 1');
		WAITFOR DELAY '00:00:7'
		UPDATE Film SET Limba = 'tran1' WHERE Nume = 'Venom'
		INSERT INTO Comenzi(comanda) VALUES ('update in film 1');
	COMMIT TRAN;
END TRY
BEGIN CATCH
	ROLLBACK TRANSACTION
	INSERT INTO Comenzi(comanda) VALUES ('deadlock in tranzactia 1');
END CATCH


SELECT * FROM Film
SELECT * FROM Utilizator
SELECT * FROM Comenzi

GO
CREATE OR ALTER PROCEDURE thread1
AS
BEGIN
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE
	BEGIN TRAN
		INSERT INTO Comenzi(comanda) VALUES ('begin transaction')
		UPDATE Film SET Limba = 'tran1' WHERE Nume = 'Venom'
		INSERT INTO Comenzi(comanda) VALUES ('update in film 1');
		WAITFOR DELAY '00:00:7'
		UPDATE Utilizator SET Parola = 'tran1' WHERE Nume = 'Bob3'
		INSERT INTO Comenzi(comanda) VALUES ('update in utilizator 1');
	COMMIT TRAN
END

GO
CREATE OR ALTER PROCEDURE thread2
AS
BEGIN
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE
	BEGIN TRAN
		UPDATE Utilizator SET Parola = 'tran2' WHERE Nume = 'Bob3'
		INSERT INTO Comenzi(comanda) VALUES ('update in utilizator 2');
		WAITFOR DELAY '00:00:7'
		UPDATE Film SET Limba = 'tran2' WHERE Nume = 'Venom'
		INSERT INTO Comenzi(comanda) VALUES ('update in film 2');
	COMMIT TRAN
END