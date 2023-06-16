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
IDAngajat int primary key,
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
IdSala int primary key,
NrLocuri int,
IdCinema int FOREIGN KEY REFERENCES Cinema(IdCinema)
)


create table Loc(
IdLoc int primary key,
Numar int,
Randd int,
IdSala int FOREIGN KEY REFERENCES Sala(IdSala)
)
