USE P152022


SELECT * FROM Categorii
DELETE FROM Categorii WHERE nume_c='nume phantom'

--problema phantom reads 1 
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
BEGIN TRANSACTION;
SELECT * FROM Categorii;
WAITFOR DELAY '00:00:7';
SELECT * FROM Categorii;
COMMIT TRANSACTION;

--solutie phantom reads 1 
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE ;
BEGIN TRANSACTION;
SELECT * FROM Categorii;
WAITFOR DELAY '00:00:7';
SELECT * FROM Categorii;
COMMIT TRANSACTION;

DROP INDEX index_Categorii_descriere_c ON Categorii;
CREATE INDEX index_Categorii_descriere_c on Categorii(descriere_c ASC)

SELECT descriere_c FROM Categorii ORDER BY descriere_c