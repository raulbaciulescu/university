USE ArtaCinematografica

--exemplu dirty reads 2 
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
INSERT INTO Comenzi(comanda) VALUES ('begin transaction 2')
BEGIN TRAN;
SELECT * FROM Film;
INSERT INTO Comenzi(comanda) VALUES ('select all from film')
COMMIT TRAN

--solutie dirty reads
SET TRANSACTION ISOLATION LEVEL READ COMMITTED
BEGIN TRAN
SELECT * FROM Film;
INSERT INTO Comenzi VALUES ('select all from film')
COMMIT TRANSACTION


--exemplu unrepeatable 2 
BEGIN TRANSACTION;
INSERT INTO Comenzi VALUES ('begin transaction 2');
UPDATE Film SET Durata = 250 WHERE Nume = 'IT'
INSERT INTO Comenzi(comanda) VALUES ('update film')
COMMIT TRANSACTION;
INSERT INTO Comenzi(comanda) VALUES ('commit transaction');



--exemplu phantom reads 2 
BEGIN TRANSACTION;
INSERT INTO Comenzi VALUES ('begin transaction 2');
INSERT INTO Film(Nume, Durata, Limba, Dimensiune) VALUES ('Film phantom', 123, 'engleza', '2D');
INSERT INTO Comenzi(comanda) VALUES ('insert film')
COMMIT TRANSACTION;
INSERT INTO Comenzi(comanda) VALUES ('commit transaction');


--exemplu deadlock 2
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
BEGIN TRY
	BEGIN TRAN;
		UPDATE Utilizator SET Parola = 'tran2' WHERE Nume = 'Bob3'
		INSERT INTO Comenzi(comanda) VALUES ('update in utilizator 2');
		WAITFOR DELAY '00:00:7'
		UPDATE Film SET Limba = 'tran2' WHERE Nume = 'Venom'
		INSERT INTO Comenzi(comanda) VALUES ('update in film 2');
	COMMIT TRAN;
END TRY
BEGIN CATCH
	ROLLBACK TRANSACTION
	INSERT INTO Comenzi(comanda) VALUES ('deadlock in tranzactia 2');
END CATCH
