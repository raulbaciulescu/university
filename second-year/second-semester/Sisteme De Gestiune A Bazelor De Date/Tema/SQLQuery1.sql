create database SgbdExampleSem3
go
use SgbdExampleSem3
go

CREATE TABLE Presents(
present_id INT PRIMARY KEY IDENTITY,
description VARCHAR(100),
owner VARCHAR(100),
price REAL
);

CREATE TABLE Movies(
movie_id INT PRIMARY KEY IDENTITY,
title VARCHAR(100),
runtime INT
);

CREATE TABLE Actors(
actor_id INT PRIMARY KEY IDENTITY,
firstname VARCHAR(100),
lastname VARCHAR(100)
);

INSERT INTO Presents (description, owner, price) VALUES
('pony', 'Jack', 10000), ('candle', 'Jane', 2.5),
('chocolate', 'James', 3)

INSERT INTO Actors (firstname , lastname ) VALUES
('Bill', 'Skarsgard'), ('Chadwick', 'Boseman'),('Jennifer','Lawrence')




--exemplu de tranzactie explicita
BEGIN TRAN --marcheaza inceputul tranzactiei

UPDATE Presents SET description='horse' WHERE owner='Jack';

DELETE FROM Presents WHERE price=3;

COMMIT TRAN -- sfaritul tranzactiei


--exemplu de tranzactie implicita 
SET IMPLICIT_TRANSACTIONS ON;

INSERT INTO Presents(description, owner, price) VALUES
('car', 'Sam', 40000);

UPDATE Presents SET price=4 WHERE owner='Jane';

COMMIT TRAN


--exemplu de imbricare a tranzactiilor
BEGIN TRAN;

UPDATE Presents SET description='bike' WHERE owner='Jane';

BEGIN TRAN;

INSERT INTO Presents (description, owner, price) VALUES
('necklace', 'Sharon', 50);

COMMIT TRAN;
UPDATE Presents SET price=100 WHERE owner='Jane';
ROLLBACK TRAN;

--exemplu de tranzactie autocommit
SELECT * FROM Presents


--exemplu de tranzactie cu savepoint
BEGIN TRAN

INSERT INTO Movies(title, runtime) VALUES ('Frozen', 109);

SAVE TRAN savepoint;

INSERT INTO Actors(firstname, lastname) VALUES 
('Kristen', 'Bell');

ROLLBACK TRAN savepoint;

COMMIT TRAN;


SELECT * FROM Actors
SELECT * FROM Movies


--exemplu dirty reads
BEGIN TRAN
	UPDATE Movies SET runtime=200 WHERE title='IT';
	WAITFOR DELAY '00:00:07';
	ROLLBACK TRAN;