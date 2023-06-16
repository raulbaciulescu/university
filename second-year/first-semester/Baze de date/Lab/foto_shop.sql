CREATE DATABASE FotoShop
GO
USE FotoShop
GO

CREATE TABLE Categorii(
Cid INT PRIMARY KEY IDENTITY,
Denumire_Categorie VARCHAR(30))

CREATE TABLE Produse(
Pid INT PRIMARY KEY IDENTITY,
Denumire_Produs VARCHAR(100),
Pret INT CHECK(Pret>0) NOT NULL,
Descriere_Produs VARCHAR(1000),
Cid INT FOREIGN KEY REFERENCES Categorii(Cid))

CREATE TABLE Clienti(
Cid INT PRIMARY KEY IDENTITY,
Nume_Client VARCHAR(50),
Prenume_Client VARCHAR(50),
CMail VARCHAR(50),
CTelefon VARCHAR(50)
)

CREATE TABLE Achizitii(
Cid INT FOREIGN KEY REFERENCES Clienti(Cid),
Pid INT FOREIGN KEY REFERENCES Produse(Pid)
CONSTRAINT Pk_Achizitii PRIMARY KEY(Cid,Pid)
)
CREATE TABLE Recenzii(
Rid int primary key identity,
Cid INT FOREIGN KEY REFERENCES Clienti(Cid),
Numar_Stele INT CHECK(Numar_Stele>0 AND Numar_Stele<=5) NOT NULL,
Recenzie VARCHAR(1000)
)



-- Categorii
INSERT INTO Categorii(Denumire_Categorie) VALUES ('Profesional'), ('Ocazional'), ('Uzual')

-- Produse
-- delete from Produse
INSERT INTO Produse(Denumire_Produs, Pret, Descriere_Produs, Cid)
VALUES ('Canon d5K','8800','Aparat de fotografiat Canon',2)
INSERT INTO Produse(Denumire_Produs, Pret, Descriere_Produs, Cid)
VALUES ('Nikon D850','10000','Aparat de fotografiat Nikon',3)
INSERT INTO Produse(Denumire_Produs, Pret, Descriere_Produs, Cid)
VALUES ('Sony Alpha9','25000','Aparat de fotografiat Sony',2)
-- Clienti
INSERT INTO Clienti(Nume_Client, Prenume_client, CMail, CTelefon)
VALUES ('Almasan','Radu','a@acd.ro','0752525522'), ('Cristea', 'Docolin', 'sd@gfby.com', '0712121212')
-- Achizitii
Insert into Achizitii(Cid, Pid) Values (1, 1), (1, 2) --am incercat si o crapat
-- Recenzii
insert into Recenzii(Cid, Numar_Stele, Recenzie) Values (1, 4, 'bun'), (2, 5, 'FB')

--DELETE FROM Categorii
--DELETE FROM Produse
--DELETE FROM Clienti
--DELETE FROM Achizitii
--DELETE FROM Categorii
