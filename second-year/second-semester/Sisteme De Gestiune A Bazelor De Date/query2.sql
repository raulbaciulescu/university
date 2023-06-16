USE P152022


--exemplu phantom reads 2 
BEGIN TRANSACTION;
INSERT INTO Categorii (descriere_c, nume_c) VALUES
('cartegorie phantom', 'nume phantom');
COMMIT TRANSACTION;