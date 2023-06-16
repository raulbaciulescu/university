-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 25, 2022 at 05:52 PM
-- Server version: 10.3.32-MariaDB
-- PHP Version: 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `aerpur_y50`
--

-- --------------------------------------------------------

--
-- Table structure for table `chestionar`
--

CREATE TABLE `chestionar` (
  `id` int(11) NOT NULL,
  `pacient_id` int(11) NOT NULL,
  `tip_chestionar` int(2) NOT NULL,
  `sumar` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chestionar`
--

INSERT INTO `chestionar` (`id`, `pacient_id`, `tip_chestionar`, `sumar`) VALUES
(91, 108, 1, '<br>Rezultat chestionar: <strong>Pacientul este incadrat in categoria: B2</strong><br><hr><table><tr><th>Stil de viata</th><th>Date medicale</th></tr><tr><td><strong>Compozitie corporala</strong><br>Circumferinta taliei: 100 >= 88<br>BMI = 26.989619377163 25 <= BMI < 27<br><strong>Obiceiuri alimentare</strong><br>Consuma peste cel putin o zi pe saptamana<br>Consuma nuci cel putin o zi pe saptamana<br>Nu consuma ulei de masline<br>Consuma 6fructe & legume - adecvat > 5<br><strong>Consum alcool</strong><br>Consum adecvat de alcool: 0.85714285714286<br><strong>Activitate fizica</strong><br>Activitate fizica adecvata: 450 minute<br><strong>Consum fumat</strong><br>fumator Nu foloseste tigari electronice <br>Nu foloseste produse cu tutun incalzit<br></td><td><br>Tensiunea arteriala este in parametri normali<br>Colesterolemia - valori normale: 210<br>Trigliceride - valori normale: 180<br>Glicemie valori normale100<br></td></tr></table>'),
(92, 109, 1, '<br>Rezultat chestionar: <strong>Pacientul este incadrat in categoria: C</strong><br><hr><table><tr><th>Stil de viata</th><th>Date medicale</th></tr><tr><td><strong>Compozitie corporala</strong><br>Circumferinta taliei: 88 <= 94<br>BMI = 24.898143956541 < 25<br><strong>Obiceiuri alimentare</strong><br>Nu consuma peste<br>Nu consuma nuci<br>Nu consuma ulei de masline<br>Consuma 0fructe & legume - insuficient < 3<br><strong>Consum alcool</strong><br>Consum adecvat de alcool: 0<br><strong>Activitate fizica</strong><br>Activitate fizica adecvata: 285 minute<br><strong>Consum fumat</strong><br>nefumatorNu foloseste tigari electronice <br>Nu foloseste produse cu tutun incalzit<br></td><td><br>Hipertensiune arteriala - Dia = 90<br>Colesterolemia - valori normale: 115<br>Trigliceride - valori normale: 115<br>Hiperglicemie (Random sau fasting): 115<br></td></tr></table>'),
(93, 108, 2, '<br>Rezultat chestionar: <strong>Pacientul este incadrat in categoria: C</strong><br><hr><table><tr><th>Stil de viata</th><th>Date medicale</th></tr><tr><td><strong>Compozitie corporala</strong><br>Circumferinta taliei: 77 <= 80<br>BMI = 23.951226593119 < 25<br><strong>Obiceiuri alimentare</strong><br>Nu consuma peste<br>Nu consuma nuci<br>Nu consuma ulei de masline<br>Consuma 0fructe & legume - insuficient < 3<br><strong>Consum alcool</strong><br>Consum adecvat de alcool: 0<br><strong>Activitate fizica</strong><br>Activitate fizica insuficienta: 0 minute<br><strong>Consum fumat</strong><br>nefumatorNu foloseste tigari electronice <br>Nu foloseste produse cu tutun incalzit<br></td><td><br>Hipertensiune arteriala - Dia = 90<br>Colesterolemia - valori normale: 115<br>Trigliceride - valori normale: 115<br>Hiperglicemie (Random sau fasting): 115<br></td></tr></table>'),
(94, 109, 2, '<br>Rezultat chestionar: <strong>Pacientul este incadrat in categoria: C</strong><br><hr><table><tr><th>Stil de viata</th><th>Date medicale</th></tr><tr><td><strong>Compozitie corporala</strong><br>Circumferinta taliei: 100 >= 88<br>BMI = 31.887755102041 >= 27<br><strong>Obiceiuri alimentare</strong><br>Nu consuma peste<br>Nu consuma nuci<br>Nu consuma ulei de masline<br>Consuma 2fructe & legume - insuficient < 3<br><strong>Consum alcool</strong><br>Consum mare de alcool: 2.1428571428571<br><strong>Activitate fizica</strong><br>Activitate fizica insuficienta: 90 minute<br><strong>Consum fumat</strong><br>nefumatorNu foloseste tigari electronice <br>Nu foloseste produse cu tutun incalzit<br></td><td><br>Tensiunea arteriala este in parametri normali<br>Colesterolemia - valori normale: 200<br>Hipertrigliceridemie: 216<br>Hiperglicemie (Random sau fasting): 110<br>Tratament colesterol<br></td></tr></table>'),
(95, 110, 1, '<br>Rezultat chestionar: <strong>Pacientul este incadrat in categoria: Nu este eligibil pentru chestionar</strong><br><hr><table><tr><th>Stil de viata</th><th>Date medicale</th></tr><tr><td></td><td>Incadrarea medicala a pacientului in categoria D - Diabet - Cancer - Inima</td></tr></table>'),
(96, 111, 1, '<br>Rezultat chestionar: <strong>Pacientul este incadrat in categoria: C</strong><br><hr><table><tr><th>Stil de viata</th><th>Date medicale</th></tr><tr><td><strong>Compozitie corporala</strong><br>Circumferinta taliei: 91 <= 94<br>BMI = 24.913494809689 < 25<br><strong>Obiceiuri alimentare</strong><br>Nu consuma peste<br>Nu consuma nuci<br>Consuma ulei de masline cel putin o zi pe saptamana<br>Consuma 3fructe & legume - moderat >= 3 <5<br><strong>Consum alcool</strong><br>Consum adecvat de alcool: 0<br><strong>Activitate fizica</strong><br>Activitate fizica adecvata: 540 minute<br><strong>Consum fumat</strong><br>nefumatorNu foloseste tigari electronice <br>Nu foloseste produse cu tutun incalzit<br></td><td><br>Tensiunea arteriala este in parametri normali<br>Hipercolesterolemie: 260<br>Hipertrigliceridemie: 332<br>Glicemie valori normale: 122<br></td></tr></table>'),
(97, 112, 1, '<br>Rezultat chestionar: <strong>Pacientul este incadrat in categoria: C</strong><br><hr><table><tr><th>Stil de viata</th><th>Date medicale</th></tr><tr><td><strong>Compozitie corporala</strong><br>Circumferinta taliei: 89 >= 88<br>BMI = 24.919900320399 < 25<br><strong>Obiceiuri alimentare</strong><br>Nu consuma peste<br>Nu consuma nuci<br>Consuma ulei de masline cel putin o zi pe saptamana<br>Consuma 3fructe & legume - moderat >= 3 <5<br><strong>Consum alcool</strong><br>Consum adecvat de alcool: 0<br><strong>Activitate fizica</strong><br>Activitate fizica adecvata: 435 minute<br><strong>Consum fumat</strong><br>nefumatorNu foloseste tigari electronice <br>Nu foloseste produse cu tutun incalzit<br></td><td><br>Tensiunea arteriala este in parametri normali<br>Colesterolemia - valori normale: 181<br>Trigliceride - valori normale: 123<br>Hiperglicemie (Post prandial): 142<br></td></tr></table>'),
(98, 113, 1, '<br>Rezultat chestionar: <strong>Pacientul este incadrat in categoria: C</strong><br><hr><table><tr><th>Stil de viata</th><th>Date medicale</th></tr><tr><td><strong>Compozitie corporala</strong><br>Circumferinta taliei: 89 >= 88<br>BMI = 24.919900320399 < 25<br><strong>Obiceiuri alimentare</strong><br>Nu consuma peste<br>Nu consuma nuci<br>Consuma ulei de masline cel putin o zi pe saptamana<br>Consuma 3fructe & legume - moderat >= 3 <5<br><strong>Consum alcool</strong><br>Consum adecvat de alcool: 0<br><strong>Activitate fizica</strong><br>Activitate fizica adecvata: 435 minute<br><strong>Consum fumat</strong><br>nefumatorNu foloseste tigari electronice <br>Nu foloseste produse cu tutun incalzit<br></td><td><br>Tensiunea arteriala este in parametri normali<br>Colesterolemia - valori normale: 181<br>Trigliceride - valori normale: 123<br>Hiperglicemie (Post prandial): 142<br></td></tr></table>'),
(99, 114, 1, '<br>Rezultat chestionar: <strong>Pacientul este incadrat in categoria: B2</strong><br><hr><table><tr><th>Stil de viata</th><th>Date medicale</th></tr><tr><td><strong>Compozitie corporala</strong><br>Circumferinta taliei: 121 >= 102<br>BMI = 33.412964876033 >= 27<br><strong>Obiceiuri alimentare</strong><br>Nu consuma peste<br>Consuma nuci cel putin o zi pe saptamana<br>Nu consuma ulei de masline<br>Consuma 2fructe & legume - insuficient < 3<br><strong>Consum alcool</strong><br>Consum adecvat de alcool: 0.28571428571429<br><strong>Activitate fizica</strong><br>Activitate fizica adecvata: 150 minute<br><strong>Consum fumat</strong><br>nefumatorNu foloseste tigari electronice <br>Nu foloseste produse cu tutun incalzit<br></td><td><br>Tensiunea arteriala este in parametri normali<br>Colesterolemia - valori normale: 192<br>Trigliceride - valori normale: 101<br>Glicemie valori normale90<br></td></tr></table>'),
(100, 115, 1, '<br>Rezultat chestionar: <strong>Pacientul este incadrat in categoria: B2</strong><br><hr><table><tr><th>Stil de viata</th><th>Date medicale</th></tr><tr><td><strong>Compozitie corporala</strong><br>Circumferinta taliei: 121 >= 102<br>BMI = 33.412964876033 >= 27<br><strong>Obiceiuri alimentare</strong><br>Nu consuma peste<br>Consuma nuci cel putin o zi pe saptamana<br>Nu consuma ulei de masline<br>Consuma 2fructe & legume - insuficient < 3<br><strong>Consum alcool</strong><br>Consum adecvat de alcool: 0.28571428571429<br><strong>Activitate fizica</strong><br>Activitate fizica adecvata: 150 minute<br><strong>Consum fumat</strong><br>nefumatorNu foloseste tigari electronice <br>Nu foloseste produse cu tutun incalzit<br></td><td><br>Tensiunea arteriala este in parametri normali<br>Colesterolemia - valori normale: 192<br>Trigliceride - valori normale: 101<br>Glicemie valori normale90<br></td></tr></table>'),
(101, 116, 1, '<br>Rezultat chestionar: <strong>Pacientul este incadrat in categoria: C</strong><br><hr><table><tr><th>Stil de viata</th><th>Date medicale</th></tr><tr><td><strong>Compozitie corporala</strong><br>Circumferinta taliei: 130 >= 102<br>BMI = 40.138408304498 >= 27<br><strong>Obiceiuri alimentare</strong><br>Nu consuma peste<br>Consuma nuci cel putin o zi pe saptamana<br>Nu consuma ulei de masline<br>Consuma 5fructe & legume - adecvat > 5<br><strong>Consum alcool</strong><br>Consum adecvat de alcool: 1.2857142857143<br><strong>Activitate fizica</strong><br>Activitate fizica adecvata: 450 minute<br><strong>Consum fumat</strong><br>nefumatorNu foloseste tigari electronice <br>Nu foloseste produse cu tutun incalzit<br></td><td><br>Hipertensiune arteriala - Sys = 144<br>Colesterolemia - valori normale: 228<br>Trigliceride - valori normale: 188<br>Glicemie valori normale: 86<br></td></tr></table>'),
(102, 117, 1, '<br>Rezultat chestionar: <strong>Pacientul este incadrat in categoria: B2</strong><br><hr><table><tr><th>Stil de viata</th><th>Date medicale</th></tr><tr><td><strong>Compozitie corporala</strong><br>Circumferinta taliei: 66 <= 80<br>BMI = 21.504469556418 < 25<br><strong>Obiceiuri alimentare</strong><br>Nu consuma peste<br>Consuma nuci cel putin o zi pe saptamana<br>Nu consuma ulei de masline<br>Consuma 0.57142857142857fructe & legume - insuficient < 3<br><strong>Consum alcool</strong><br>Consum adecvat de alcool: 0.14285714285714<br><strong>Activitate fizica</strong><br>Activitate fizica adecvata: 525 minute<br><strong>Consum fumat</strong><br>nefumatorNu foloseste tigari electronice <br>Nu foloseste produse cu tutun incalzit<br></td><td><br>Tensiunea arteriala este in parametri normali<br>Colesterolemia - valori normale: 203<br>Trigliceride - valori normale: 188<br>Glicemie valori normale93<br></td></tr></table>'),
(103, 118, 1, '<br>Rezultat chestionar: <strong>Pacientul este incadrat in categoria: C</strong><br><hr><table><tr><th>Stil de viata</th><th>Date medicale</th></tr><tr><td><strong>Compozitie corporala</strong><br>Circumferinta taliei: 97 > 94 < 102<br>BMI = 26.038378992551 25 <= BMI < 27<br><strong>Obiceiuri alimentare</strong><br>Consuma peste cel putin o zi pe saptamana<br>Consuma nuci cel putin o zi pe saptamana<br>Consuma ulei de masline cel putin o zi pe saptamana<br>Consuma 1.7142857142857fructe & legume - insuficient < 3<br><strong>Consum alcool</strong><br>Consum adecvat de alcool: 1<br><strong>Activitate fizica</strong><br>Activitate fizica adecvata: 765 minute<br><strong>Consum fumat</strong><br>nefumatorNu foloseste tigari electronice <br>Nu foloseste produse cu tutun incalzit<br></td><td><br>Hipertensiune arteriala - Sys = 148; Dia = 102<br>Colesterolemia - valori normale: 191<br>Trigliceride - valori normale: 104<br>Glicemie valori normale95<br></td></tr></table>'),
(104, 119, 1, '<br>Rezultat chestionar: <strong>Pacientul este incadrat in categoria: C</strong><br><hr><table><tr><th>Stil de viata</th><th>Date medicale</th></tr><tr><td><strong>Compozitie corporala</strong><br>Circumferinta taliei: 140 >= 102<br>BMI = 45.764423683878 >= 27<br><strong>Obiceiuri alimentare</strong><br>Nu consuma peste<br>Consuma nuci cel putin o zi pe saptamana<br>Consuma ulei de masline cel putin o zi pe saptamana<br>Consuma 1.4285714285714fructe & legume - insuficient < 3<br><strong>Consum alcool</strong><br>Consum adecvat de alcool: 0.14285714285714<br><strong>Activitate fizica</strong><br>Activitate fizica adecvata: 600 minute<br><strong>Consum fumat</strong><br>nefumatorNu foloseste tigari electronice <br>Nu foloseste produse cu tutun incalzit<br></td><td><br>Hipertensiune arteriala - Sys = 169; Dia = 114<br>Colesterolemia - valori normale: 151<br>Hipertrigliceridemie: 283<br>Glicemie valori normale104<br></td></tr></table>'),
(105, 120, 1, '<br>Rezultat chestionar: <strong>Pacientul este incadrat in categoria: B2</strong><br><hr><table><tr><th>Stil de viata</th><th>Date medicale</th></tr><tr><td><strong>Compozitie corporala</strong><br>Circumferinta taliei: 81 >80 <88<br>BMI = 24.34175828634 < 25<br><strong>Obiceiuri alimentare</strong><br>Nu consuma peste<br>Consuma nuci cel putin o zi pe saptamana<br>Consuma ulei de masline cel putin o zi pe saptamana<br>Consuma 2fructe & legume - insuficient < 3<br><strong>Consum alcool</strong><br>Consum adecvat de alcool: 0<br><strong>Activitate fizica</strong><br>Activitate fizica adecvata: 600 minute<br><strong>Consum fumat</strong><br>nefumatorNu foloseste tigari electronice <br>Nu foloseste produse cu tutun incalzit<br></td><td><br>Tensiunea arteriala este in parametri normali<br>Colesterolemia - valori normale: 171<br>Trigliceride - valori normale: 165<br>Glicemie valori normale: 131<br></td></tr></table>'),
(106, 121, 1, '<br>Rezultat chestionar: <strong>Pacientul este incadrat in categoria: C</strong><br><hr><table><tr><th>Stil de viata</th><th>Date medicale</th></tr><tr><td><strong>Compozitie corporala</strong><br>Circumferinta taliei: 106 >= 88<br>BMI = 31.163708086785 >= 27<br><strong>Obiceiuri alimentare</strong><br>Nu consuma peste<br>Nu consuma nuci<br>Nu consuma ulei de masline<br>Consuma 2fructe & legume - insuficient < 3<br><strong>Consum alcool</strong><br>Consum adecvat de alcool: 0.57142857142857<br><strong>Activitate fizica</strong><br>Activitate fizica adecvata: 840 minute<br><strong>Consum fumat</strong><br>nefumatorNu foloseste tigari electronice <br>Nu foloseste produse cu tutun incalzit<br></td><td><br>Tensiunea arteriala este in parametri normali<br>Hipercolesterolemie: 243<br>Trigliceride - valori normale: 112<br>Glicemie valori normale: 123<br></td></tr></table>');

-- --------------------------------------------------------

--
-- Table structure for table `monitorizare`
--

CREATE TABLE `monitorizare` (
  `id` int(11) NOT NULL,
  `pacient_id` int(11) NOT NULL,
  `categorie_risc` varchar(255) NOT NULL,
  `scrisoare_evaluare` varchar(255) NOT NULL,
  `data_scrisoare` datetime DEFAULT NULL,
  `invitat_stil` varchar(255) NOT NULL,
  `observatii_invitatie_stil` varchar(255) NOT NULL,
  `participare_stil` varchar(255) NOT NULL,
  `observatii_paticipare_stil` varchar(255) NOT NULL,
  `invitat_greutate` varchar(255) NOT NULL,
  `observatii_invitat_greutate` varchar(255) NOT NULL,
  `participare_greutate` varchar(255) NOT NULL,
  `observatii_participare_greutate` varchar(255) NOT NULL,
  `invitat_fumat` varchar(255) NOT NULL,
  `observatii_invitat_fumat` varchar(255) NOT NULL,
  `participat_fumat` varchar(255) NOT NULL,
  `observatii_participat_fumat` varchar(255) NOT NULL,
  `directionat_medic` varchar(255) NOT NULL,
  `observatii_directionat_medic` varchar(255) NOT NULL,
  `mers_medic` varchar(255) NOT NULL,
  `observatii_mers_medic` varchar(255) NOT NULL,
  `scrisoare_medic` varchar(255) NOT NULL,
  `observatii_scrisoare_medic` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `monitorizare`
--

INSERT INTO `monitorizare` (`id`, `pacient_id`, `categorie_risc`, `scrisoare_evaluare`, `data_scrisoare`, `invitat_stil`, `observatii_invitatie_stil`, `participare_stil`, `observatii_paticipare_stil`, `invitat_greutate`, `observatii_invitat_greutate`, `participare_greutate`, `observatii_participare_greutate`, `invitat_fumat`, `observatii_invitat_fumat`, `participat_fumat`, `observatii_participat_fumat`, `directionat_medic`, `observatii_directionat_medic`, `mers_medic`, `observatii_mers_medic`, `scrisoare_medic`, `observatii_scrisoare_medic`) VALUES
(27, 108, 'b2', '0', NULL, '0', '', '0', '', '0', '', '0', '', '0', '', '0', '', '0', '', '0', '', '1', ''),
(28, 109, 'c', '0', NULL, '0', '', '1', '', '0', '', '', '', '', '', '', '', '', '', '', '', '', ''),
(29, 110, '', '', NULL, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''),
(30, 111, '', '', NULL, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''),
(31, 112, '', '', NULL, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''),
(32, 113, '', '', NULL, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''),
(33, 114, '', '', NULL, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''),
(34, 115, '', '', NULL, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''),
(35, 116, '', '', NULL, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''),
(36, 117, '', '', NULL, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''),
(37, 118, '', '', NULL, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''),
(38, 119, '', '', NULL, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''),
(39, 120, '', '', NULL, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''),
(40, 121, '', '', NULL, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `pacient`
--

CREATE TABLE `pacient` (
  `id` int(11) NOT NULL,
  `nume` varchar(255) NOT NULL,
  `prenume` varchar(255) NOT NULL,
  `inscris_de` int(11) NOT NULL,
  `data_inscriere` datetime DEFAULT NULL,
  `chestionar1_categorie` varchar(255) NOT NULL,
  `chestionar2_categorie` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pacient`
--

INSERT INTO `pacient` (`id`, `nume`, `prenume`, `inscris_de`, `data_inscriere`, `chestionar1_categorie`, `chestionar2_categorie`) VALUES
(108, 'ilies', 'marina', 46, '2021-11-17 00:41:35', 'B2', 'C'),
(109, 'test', 'test', 46, '2022-04-07 14:43:34', 'C', 'C'),
(110, 'Albert', 'Sanda', 49, '2022-07-25 09:13:54', 'Nu este eligibil pentru chestionar', ''),
(111, 'Baraian', 'Danut', 49, '2022-07-25 10:04:54', 'C', ''),
(112, 'Baraian', 'Fica', 49, '2022-07-25 10:24:39', 'C', ''),
(113, 'Baraian', 'Fica', 49, '2022-07-25 10:24:41', 'C', ''),
(114, 'Florea', 'Ioan', 49, '2022-07-25 10:38:24', 'B2', ''),
(115, 'Florea', 'Ioan', 49, '2022-07-25 10:38:24', 'B2', ''),
(116, 'Laslou', 'Dumitru Dan', 49, '2022-07-25 10:58:37', 'C', ''),
(117, 'Mocan', 'Daniela', 49, '2022-07-25 11:01:20', 'B2', ''),
(118, 'Nicoara', 'Daniel Timotei', 49, '2022-07-25 11:45:04', 'C', ''),
(119, 'Verba', 'Andras', 49, '2022-07-25 12:00:32', 'C', ''),
(120, 'Laslou', 'Virginia', 49, '2022-07-25 12:11:17', 'B2', ''),
(121, 'Kramer', 'Helmuta', 49, '2022-07-25 12:21:55', 'C', '');

-- --------------------------------------------------------

--
-- Table structure for table `pacient_date_alimentatie`
--

CREATE TABLE `pacient_date_alimentatie` (
  `id` int(11) NOT NULL,
  `pacient_id` int(11) NOT NULL,
  `chestionar_id` int(11) NOT NULL,
  `obiceiuri_alimentare` varchar(255) NOT NULL,
  `consum_saptamana_fructe_legume` varchar(255) NOT NULL,
  `consumat_saptamana_portii_fructe_legume` varchar(255) NOT NULL,
  `consum_zi_portii_fructe_legume` varchar(255) NOT NULL,
  `opinie_consum_saptamana_fructe_legume` varchar(255) NOT NULL,
  `consum_preconizat_fructe_legume` varchar(255) NOT NULL,
  `incredere_consum_fructe_legume` varchar(255) NOT NULL,
  `consum_lapte_iaurt` varchar(255) NOT NULL,
  `consum_branza` varchar(255) NOT NULL,
  `consum_smantana_frisca` varchar(255) NOT NULL,
  `consum_unt` varchar(255) NOT NULL,
  `consum_margarina` varchar(255) NOT NULL,
  `consum_carne_rosie` varchar(255) NOT NULL,
  `consum_pui` varchar(255) NOT NULL,
  `consum_peste` varchar(255) NOT NULL,
  `consum_mezeluri` varchar(255) NOT NULL,
  `consum_oua` varchar(255) NOT NULL,
  `consum_slanina` varchar(255) NOT NULL,
  `consum_ulei_floarea_soarelui` varchar(255) NOT NULL,
  `consum_ulei_masline` varchar(255) NOT NULL,
  `consum_nuci` varchar(255) NOT NULL,
  `consum_paine_alba` varchar(255) NOT NULL,
  `consum_paine_neagra` varchar(255) NOT NULL,
  `consum_orez` varchar(255) NOT NULL,
  `consum_cartofi` varchar(255) NOT NULL,
  `consum_fasole` varchar(255) NOT NULL,
  `consum_chipsuri` varchar(255) NOT NULL,
  `consum_patiserie` varchar(255) NOT NULL,
  `consum_dulciuri` varchar(255) NOT NULL,
  `consum_sucuri` varchar(255) NOT NULL,
  `consum_piele_pui` varchar(255) NOT NULL,
  `analiza_grasimi_consumate` varchar(255) NOT NULL,
  `actiuni_consum_grasimi` varchar(255) NOT NULL,
  `analiza_sare_consumata` varchar(255) NOT NULL,
  `actiuni_consum_sare` varchar(255) NOT NULL,
  `consum_alcool` varchar(255) NOT NULL,
  `consumat_portii_alcool` varchar(255) NOT NULL,
  `mic_dejun` varchar(255) NOT NULL,
  `somn_regulat` varchar(255) NOT NULL,
  `sfaturi_specialisti` varchar(255) NOT NULL,
  `utilizare_internet` varchar(255) NOT NULL,
  `informatii_internet` varchar(255) NOT NULL,
  `analize_medicale_recente` varchar(255) NOT NULL,
  `suplimente_nutritive` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pacient_date_alimentatie`
--

INSERT INTO `pacient_date_alimentatie` (`id`, `pacient_id`, `chestionar_id`, `obiceiuri_alimentare`, `consum_saptamana_fructe_legume`, `consumat_saptamana_portii_fructe_legume`, `consum_zi_portii_fructe_legume`, `opinie_consum_saptamana_fructe_legume`, `consum_preconizat_fructe_legume`, `incredere_consum_fructe_legume`, `consum_lapte_iaurt`, `consum_branza`, `consum_smantana_frisca`, `consum_unt`, `consum_margarina`, `consum_carne_rosie`, `consum_pui`, `consum_peste`, `consum_mezeluri`, `consum_oua`, `consum_slanina`, `consum_ulei_floarea_soarelui`, `consum_ulei_masline`, `consum_nuci`, `consum_paine_alba`, `consum_paine_neagra`, `consum_orez`, `consum_cartofi`, `consum_fasole`, `consum_chipsuri`, `consum_patiserie`, `consum_dulciuri`, `consum_sucuri`, `consum_piele_pui`, `analiza_grasimi_consumate`, `actiuni_consum_grasimi`, `analiza_sare_consumata`, `actiuni_consum_sare`, `consum_alcool`, `consumat_portii_alcool`, `mic_dejun`, `somn_regulat`, `sfaturi_specialisti`, `utilizare_internet`, `informatii_internet`, `analize_medicale_recente`, `suplimente_nutritive`) VALUES
(65, 108, 1, '3', '7', '6', '5', '1', '1', '1', '0', '1', '2', '0', '1', '2', '0', '1', '2', '0', '1', '2', '0', '1', '2', '0', '1', '2', '0', '1', '2', '0', '1', '1', '1', '1,2,3,4,5,6,7,', '1', '1,2,3,4,', '6', '1', '1', '1', '0,2,', '0', '', '0,2,', '1,2,'),
(66, 109, 1, '1', '0', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '', '0', '', '0', '0', '1', '0', '3,', '0', '', '0,', '0,'),
(67, 108, 2, '1', '0', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '', '0', '', '0', '0', '1', '0', '2,', '0', '', '0,', '0,'),
(68, 109, 2, '1', '7', '2', '3', '1', '1', '1', '3', '0', '0', '0', '2', '0', '0', '0', '2', '0', '3', '0', '0', '0', '1', '1', '1', '3', '3', '3', '2', '3', '2', '1', '1', '2,3,', '1', '2,3,', '5', '3', '3', '1', '0,3,', '0', '', '5,', '0,'),
(69, 110, 1, '3', '7', '2', '2', '4', '1', '1', '4', '4', '1', '1', '0', '2', '1', '0', '1', '1', '1', '4', '3', '2', '4', '0', '2', '1', '1', '0', '0', '4', '1', '1', '1', '2,', '0', '', '0', '0', '1', '0', '6,', '1', '0,1,', '5,', '1,2,'),
(70, 111, 1, '3', '7', '3', '4', '4', '3', '2', '4', '1', '0', '0', '0', '1', '0', '0', '1', '1', '0', '1', '1', '0', '1', '0', '0', '0', '0', '0', '2', '1', '0', '2', '1', '2,', '0', '', '0', '0', '3', '1', '0,1,', '1', '6,', '0,1,2,', ''),
(71, 112, 1, '3', '7', '3', '4', '1', '3', '2', '4', '1', '0', '0', '0', '1', '0', '0', '1', '1', '1', '1', '1', '0', '1', '0', '0', '0', '0', '0', '1', '1', '0', '2', '1', '2,', '1', '1,', '0', '0', '1', '1', '6,', '1', '0,1,2,5,', '0,2,', '1,'),
(72, 113, 1, '3', '7', '3', '4', '1', '3', '2', '4', '1', '0', '0', '0', '1', '0', '0', '1', '1', '1', '1', '1', '0', '1', '0', '0', '0', '0', '0', '1', '1', '0', '2', '1', '2,', '1', '1,', '0', '0', '1', '1', '6,', '1', '0,1,2,5,', '0,2,', '1,'),
(73, 114, 1, '3', '7', '2', '3', '1', '1', '2', '1', '1', '1', '0', '0', '2', '1', '0', '0', '0', '0', '0', '0', '2', '4', '1', '1', '0', '0', '1', '1', '0', '0', '1', '1', '2,3,', '1', '1,', '2', '1', '1', '1', '0,1,2,5,', '1', '6,', '0,1,2,', '0,'),
(74, 115, 1, '3', '7', '2', '3', '1', '1', '2', '1', '1', '1', '0', '0', '2', '1', '0', '0', '0', '0', '0', '0', '2', '4', '1', '1', '0', '0', '1', '1', '0', '0', '1', '1', '2,3,', '1', '1,', '2', '1', '1', '1', '0,1,2,5,', '1', '6,', '0,1,2,', '0,'),
(75, 116, 1, '3', '7', '5', '2', '2', '3', '1', '0', '1', '0', '1', '0', '4', '0', '0', '1', '2', '0', '1', '0', '1', '4', '1', '1', '0', '0', '1', '1', '4', '0', '2', '0', '', '0', '', '3', '3', '4', '1', '0,1,2,', '1', '0,', '0,2,', '0,'),
(76, 117, 1, '3', '2', '2', '1', '2', '3', '2', '1', '1', '0', '1', '0', '1', '1', '0', '1', '1', '1', '1', '0', '1', '0', '4', '0', '0', '0', '0', '4', '1', '1', '1', '0', '', '0', '', '1', '1', '1', '0', '6,', '1', '0,1,', '2,', '1,'),
(77, 118, 1, '3', '3', '4', '2', '2', '3', '2', '3', '4', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '1', '4', '0', '4', '1', '1', '1', '0', '1', '1', '0', '3', '1', '1,', '1', '1,', '7', '1', '1', '1', '6,', '1', '6,', '1,2,', '1,'),
(78, 119, 1, '3', '5', '2', '4', '2', '3', '2', '0', '0', '1', '1', '0', '4', '2', '0', '4', '1', '0', '4', '1', '1', '0', '4', '0', '1', '0', '1', '3', '2', '0', '1', '0', '', '1', '1,', '1', '1', '2', '1', '0,1,2,', '1', '6,', '0,1,2,', '1,'),
(79, 120, 1, '3', '7', '2', '3', '2', '3', '2', '1', '2', '1', '1', '1', '2', '1', '0', '1', '1', '0', '4', '2', '1', '0', '1', '2', '1', '0', '0', '0', '2', '0', '2', '0', '', '0', '', '0', '0', '1', '0', '6,', '1', '0,1,2,', '0,1,2,3,', '1,'),
(80, 121, 1, '3', '7', '2', '3', '2', '1', '2', '0', '1', '1', '0', '0', '0', '1', '0', '0', '0', '0', '1', '0', '0', '2', '0', '0', '0', '0', '0', '0', '1', '0', '2', '0', '', '0', '', '4', '1', '4', '0', '6,', '1', '6,', '2,', '0,');

-- --------------------------------------------------------

--
-- Table structure for table `pacient_date_diverse`
--

CREATE TABLE `pacient_date_diverse` (
  `id` int(11) NOT NULL,
  `pacient_id` int(11) NOT NULL,
  `chestionar_id` int(11) NOT NULL,
  `menopauza` varchar(255) NOT NULL,
  `greutatea` varchar(255) NOT NULL,
  `inaltime` varchar(255) NOT NULL,
  `talie` varchar(255) NOT NULL,
  `BMI` varchar(255) NOT NULL,
  `covid` varchar(255) NOT NULL,
  `covid_fumat` varchar(255) NOT NULL,
  `covid_fumat_electronic` varchar(255) NOT NULL,
  `covid_fumat_IQOS` varchar(255) NOT NULL,
  `covid_alimentatie_fastfood` varchar(255) NOT NULL,
  `covid_alimentatie_gatitcasa` varchar(255) NOT NULL,
  `covid_alimentatie_gatitcumparat` varchar(255) NOT NULL,
  `covid_alimentatie_cartofi` varchar(255) NOT NULL,
  `covid_alimentatie_legumefructe_proaspete` varchar(255) NOT NULL,
  `covid_alimentatie_legumefructe_conserva` varchar(255) NOT NULL,
  `covid_alimentatie_lactate` varchar(255) NOT NULL,
  `covid_alimentatie_carnepui` varchar(255) NOT NULL,
  `covid_alimentatie_carnepeste` varchar(255) NOT NULL,
  `covid_alimentatie_carneporcvita` varchar(255) NOT NULL,
  `covid_alimentatie_mezeluri` varchar(255) NOT NULL,
  `covid_alimentatie_dulciuri` varchar(255) NOT NULL,
  `covid_alimentatie_bauturicarbo` varchar(255) NOT NULL,
  `covid_alimentatie_alcoolice` varchar(255) NOT NULL,
  `covid_alimentatie_orez` varchar(255) NOT NULL,
  `covid_alimentatie_paine` varchar(255) NOT NULL,
  `sfaturi_comportament` varchar(255) NOT NULL,
  `sfaturi_comportament_primite` varchar(255) NOT NULL,
  `materiale_promotionale` varchar(255) NOT NULL,
  `materiale_promotionale_primite` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pacient_date_diverse`
--

INSERT INTO `pacient_date_diverse` (`id`, `pacient_id`, `chestionar_id`, `menopauza`, `greutatea`, `inaltime`, `talie`, `BMI`, `covid`, `covid_fumat`, `covid_fumat_electronic`, `covid_fumat_IQOS`, `covid_alimentatie_fastfood`, `covid_alimentatie_gatitcasa`, `covid_alimentatie_gatitcumparat`, `covid_alimentatie_cartofi`, `covid_alimentatie_legumefructe_proaspete`, `covid_alimentatie_legumefructe_conserva`, `covid_alimentatie_lactate`, `covid_alimentatie_carnepui`, `covid_alimentatie_carnepeste`, `covid_alimentatie_carneporcvita`, `covid_alimentatie_mezeluri`, `covid_alimentatie_dulciuri`, `covid_alimentatie_bauturicarbo`, `covid_alimentatie_alcoolice`, `covid_alimentatie_orez`, `covid_alimentatie_paine`, `sfaturi_comportament`, `sfaturi_comportament_primite`, `materiale_promotionale`, `materiale_promotionale_primite`) VALUES
(61, 108, 1, '0', '78', '170', '100', '26.989619377163', '2', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', 'fumat', '0', ''),
(62, 109, 1, '2', '88', '188', '88', '24.898143956541', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '', '0', ''),
(63, 108, 2, '0', '66', '166', '77', '23.951226593119', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '', '0', ''),
(64, 109, 2, '1', '90', '168', '100', '31.887755102041', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '1', 'sfarturi generale', '0', ''),
(65, 110, 1, '1', '72', '165', '93', '', '0', '0', '0', '0', '0', '0', '0', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', 'Consum legume fructe', '1', 'Pliante '),
(66, 111, 1, '2', '72', '170', '91', '24.913494809689', '1', '0', '0', '0', '0', '1', '0', '1', '0', '0', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '1', 'Consum legume fructe', '1', 'Pliante'),
(67, 112, 1, '1', '63', '159', '89', '24.919900320399', '1', '0', '0', '0', '0', '1', '0', '1', '0', '1', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '1', 'Consum legume fructe sare grasimi', '1', 'Legume fructe sare grasimi activitate fizica'),
(68, 113, 1, '1', '63', '159', '89', '24.919900320399', '1', '0', '0', '0', '0', '1', '0', '1', '0', '1', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '1', 'Consum legume fructe sare grasimi', '1', 'Legume fructe sare grasimi activitate fizica'),
(69, 114, 1, '2', '103.5', '176', '121', '33.412964876033', '1', '0', '0', '0', '1', '1', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '1', 'Legume fructe sare grasimi sport', '1', 'Legume fructe sare grasimi sport'),
(70, 115, 1, '2', '103.5', '176', '121', '33.412964876033', '1', '0', '0', '0', '1', '1', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '1', 'Legume fructe sare grasimi sport', '1', 'Legume fructe sare grasimi sport'),
(71, 116, 1, '2', '116', '170', '130', '40.138408304498', '1', '0', '0', '0', '3', '1', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '1', '3', '1', 'Legume fructe sare grasimi sport', '1', 'Legume fructe sare grasimi sport'),
(72, 117, 1, '0', '51', '154', '66', '21.504469556418', '0', '0', '0', '0', '0', '1', '3', '1', '1', '3', '3', '0', '0', '1', '3', '3', '3', '3', '0', '0', '1', 'Fructe legume sport ', '1', 'Fructe legume activitÄƒÈ›i fizice '),
(73, 118, 1, '2', '82.5', '178', '97', '26.038378992551', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', 'Legume fructe sare grasimi sport', '1', 'Legume fructe sare grasimi sport'),
(74, 119, 1, '2', '145', '178', '140', '45.764423683878', '1', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', 'Legume fructe sare grasimi sport', '1', 'Legume fructe sare grasimi sport'),
(75, 120, 1, '0', '60', '157', '81', '24.34175828634', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', 'Legum fructe sare grasimi sport', '1', 'Legume fructe sare grasimi sport'),
(76, 121, 1, '2', '118.5', '195', '106', '31.163708086785', '1', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', 'Legume fructe sare grasimi sport', '1', 'Legume fructe sare grasimi sport');

-- --------------------------------------------------------

--
-- Table structure for table `pacient_date_medicale`
--

CREATE TABLE `pacient_date_medicale` (
  `id` int(11) NOT NULL,
  `pacient_id` int(11) NOT NULL,
  `chestionar_id` int(2) NOT NULL,
  `puls` varchar(255) NOT NULL,
  `tulburari_ritm` varchar(255) NOT NULL,
  `sys` varchar(255) NOT NULL,
  `dia` varchar(255) NOT NULL,
  `tratament_medical_presiune` varchar(255) NOT NULL,
  `blood_sugar` varchar(255) NOT NULL,
  `moment_blood_sugar` varchar(255) NOT NULL,
  `tratament_blood_sugar` varchar(255) NOT NULL,
  `colesterol` varchar(255) NOT NULL,
  `colesterol_HDL` varchar(255) NOT NULL,
  `colesterol_LDL` varchar(255) NOT NULL,
  `tratament_medical_colesterol` varchar(255) NOT NULL,
  `trigliceride` varchar(255) NOT NULL,
  `tratament_medical_trigliceride` varchar(255) NOT NULL,
  `diabet` varchar(255) NOT NULL,
  `cancer` varchar(255) NOT NULL,
  `inima` varchar(255) NOT NULL,
  `neuro` varchar(255) NOT NULL,
  `psihiatric` varchar(255) NOT NULL,
  `renala` varchar(255) NOT NULL,
  `alte_boli` text NOT NULL,
  `boli_familie` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pacient_date_medicale`
--

INSERT INTO `pacient_date_medicale` (`id`, `pacient_id`, `chestionar_id`, `puls`, `tulburari_ritm`, `sys`, `dia`, `tratament_medical_presiune`, `blood_sugar`, `moment_blood_sugar`, `tratament_blood_sugar`, `colesterol`, `colesterol_HDL`, `colesterol_LDL`, `tratament_medical_colesterol`, `trigliceride`, `tratament_medical_trigliceride`, `diabet`, `cancer`, `inima`, `neuro`, `psihiatric`, `renala`, `alte_boli`, `boli_familie`) VALUES
(98, 108, 1, '70', 'nu', '120', '70', '0', '100', '0', '0', '210', '', '', '0', '180', '0', '0', '0', '0', '0', '0', '0', 'nu', '0,2,'),
(99, 109, 1, '88', 'nu', '110', '90', '0', '115', '0', '0', '115', '115', '115', '0', '115', '0', '0', '0', '0', '0', '0', '0', 'nu', '0,2,'),
(100, 108, 2, '80', 'nu', '120', '90', '0', '115', '0', '0', '115', '115', '115', '0', '115', '0', '0', '0', '0', '0', '0', '0', 'as', '0,'),
(101, 109, 2, '70', '', '120', '80', '0', '110', '2', '0', '200', '', '', '1', '216', '0', '0', '0', '0', '0', '0', '0', 'nu', '1,2,'),
(102, 110, 1, '66', '', '107', '68', '1', '106', '1', '0', '207', '', '', '0', '175', '0', '0', '0', '1', '0', '1', '0', 'Nu', '2,'),
(103, 111, 1, '79', '', '133', '82', '0', '122', '1', '0', '260', '', '', '0', '332', '0', '0', '0', '0', '0', '0', '0', 'Nu', '1,4,'),
(104, 112, 1, '66', '', '106', '75', '0', '142', '1', '0', '181', '', '', '0', '123', '0', '0', '0', '0', '0', '0', '0', 'Nu', '0,1,3,'),
(105, 113, 1, '66', '', '106', '75', '0', '142', '1', '0', '181', '', '', '0', '123', '0', '0', '0', '0', '0', '0', '0', 'Nu', '0,1,3,'),
(106, 114, 1, '80', '', '121', '72', '0', '90', '2', '0', '192', '', '', '0', '101', '0', '0', '0', '0', '0', '0', '0', 'Nu', '0,1,'),
(107, 115, 1, '80', '', '121', '72', '0', '90', '2', '0', '192', '', '', '0', '101', '0', '0', '0', '0', '0', '0', '0', 'Nu', '0,1,'),
(108, 116, 1, '87', '', '144', '83', '0', '86', '1', '0', '228', '', '', '0', '188', '0', '0', '0', '0', '0', '0', '0', 'Nu', '0,1,'),
(109, 117, 1, '99', '', '129', '89', '0', '93', '2', '0', '203', '', '', '0', '188', '0', '0', '0', '0', '0', '0', '0', 'Nu', ''),
(110, 118, 1, '74', '', '148', '102', '0', '95', '2', '0', '191', '', '', '0', '104', '0', '0', '0', '0', '0', '0', '0', 'Nu', '1,2,4,'),
(111, 119, 1, '85', '', '169', '114', '0', '104', '2', '0', '151', '', '', '0', '283', '1', '0', '0', '0', '0', '0', '0', 'Nu', '0,1,4,'),
(112, 120, 1, '66', '', '116', '76', '0', '131', '1', '0', '171', '', '', '0', '165', '0', '0', '0', '0', '0', '0', '0', 'Nu', '0,1,'),
(113, 121, 1, '88', '', '136', '74', '0', '123', '1', '0', '243', '', '', '0', '112', '0', '0', '0', '0', '0', '0', '0', 'Nu', '2,3,');

-- --------------------------------------------------------

--
-- Table structure for table `pacient_date_sport`
--

CREATE TABLE `pacient_date_sport` (
  `id` int(11) NOT NULL,
  `pacient_id` int(11) NOT NULL,
  `chestionar_id` int(11) NOT NULL,
  `sport_viguros` varchar(255) NOT NULL,
  `miscare_viguroasa` varchar(255) NOT NULL,
  `miscare_greutati` varchar(255) NOT NULL,
  `mersulpejos` varchar(255) NOT NULL,
  `sezand` varchar(255) NOT NULL,
  `sport_preferat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pacient_date_sport`
--

INSERT INTO `pacient_date_sport` (`id`, `pacient_id`, `chestionar_id`, `sport_viguros`, `miscare_viguroasa`, `miscare_greutati`, `mersulpejos`, `sezand`, `sport_preferat`) VALUES
(69, 108, 1, '105', '120', '2', '120', '6', 'gradinarit'),
(70, 109, 1, '45', '75', '3', '120', '5', 'asd'),
(71, 108, 2, '0', '0', '1', '0', '1', 'asd'),
(72, 109, 2, '0', '15', '3', '75', '5', 'mers pe jos'),
(73, 110, 1, '0', '300', '1', '300', '6', 'Plimbari'),
(74, 111, 1, '45', '225', '1', '225', '5', 'Mers pe jos'),
(75, 112, 1, '45', '300', '1', '45', '6', 'Inot'),
(76, 113, 1, '45', '300', '1', '45', '6', 'Inot'),
(77, 114, 1, '15', '0', '1', '120', '6', 'Mers pe jos'),
(78, 115, 1, '15', '0', '1', '120', '6', 'Mers pe jos'),
(79, 116, 1, '0', '225', '1', '225', '3', 'Bicicleta'),
(80, 117, 1, '0', '300', '1', '225', '6', 'PlimbÄƒri '),
(81, 118, 1, '120', '300', '2', '225', '5', 'Bicicleta'),
(82, 119, 1, '0', '300', '1', '300', '4', 'Bicicleta'),
(83, 120, 1, '0', '300', '4', '300', '3', 'Aerobic'),
(84, 121, 1, '120', '300', '4', '300', '6', 'Bicicleta');

-- --------------------------------------------------------

--
-- Table structure for table `pacient_fumat`
--

CREATE TABLE `pacient_fumat` (
  `id` int(11) NOT NULL,
  `pacient_id` int(11) NOT NULL,
  `chestionar_id` int(11) NOT NULL,
  `fumat_luna` varchar(255) NOT NULL,
  `fumat_zilnic` varchar(255) NOT NULL,
  `numar_tigari` varchar(255) NOT NULL,
  `varsta_fumat_fumator` varchar(255) NOT NULL,
  `prima_tig` varchar(255) NOT NULL,
  `fumat_public` varchar(255) NOT NULL,
  `tigata_ren` varchar(255) NOT NULL,
  `tigara_dim` varchar(255) NOT NULL,
  `fumat_bolnav` varchar(255) NOT NULL,
  `intentie_fumat` varchar(255) NOT NULL,
  `intentie_renuntat` varchar(255) NOT NULL,
  `consiliere_renuntat` varchar(255) NOT NULL,
  `gandit_renuntat_fumat` varchar(255) NOT NULL,
  `incercare_renuntat_fumat` varchar(255) NOT NULL,
  `tip_nefumator` varchar(255) NOT NULL,
  `fost_fumator_timp` varchar(255) NOT NULL,
  `varsta_fumat_fost_fumator` varchar(255) NOT NULL,
  `tig_electro` varchar(255) NOT NULL,
  `intentie_tig_electro` varchar(255) NOT NULL,
  `tig_electro_IQOS` varchar(255) NOT NULL,
  `folosit_tig_electro_IQOS` varchar(255) NOT NULL,
  `intentie_folosit_tig_electro_IQOS` varchar(255) NOT NULL,
  `fumat_pasiv_acasa` varchar(255) NOT NULL,
  `fumat_pasiv_public` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pacient_fumat`
--

INSERT INTO `pacient_fumat` (`id`, `pacient_id`, `chestionar_id`, `fumat_luna`, `fumat_zilnic`, `numar_tigari`, `varsta_fumat_fumator`, `prima_tig`, `fumat_public`, `tigata_ren`, `tigara_dim`, `fumat_bolnav`, `intentie_fumat`, `intentie_renuntat`, `consiliere_renuntat`, `gandit_renuntat_fumat`, `incercare_renuntat_fumat`, `tip_nefumator`, `fost_fumator_timp`, `varsta_fumat_fost_fumator`, `tig_electro`, `intentie_tig_electro`, `tig_electro_IQOS`, `folosit_tig_electro_IQOS`, `intentie_folosit_tig_electro_IQOS`, `fumat_pasiv_acasa`, `fumat_pasiv_public`) VALUES
(59, 108, 1, '1', '0', '1', '15', '4', '1', '2', '2', '2', '8', '3', '3', '2', '2', '', '', '', '1', '1', '0', '1', '3', '1', '2'),
(60, 109, 1, '0', '', '', '', '', '', '', '', '', '', '', '', '', '', '1', '', '', '1', '3', '0', '2', '1', '1', '1'),
(61, 108, 2, '0', '', '', '', '', '', '', '', '', '', '', '', '', '', '2', '7', '-1', '1', '1', '1', '1', '1', '1', '1'),
(62, 109, 2, '0', '', '', '', '', '', '', '', '', '', '', '', '', '', '1', '', '', '2', '3', '0', '1', '3', '2', '3'),
(63, 110, 1, '0', '', '', '', '', '', '', '', '', '', '', '', '', '', '2', '1', '18', '1', '5', '1', '1', '5', '1', '1'),
(64, 111, 1, '0', '', '', '', '', '', '', '', '', '', '', '', '', '', '1', '', '', '1', '5', '1', '1', '5', '1', '1'),
(65, 112, 1, '0', '', '', '', '', '', '', '', '', '', '', '', '', '', '1', '', '', '1', '5', '0', '1', '5', '1', '1'),
(66, 113, 1, '0', '', '', '', '', '', '', '', '', '', '', '', '', '', '1', '', '', '1', '5', '0', '1', '5', '1', '1'),
(67, 114, 1, '0', '', '', '', '', '', '', '', '', '', '', '', '', '', '1', '', '', '1', '5', '1', '1', '5', '1', '2'),
(68, 115, 1, '0', '', '', '', '', '', '', '', '', '', '', '', '', '', '1', '', '', '1', '5', '1', '1', '5', '1', '2'),
(69, 116, 1, '0', '', '', '', '', '', '', '', '', '', '', '', '', '', '2', '1', '15', '1', '4', '1', '1', '5', '1', '1'),
(70, 117, 1, '0', '', '', '', '', '', '', '', '', '', '', '', '', '', '1', '', '', '1', '5', '0', '1', '5', '1', '1'),
(71, 118, 1, '0', '', '', '', '', '', '', '', '', '', '', '', '', '', '1', '', '', '1', '5', '1', '1', '5', '1', '1'),
(72, 119, 1, '0', '', '', '', '', '', '', '', '', '', '', '', '', '', '1', '', '', '1', '5', '1', '1', '5', '1', '1'),
(73, 120, 1, '0', '', '', '', '', '', '', '', '', '', '', '', '', '', '1', '', '', '1', '5', '1', '1', '5', '1', '1'),
(74, 121, 1, '0', '', '', '', '', '', '', '', '', '', '', '', '', '', '2', '1', '20', '1', '5', '1', '1', '5', '1', '1');

-- --------------------------------------------------------

--
-- Table structure for table `pacient_info`
--

CREATE TABLE `pacient_info` (
  `id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `oras` varchar(255) NOT NULL,
  `adresa` text NOT NULL,
  `telefon` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `data_nastere` datetime NOT NULL,
  `sex` varchar(2) NOT NULL,
  `screening_center` varchar(255) NOT NULL,
  `medic_familie` tinyint(1) NOT NULL,
  `nume_medic_familie` varchar(255) NOT NULL,
  `studii` varchar(255) NOT NULL,
  `ocupatie` varchar(255) NOT NULL,
  `stare_actuala` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pacient_info`
--

INSERT INTO `pacient_info` (`id`, `patient_id`, `oras`, `adresa`, `telefon`, `email`, `data_nastere`, `sex`, `screening_center`, `medic_familie`, `nume_medic_familie`, `studii`, `ocupatie`, `stare_actuala`) VALUES
(96, 108, 'cluj', 'cluj', '', '', '1981-11-17 00:00:00', 'F', 'Manastur', 0, '', '5', '1', '1'),
(97, 109, 'asd', 'asd', 'asd', 'asd', '1977-12-11 00:00:00', 'M', 'asd', 0, '', '1', '1', '1'),
(98, 110, 'Floresti', 'Porii, nr 19, bl 8, sc 2, ap 20', '0751356794', 'Sandamstr@gmail.com', '1969-09-25 00:00:00', 'F', 'Dr Pop Claudia', 1, 'Dr Pop Claudia', '2', '1', '4'),
(99, 111, 'Cluj Napoca', 'Campului nr 124', '0755748036', 'Baraiandan69@gmail.com', '1969-09-01 00:00:00', 'M', 'Dr Pop Claudia', 1, 'Dr Pop Claudia', '4', '1', '5'),
(100, 112, 'Cluj-Napoca ', 'Campului nr 124', '07488000831', 'Ficabaraian@gmail.com', '1973-05-13 00:00:00', 'F', 'Dr pop claudia', 1, 'Dr pop claudia', '4', '2', '5'),
(101, 113, 'Cluj-Napoca ', 'Campului nr 124', '07488000831', 'Ficabaraian@gmail.com', '1973-05-13 00:00:00', 'F', 'Dr pop claudia', 1, 'Dr pop claudia', '4', '2', '5'),
(102, 114, 'Cluj-Napoca ', 'Mehedinti, nr 13, ap 26', '0723699203', 'Iflorea13@hotmail.com', '1972-12-06 00:00:00', 'M', 'Dr pop claudia', 1, 'Dr pop claudia', '3', '1', '5'),
(103, 115, 'Cluj-Napoca ', 'Mehedinti, nr 13, ap 26', '0723699203', 'Iflorea13@hotmail.com', '1972-12-06 00:00:00', 'M', 'Dr pop claudia', 1, 'Dr pop claudia', '3', '1', '5'),
(104, 116, 'Cluj-Napoca ', 'Mehedinti, nr 47-49, ap 126', '0740324202', 'Danlaslou@gmail.com', '1973-01-23 00:00:00', 'M', 'Dr pop claudia', 1, 'Dr pop claudia', '6', '1', '3'),
(105, 117, 'Ciurea', 'Comuna Ciurea, sat Ciurea, nr 476', '0741468914', 'danielamocan13@yahoo.com', '1971-10-13 00:00:00', 'F', 'Dr. Pop Claudia', 1, 'Dr. Pop Claudia', '4', '1', '1'),
(106, 118, 'Cluj-Napoca ', 'Strada', '0744809946', 'N.timotei@gmail.com', '1973-02-05 00:00:00', 'M', 'Dr pop claudia', 1, 'Dr pop claudia', '6', '2', '5'),
(107, 119, 'Cluj-Napoca ', 'Ale negoiu, nr 3 ap 27', '0727791793', 'Andrasverba@yahoo.com', '1970-07-16 00:00:00', 'M', 'Dr pop claudia', 1, 'Dr pop claudia', '6', '1', '5'),
(108, 120, 'Cluj-Napoca ', 'Mehedinti, nr 47-49, ap 126', '0728293464', 'Virginia.laslou@yahoo.com', '1971-07-25 00:00:00', 'F', 'Dr pop claudia', 1, 'Dr pop claudia', '6', '1', '3'),
(109, 121, 'Cluj-Napoca ', 'Edgar quinet nr 8b ap 69', '0756396292', 'Naomi.kramet@gmail.com', '1972-08-20 00:00:00', 'F', 'Dr pop claudia', 1, 'Dr pop claudia', '3', '1', '5');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `role` enum('Admin','Moderator','Doctor') NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `email`, `role`, `password`, `created_at`, `updated_at`) VALUES
(36, 'admin', '', 'Admin', 'cc03e747a6afbbcbf8be7668acfebee5 ', '2020-11-08 12:59:56', NULL),
(37, 'moderator', 'asd@asd.ro', 'Moderator', '202cb962ac59075b964b07152d234b70', '2020-11-08 13:00:49', '2020-11-08'),
(38, 'doctor', 'asd@asd.ro', 'Doctor', '202cb962ac59075b964b07152d234b70', '2020-11-08 13:01:14', '2020-11-08'),
(41, 'admin2', '', 'Admin', '202cb962ac59075b964b07152d234b70', '2020-11-08 13:03:47', NULL),
(43, 'doctor2', 'asd@asd.ro', 'Doctor', '202cb962ac59075b964b07152d234b70', '2020-11-08 13:05:03', '2020-11-08'),
(44, 'moderator2', 'asd@asd.ro', 'Moderator', '202cb962ac59075b964b07152d234b70', '2020-11-08 13:05:32', '2020-11-08'),
(45, 'lucia', 'lucia@lucia.ro', 'Moderator', '06e9071db01b828dd2e72ff59a95cb8b', '2020-11-08 14:02:52', '2020-11-08'),
(46, 'lotrean', 'lotrean@asd.ro', 'Doctor', '06e9071db01b828dd2e72ff59a95cb8b', '2020-11-08 14:03:26', '2020-11-08'),
(47, 'lucia_doctorand1', 'asd@asd.ro', 'Doctor', '200820e3227815ed1756a6b531e7e0d2', '2020-11-08 14:03:57', '2020-11-08'),
(49, 'lucia_doctorand2', 'asd@asd.ro', 'Doctor', '200820e3227815ed1756a6b531e7e0d2', '2020-11-08 14:05:16', '2020-11-08'),
(50, 'SorinaP', 'drsorinapop@yahoo.com', 'Doctor', '9a400f8d29723ad83a861cd8f51be3aa', '2021-09-08 18:24:31', '2021-09-08'),
(51, 'BiancaCM', 'cojanminzat.bianca@yahoo.com ', 'Doctor', '75431b2a50feb897afcc8234bc4d5a22', '2021-09-08 18:28:00', '2021-09-08');

-- --------------------------------------------------------

--
-- Table structure for table `users_info`
--

CREATE TABLE `users_info` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `nume` varchar(255) NOT NULL,
  `prenume` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users_info`
--

INSERT INTO `users_info` (`id`, `user_id`, `nume`, `prenume`, `location`, `created_at`, `updated_at`) VALUES
(24, 37, 'moderator', 'moderator', 'cluj', '2020-11-08 13:00:49', '2020-11-08'),
(25, 38, 'doctor', 'doctora', 'cluj', '2020-11-08 13:01:14', '2020-11-08'),
(26, 43, 'doctor2', 'doctor2', 'cluj', '2020-11-08 13:05:03', '2020-11-08'),
(27, 44, 'moderator2', 'moderator2', 'cluj', '2020-11-08 13:05:32', '2020-11-08'),
(28, 45, 'Lucia', 'Lotrean', 'Cluj', '2020-11-08 14:02:52', '2020-11-08'),
(29, 46, 'Lucia', 'Lotrean', 'Cluj', '2020-11-08 14:03:26', '2020-11-08'),
(30, 47, 'Lucia', 'Doctorand 1', 'Cluj', '2020-11-08 14:03:57', '2020-11-08'),
(32, 49, 'Lucia', 'Doctorand 2', 'Cluj', '2020-11-08 14:05:16', '2020-11-08'),
(33, 50, 'Pop', 'Sorina', 'Cluj-Napoca', '2021-09-08 18:24:31', '2021-09-08'),
(34, 51, 'Cojan Minzat', 'Bianca', 'Cluj-Napoca', '2021-09-08 18:28:00', '2021-09-08');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chestionar`
--
ALTER TABLE `chestionar`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `monitorizare`
--
ALTER TABLE `monitorizare`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pacient`
--
ALTER TABLE `pacient`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pacient_date_alimentatie`
--
ALTER TABLE `pacient_date_alimentatie`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pacient_date_diverse`
--
ALTER TABLE `pacient_date_diverse`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pacient_date_medicale`
--
ALTER TABLE `pacient_date_medicale`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pacient_date_sport`
--
ALTER TABLE `pacient_date_sport`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pacient_fumat`
--
ALTER TABLE `pacient_fumat`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pacient_info`
--
ALTER TABLE `pacient_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users_info`
--
ALTER TABLE `users_info`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chestionar`
--
ALTER TABLE `chestionar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=107;

--
-- AUTO_INCREMENT for table `monitorizare`
--
ALTER TABLE `monitorizare`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `pacient`
--
ALTER TABLE `pacient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=122;

--
-- AUTO_INCREMENT for table `pacient_date_alimentatie`
--
ALTER TABLE `pacient_date_alimentatie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT for table `pacient_date_diverse`
--
ALTER TABLE `pacient_date_diverse`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=77;

--
-- AUTO_INCREMENT for table `pacient_date_medicale`
--
ALTER TABLE `pacient_date_medicale`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=114;

--
-- AUTO_INCREMENT for table `pacient_date_sport`
--
ALTER TABLE `pacient_date_sport`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;

--
-- AUTO_INCREMENT for table `pacient_fumat`
--
ALTER TABLE `pacient_fumat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=75;

--
-- AUTO_INCREMENT for table `pacient_info`
--
ALTER TABLE `pacient_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=110;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `users_info`
--
ALTER TABLE `users_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `users_info`
--
ALTER TABLE `users_info`
  ADD CONSTRAINT `users_info_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
