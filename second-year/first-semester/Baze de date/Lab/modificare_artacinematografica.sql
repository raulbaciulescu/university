GO
USE ArtaCinematografica


CREATE TABLE Versiune(
Vers INT PRIMARY KEY DEFAULT 0
);


--1 modificarea tipului unei coloane
ALTER TABLE Manager 
ALTER COLUMN Varsta SMALLINT

--1 opusul
ALTER TABLE Manager 
ALTER COLUMN Varsta INT

--2 constrangerea default
ALTER TABLE Manager 
ADD CONSTRAINT default_18 DEFAULT 20 FOR Varsta

--2 opus
ALTER TABLE Manager DROP CONSTRAINT default_18;


--3 creare tabel 
CREATE TABLE CasaBilete(
IdCasa INT PRIMARY KEY IDENTITY,
NumarBilete INT 
);

--3 opus
DROP TABLE CasaBilete

--4 
ALTER TABLE CasaBilete
ADD IDCinema INT

--4 opus
ALTER TABLE CasaBilete
DROP COLUMN IDCinema

--5 creare cheie straina
ALTER TABLE CasaBilete ADD CONSTRAINT
fkCinemaCasa FOREIGN KEY(IDCinema) REFERENCES Cinema(IDCinema)

--5 opus
ALTER TABLE CasaBilete DROP CONSTRAINT fkCinemaCasa;

IF OBJECT_ID ( 'modificaTipColoanaInSmallint', 'P' ) IS NOT NULL   
    DROP PROCEDURE modificaTipColoanaInSmallint;  
GO



--proceduri:
--1
GO
CREATE PROCEDURE modificaTipColoanaInSmallint
AS
BEGIN
	ALTER TABLE Manager ALTER COLUMN Varsta SMALLINT
	PRINT 'Coloana Varsta a fost modificata in smallint, in tabelul Manager'
END
GO

--1 opus
GO
CREATE PROCEDURE modificaTipColoanaInInt
AS
BEGIN
	ALTER TABLE Manager ALTER COLUMN Varsta INT
	PRINT 'Coloana Varsta a fost modificata in int, in tabelul Manager'
END
GO
DROP PROCEDURE modificaTipColoanaInSmallint

EXEC modificaTipColoanaInInt

--2
GO
CREATE PROCEDURE adaugaConstrangereDefault
AS
BEGIN
	ALTER TABLE Manager ADD CONSTRAINT default_18 DEFAULT 20 FOR Varsta
	PRINT 'S-a adaugat constrangerea default'
END
GO

--2 OPUS
GO
CREATE PROCEDURE stergeConstrangereDefault
AS
BEGIN
	ALTER TABLE Manager DROP CONSTRAINT default_18;
	PRINT 'S-a sters constrangerea default'
END
GO

--DROP PROCEDURE adaugaConstrangereDefault
EXEC adaugaConstrangereDefault
EXEC stergeConstrangereDefault


--3 
GO
CREATE PROCEDURE creareTabelCasaBilete
AS
BEGIN
	CREATE TABLE CasaBilete(
	IdCasa INT PRIMARY KEY IDENTITY,
	NumarBilete INT 
	);
	PRINT 'S-a creat tabelul CasaBilete'
END
GO
--3 opus
GO
CREATE PROCEDURE stergeTabelCasaBilete
AS
BEGIN
	DROP TABLE CasaBilete
	PRINT 'S-a sters tabelul CasaBilete'
END
GO

EXEC creareTabelCasaBilete
EXEC stergeTabelCasaBilete


--4 
GO
CREATE PROCEDURE adaugaCampIdCinema
AS
BEGIN
	ALTER TABLE CasaBilete ADD IDCinema INT
	PRINT 'S-a adaugat campul id cinema'
END
GO
--4 opus
GO
CREATE PROCEDURE stergeCampIdCinema
AS
BEGIN
	ALTER TABLE CasaBilete DROP COLUMN IDCinema
	PRINT 'S-a sters campul id cinema'
END
GO

EXEC adaugaCampIdCinema
EXEC stergeCampIdCinema


--5 
GO
CREATE PROCEDURE creareCheieStraina
AS
BEGIN
	ALTER TABLE CasaBilete ADD CONSTRAINT fkCinemaCasa FOREIGN KEY(IDCinema) REFERENCES Cinema(IDCinema)
	PRINT 'S-a creat cheia straina IDCinema'
END
GO

--5 opus
GO
CREATE PROCEDURE stergeCheieStraina
AS
BEGIN
	ALTER TABLE CasaBilete DROP CONSTRAINT fkCinemaCasa;
	PRINT 'S-a sters cheia straina IDCinema'
END
GO

EXEC creareCheieStraina
EXEC stergeCheieStraina



GO
CREATE PROCEDURE principal @Versiune INT AS
BEGIN
	

	IF (@Versiune > 5)
	BEGIN
		PRINT 'Introdu o versiune intre 0 si 5'
		RETURN
	END
	declare @Contor int = (SELECT Vers FROM Versiune)
	IF (@Versiune = @Contor)
	BEGIN
		PRINT 'Nu trebuie schimbat nimic'
		RETURN
	END
	WHILE (@Contor != @Versiune)
		BEGIN
			IF (@Contor < @Versiune)
				BEGIN
					SET @Contor = @Contor + 1;
					update Versiune set Vers = Vers + 1
					IF (@Contor = 1)
						BEGIN
							EXEC modificaTipColoanaInSmallint
							PRINT '0 -> 1'
						END
					IF (@Contor = 2)
						BEGIN
							EXEC adaugaConstrangereDefault
							PRINT '1 -> 2'
						END
					IF (@Contor = 3)
						BEGIN
							EXEC creareTabelCasaBilete
							PRINT '2 -> 3'
						END
					IF (@Contor = 4)
						BEGIN
							EXEC adaugaCampIdCinema
							PRINT '3 -> 4'
						END
					IF (@Contor = 5)
						BEGIN
							EXEC creareCheieStraina
							PRINT '4 -> 5'
						END
				END
			ELSE
				BEGIN
	
					
					IF (@Contor = 1)
						BEGIN
							EXEC modificaTipColoanaInInt
							PRINT '1 -> 0'
						END
					IF (@Contor = 2)
						BEGIN
							EXEC stergeConstrangereDefault
							PRINT '2 -> 1'
						END
					IF (@Contor = 3)
						BEGIN
							EXEC stergeTabelCasaBilete
							PRINT '3 -> 2'
						END
					IF (@Contor = 4)
						BEGIN
							EXEC stergeCampIdCinema
							PRINT '4 -> 3'
						END
					IF (@Contor = 5)
						BEGIN
							EXEC stergeCheieStraina
							PRINT '5 -> 4'
						END
					SET @Contor = @Contor - 1;
					update Versiune set Vers = Vers - 1
				END
		END
END
GO


exec principal 9
select * from Versiune
--insert into Versiune (Vers) values (0)
--update Versiune set Vers = 0
exec principal 4
exec principal 3
exec principal 0
exec principal 13
drop procedure principal

