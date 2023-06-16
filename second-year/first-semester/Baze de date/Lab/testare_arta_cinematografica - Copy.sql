GO
USE ArtaCinematografica

GO
CREATE OR ALTER VIEW View1 AS 
	SELECT * FROM Sala
GO


CREATE OR ALTER VIEW View2 AS
	--SELECT p.DataProiectare, f.Nume FROM Film f INNER JOIN Proiectare p ON f.IdFilm = p.IdFilm
	SELECT p.DataProiectare, f.Nume FROM Film f CROSS JOIN Proiectare p
GO


CREATE OR ALTER VIEW View3 AS
	SELECT p.IdSala, f.Nume FROM Film f INNER JOIN Proiectare p ON p.IdFilm = f.IdFilm
	GROUP BY p.IdSala, f.Nume
GO


INSERT INTO Tables (Name) VALUES ('Film'), ('Proiectare'), ('Sala'), ('Cinema')
INSERT INTO Tests (Name) VALUES ('delete_table'), ('insert_table'), ('select_table')
INSERT INTO Views (Name) VALUES ('View1'), ('View2'), ('View3')
INSERT INTO TestViews VALUES (3, 1), (3, 2), (3, 3)
INSERT INTO TestTables (TestID, TableID, NoOfRows, Position) VALUES 
(1, 1, 1000, 4), (1, 2, 1000, 1), (1, 3, 1000, 2), (1, 4, 1000, 3),
(2, 1, 1000, 1), (2, 2, 1000, 4), (2, 3, 1000, 3), (2, 4, 1000, 2)


DELETE FROM TestTables
SELECT * FROM TestViews
SELECT * FROM TestTables
SELECT * FROM Film
SELECT * FROM Proiectare





--insert cinema 1pk
GO
CREATE OR ALTER PROCEDURE insert_cinema AS
BEGIN
	DECLARE @NoOFRows int
	DECLARE @n int
	DECLARE @maxId int
	DECLARE @Nume VARCHAR(30)

	SELECT @NoOfRows = NoOfRows FROM TestTables WHERE TableID = 4 AND TestID = 2
	SELECT TOP 1 @maxId = IDCinema FROM Cinema ORDER BY IDCinema DESC
	SET @n = 0

	WHILE @n < @NoOfRows
	BEGIN
		SET @maxId = @maxId + 1
		SET @Nume = 'Nume' + CAST(@maxId AS VARCHAR)
		INSERT INTO Cinema (Nume) VALUES (@Nume)
		SET @n = @n + 1
	END
END


--insert film 1pk
GO
CREATE OR ALTER PROCEDURE insert_film AS
BEGIN
	DECLARE @NoOFRows INT
	DECLARE @n INT
	DECLARE @maxId INT
	DECLARE @Nume VARCHAR(30)
	DECLARE @Limba VARCHAR(30)
	DECLARE @Dimensiune VARCHAR(30)
	DECLARE @num INT
	
	SELECT @NoOfRows = NoOfRows FROM TestTables WHERE TableID = 1 AND TestID = 2
	SELECT TOP 1 @maxId = IdFilm FROM Film ORDER BY IdFilm DESC
	SET @n = 0

	WHILE @n < @NoOfRows
	BEGIN
		SET @Nume = 'Nume' + CAST(@n AS VARCHAR)
		SET @num = CEILING(RAND() * 5)
		SET @Limba = CHOOSE(@num, 'romana', 'engleza', 'spaniola', 'rusa', 'franceza')
		SET @num = CEILING(RAND() * 2)
		SET @Dimensiune = CHOOSE(@num, '2D', '3D')
		SET @num = RAND() * (121 - 61) + 61; --numar random intre 61 si 121
		INSERT INTO Film (Nume, Durata, Limba, Dimensiune) VALUES (@Nume, @num, @Limba, @Dimensiune)
		SET @n = @n + 1
	END
	EXEC insert_proiectare
END


--insert Sala 1pk, 1fk
GO
CREATE OR ALTER PROCEDURE insert_sala AS
BEGIN
	DECLARE @NoOFRows INT
	DECLARE @n INT
	DECLARE @NrLocuri INT
	DECLARE @IdCinema INT
	

	SELECT @NoOfRows = NoOfRows FROM TestTables WHERE TableID = 3 AND TestID = 2
	SET @n = 0
	SELECT TOP 1 @IdCinema = IdCinema FROM Cinema ORDER BY NEWID()
	WHILE @n < @NoOfRows
	BEGIN
		SET @NrLocuri = RAND() * (36 - 9) + 9; --numar random intre 9 si 36
		INSERT INTO Sala (NrLocuri, IdCinema) VALUES (@NrLocuri, @IdCinema)
		SET @n = @n + 1
	END
END



--insert proiectare 2pk
GO
CREATE OR ALTER PROCEDURE insert_proiectare AS
BEGIN
	DECLARE @NoOFRows INT
	SELECT @NoOfRows = NoOfRows FROM TestTables WHERE TableID = 2 AND TestID = 2
	DECLARE @FromDate DATETIME2(0)
	DECLARE @ToDate DATETIME2(0)
	SET @FromDate = '2020-01-01 10:00:00' 
	SET @ToDate = '2021-12-30 23:59:59'			
	DECLARE @Seconds INT = DATEDIFF(SECOND, @FromDate, @ToDate)
	DECLARE @Random INT = ROUND(((@Seconds-1) * RAND()), 0)
	SET @FromDate = DATEADD(SECOND, @Random, @FromDate)

	DELETE FROM Proiectare

	SELECT IdSala, IdFilm, @FromDate AS DataProiectare
	INTO aux
	FROM Sala CROSS JOIN Film

	INSERT INTO Proiectare
	SELECT TOP (@NoOfRows) IdFilm, IdSala, DataProiectare FROM aux

	DROP TABLE aux
END
SELECT * FROM Proiectare
--delete 1
GO
CREATE OR ALTER PROCEDURE delete_proiectare AS
BEGIN
	DECLARE @NoOFRows INT
	SELECT @NoOfRows = NoOfRows FROM TestTables WHERE TableID = 4 AND TestID = 1
	DELETE TOP (@NoOFRows) FROM Proiectare
END
--delete 2
GO
CREATE OR ALTER PROCEDURE delete_sala AS
BEGIN
	DECLARE @NoOFRows INT
	SELECT @NoOfRows = NoOfRows FROM TestTables WHERE TableID = 3 AND TestID = 1
	DELETE TOP (@NoOFRows) FROM Sala
END
--delete 3
GO
CREATE OR ALTER PROCEDURE delete_cinema AS
BEGIN
	DECLARE @NoOFRows INT
	SELECT @NoOfRows = NoOfRows FROM TestTables WHERE TableID = 4 AND TestID = 1
	DELETE TOP (@NoOFRows) FROM Cinema
END
--delete 4
GO
CREATE OR ALTER PROCEDURE delete_film AS
BEGIN
	DECLARE @NoOFRows INT
	SELECT @NoOfRows = NoOfRows FROM TestTables WHERE TableID = 1 AND TestID = 1
	DELETE TOP (@NoOFRows) FROM Film
END

GO
CREATE OR ALTER PROCEDURE test_sala AS
BEGIN
	DECLARE @ds DATETIME-- start time test
	DECLARE @di DATETIME-- intermediate time test
	DECLARE @de DATETIME-- end time test
	SET @ds = GETDATE()
	EXEC delete_sala
	EXEC insert_sala

	-- insert into table
	SET @di = GETDATE()
	-- evaluate (select from) view
	SELECT * FROM View1
	SET @de = GETDATE()
	INSERT INTO TestRuns (Description, StartAt, EndAt) VALUES ('test_sala_view1', @ds, @de)
	-- extract the TestRunId and “combine” it with the Id of table involved and also with the view involved in the corresponding tables
	DECLARE @IdTestRun INT = (SELECT TestRunID FROM TestRuns WHERE Description = 'test_sala_view1')
	DECLARE @IdTable INT = (SELECT TableID FROM Tables WHERE Name = 'Sala')
	DECLARE @IdView INT = (SELECT ViewID FROM Views WHERE Name = 'View1')

	INSERT INTO TestRunTables (TestRunID, TableID, StartAt, EndAt) VALUES (@IdTestRun, @IdTable, @ds, @di)
	INSERT INTO TestRunViews (TestRunID, ViewID, StartAt, EndAt) VALUES (@IdTestRun, @IdView, @di, @de)
END

GO
CREATE OR ALTER PROCEDURE test_film AS
BEGIN
	DECLARE @ds DATETIME-- start time test
	DECLARE @di DATETIME-- intermediate time test
	DECLARE @de DATETIME-- end time test
	SET @ds = GETDATE()
	EXEC delete_film
	EXEC insert_film

	-- insert into table
	SET @di = GETDATE()
	-- evaluate (select from) view
	SELECT * FROM View2
	SET @de = GETDATE()

	INSERT INTO TestRuns (Description, StartAt, EndAt) VALUES ('test_film_view2', @ds, @de)
	-- extract the TestRunId and “combine” it with the Id of table involved and also with the view involved in the corresponding tables
	DECLARE @IdTestRun INT = (SELECT TestRunID FROM TestRuns WHERE Description = 'test_film_view2')
	DECLARE @IdTable INT = (SELECT TableID FROM Tables WHERE Name = 'Film')
	DECLARE @IdView INT = (SELECT ViewID FROM Views WHERE Name = 'View2')

	INSERT INTO TestRunTables (TestRunID, TableID, StartAt, EndAt) VALUES (@IdTestRun, @IdTable, @ds, @di)
	INSERT INTO TestRunViews (TestRunID, ViewID, StartAt, EndAt) VALUES (@IdTestRun, @IdView, @di, @de)
END

GO
CREATE OR ALTER PROCEDURE test_proiectare AS
BEGIN
	DECLARE @ds DATETIME-- start time test
	DECLARE @di DATETIME-- intermediate time test
	DECLARE @de DATETIME-- end time test
	SET @ds = GETDATE()
	EXEC delete_proiectare
	EXEC insert_proiectare

	-- insert into table
	SET @di = GETDATE()
	-- evaluate (select from) view
	SELECT * FROM View2
	SET @de = GETDATE()

	INSERT INTO TestRuns (Description, StartAt, EndAt) VALUES ('test_proiectare_view3', @ds, @de)
	-- extract the TestRunId and “combine” it with the Id of table involved and also with the view involved in the corresponding tables
	DECLARE @IdTestRun INT = (SELECT TestRunID FROM TestRuns WHERE Description = 'test_proiectare_view3')
	DECLARE @IdTable INT = (SELECT TableID FROM Tables WHERE Name = 'Proiectare')
	DECLARE @IdView INT = (SELECT ViewID FROM Views WHERE Name = 'View3')

	INSERT INTO TestRunTables (TestRunID, TableID, StartAt, EndAt) VALUES (@IdTestRun, @IdTable, @ds, @di)
	INSERT INTO TestRunViews (TestRunID, ViewID, StartAt, EndAt) VALUES (@IdTestRun, @IdView, @di, @de)
END

GO
CREATE OR ALTER PROCEDURE test_all AS
BEGIN
	DELETE FROM TestRunTables
	DELETE FROM TestRunViews
	DELETE FROM TestRuns
	
	EXEC test_sala
	EXEC test_film
	EXEC test_proiectare

	SELECT * FROM TestRuns
	SELECT * FROM TestRunTables
	SELECT * FROM TestRunViews
END

SELECT * FROM TestRuns
SELECT * FROM TestRunTables
SELECT * FROM TestRunViews
SELECT * FROM Proiectare
SELECT * FROM Film
SELECT * FROM Sala
DELETE FROM Sala
DELETE FROM Film
DELETE FROM Proiectare


EXEC test_all

