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

create table Film(
IdFilm int primary key,
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
IdUtilizator int primary key,
Nume varchar(50),
Parola varchar(50)
)

create table Rezervare(
IdFilm int foreign key references Film(IdFilm),
IdUtilizator int foreign key references Utilizator(IdUtilizator),
constraint pk_Rezervare primary key (IdFilm, IdUtilizator),
DataRezervare datetime
)

