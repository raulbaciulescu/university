create database ArtaCinematografica
go
use ArtaCinematografica
go

create table Cinema(
IDCinema int primary key identity,
Nume varchar(50), 
)


create table Manager(
IdManager int foreign key references Cinema(IdCinema),
Nume varchar(20),
Prenume varchar(20),
varsta int,
constraint pk_Manager primary key(IdManager)
)


create table Angajat(
IDAngajat int primary key identity,
Nume varchar(20),
Prenume varchar(20),
varsta int,
IdCinema int FOREIGN KEY REFERENCES Cinema(IdCinema)
)

create table Salariu(
IdSalariu int foreign key references Angajat(IdAngajat),
Suma int,
Moneda varchar(20),
constraint pk_Salariu primary key(IdSalariu)
)

create table Sala(
IdSala int primary key identity,
NrLocuri int, 
IdCinema int FOREIGN KEY REFERENCES Cinema(IdCinema)
)


create table Loc(
IdLoc int primary key identity,
Numar int,
Randd int,
IdSala int FOREIGN KEY REFERENCES Sala(IdSala)
)

create table Film(
IdFilm int primary key identity,
Nume varchar(50),
Durata int,
Limba varchar(50),
Dimensiune varchar(2) check (Dimensiune = '2D' or Dimensiune = '3D')
)

create table Proiectare(
IdFilm int foreign key references Film(IdFilm),
IdSala int foreign key references Sala(IdSala),
constraint pk_Proiectare primary key (IdFilm, IdSala),
DataProiectare datetime
)


create table Utilizator(
IdUtilizator int primary key identity,
Nume varchar(50),
Parola varchar(50)
)

create table Rezervare(
IdFilm int foreign key references Film(IdFilm),
IdUtilizator int foreign key references Utilizator(IdUtilizator),
constraint pk_Rezervare primary key (IdFilm, IdUtilizator),
DataRezervare datetime
)

select CONSTRAINT_NAME
from INFORMATION_SCHEMA.TABLE_CONSTRAINTS
where TABLE_NAME = 'Rezervare'


ALTER TABLE Rezervare
drop CONSTRAINT FK__Rezervare__IdFil__4F7CD00D;


ALTER TABLE Rezervare
drop CONSTRAINT FK__Rezervare__IdUti__5070F446;

ALTER TABLE Rezervare
ADD CONSTRAINT FK_IdFilm
    FOREIGN KEY (IdFilm)
    REFERENCES Film(IdFilm)
    ON DELETE CASCADE


ALTER TABLE Rezervare
ADD CONSTRAINT FK_IdUtilizator
    FOREIGN KEY (IdUtilizator)
    REFERENCES Utilizator(IdUtilizator)
    ON DELETE CASCADE



select CONSTRAINT_NAME
from INFORMATION_SCHEMA.TABLE_CONSTRAINTS
where TABLE_NAME = 'Loc'

ALTER TABLE Loc
drop CONSTRAINT FK__Loc__IdSala__440B1D61

ALTER TABLE Loc
ADD CONSTRAINT FK_IdSala
    FOREIGN KEY (IdSala)
    REFERENCES Sala(IdSala)
    ON DELETE CASCADE


select CONSTRAINT_NAME
from INFORMATION_SCHEMA.TABLE_CONSTRAINTS
where TABLE_NAME = 'Proiectare'


ALTER TABLE Proiectare
drop CONSTRAINT FK__Proiectar__IdFil__49C3F6B7
ALTER TABLE Proiectare
drop CONSTRAINT FK__Proiectar__IdSal__4AB81AF0


ALTER TABLE Proiectare
ADD CONSTRAINT FK_IdFilmProiectare
    FOREIGN KEY (IdFilm)
    REFERENCES Film(IdFilm)
    ON DELETE CASCADE

ALTER TABLE Proiectare
ADD CONSTRAINT FK_IdSalaroiectare
    FOREIGN KEY (IdSala)
    REFERENCES Sala(IdSala)
    ON DELETE CASCADE
