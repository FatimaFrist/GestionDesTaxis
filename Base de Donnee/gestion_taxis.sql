-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 03, 2023 at 09:40 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.3.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gestion_taxis`
--
CREATE DATABASE IF NOT EXISTS `gestion_taxis` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `gestion_taxis`;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `CNE` varchar(10) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `telephone` varchar(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `soft_delete` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `CNE`, `nom`, `prenom`, `adresse`, `telephone`, `email`, `username`, `password`, `soft_delete`) VALUES
(1, 'OD56378', 'elachyry', 'Mohammed', 'Hay salam ', '0680346100', 'mohammedelachyry@gmail.com', 'elachyry', 'elachyry', NULL),
(4, 'OD97878', 'ELACHYRY', 'ADAM', 'adamachyry@gmail.com', 'HAY NAHDA', '0123648799', 'ELACHYRY_ADAM_java.util.Random@28582542', 'ekIjOW9vZmQ=', '2023-01-03 18:53:25'),
(5, 'OD878544', 'ELACHYRY', 'ADAM', 'HAY NAHDA', '0124578914', 'adamachyryy@gmail.com', 'ELACHYRY_ADAM_java.util.Random@3aeb0414', 'cU9AMXNLSkU=', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `chauffeur`
--

CREATE TABLE `chauffeur` (
  `id` int(10) NOT NULL,
  `CNE` varchar(10) NOT NULL,
  `numPermis` varchar(10) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `telephone` varchar(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `soft_delete` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chauffeur`
--

INSERT INTO `chauffeur` (`id`, `CNE`, `numPermis`, `nom`, `prenom`, `adresse`, `telephone`, `email`, `username`, `password`, `soft_delete`) VALUES
(1, 'sj11111', 'd1234', 'yyyy', 'yyy', 'laayoune', '0611111111', 'email.@gmail.com', 'chauffeur1', 'chauffeur1', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `adresse` varchar(50) DEFAULT '',
  `telephone` varchar(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `soft_delete` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id`, `nom`, `prenom`, `adresse`, `telephone`, `email`, `username`, `password`, `soft_delete`) VALUES
(2, 'elachyry', 'Mohammed', 'Hay salam ', '0680346100', 'sdfdssddf@gmail.com', 'elachyry', 'alIhNDNrcjk=', NULL),
(12, 'MOHAMMED', 'ELACHYRY', '', '0661705486', 'achyri2000@gmail.com', 'MOHAMMED_ELACHYRY_java.util.Random@5f960ef4', 'alUjMEVUanQ=', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `demande`
--

CREATE TABLE `demande` (
  `id` int(11) NOT NULL,
  `voiture` varchar(30) NOT NULL,
  `client` varchar(20) NOT NULL,
  `chauffeur` varchar(10) NOT NULL,
  `status` varchar(10) NOT NULL,
  `date_demande` date DEFAULT NULL,
  `soft_delete` timestamp NULL DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `demande`
--

INSERT INTO `demande` (`id`, `voiture`, `client`, `chauffeur`, `status`, `date_demande`, `soft_delete`) VALUES
(4, 'fff3445', 'Mohammed', 'sj11111', 'Accepté', '2022-12-29', NULL),
(5, 'fff3445', 'Mohammed', 'sj11111', 'Annulé', '2022-12-18', NULL),
(6, 'fff3445', 'Mohammed', 'sj11111', 'Accepté', '2022-12-17', NULL),
(7, 'fff3445', 'Mohammed', 'sj11111', 'Refusé', '2022-12-17', NULL),
(8, 'fff3445', 'Mohammed', 'sj11111', 'En attente', '2022-12-14', NULL),
(9, 'fff3445', 'Mohammed', 'sj11111', 'Refusé', '2023-01-01', NULL),
(10, 'fff3445', 'ELACHYRY', 'sj11111', 'Accepté', '2022-12-27', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `reparation`
--

CREATE TABLE `reparation` (
  `id` int(11) NOT NULL,
  `immatriculation` varchar(30) NOT NULL,
  `dateReparation` date NOT NULL,
  `designation` varchar(50) NOT NULL,
  `quantity` int(11) NOT NULL,
  `unitPrix` float NOT NULL,
  `prixUT` float NOT NULL,
  `prixTTC` float NOT NULL,
  `soft_delete` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reparation`
--

INSERT INTO `reparation` (`id`, `immatriculation`, `dateReparation`, `designation`, `quantity`, `unitPrix`, `prixUT`, `prixTTC`, `soft_delete`) VALUES
(1, 'fff3445', '2023-01-01', 'kjhkjtdfsdsd', 1, 12220, 12220, 14664, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `vidange`
--

CREATE TABLE `vidange` (
  `id` int(11) NOT NULL,
  `immatriculation` varchar(20) NOT NULL,
  `dateVidange` date NOT NULL,
  `killometrage` int(11) NOT NULL,
  `killometrageNextVidange` int(11) NOT NULL,
  `typeHuile` varchar(11) NOT NULL,
  `quantityHuile` float NOT NULL,
  `litrePrix` float NOT NULL,
  `prixHT` float NOT NULL,
  `prixTTC` float NOT NULL,
  `soft_delete` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vidange`
--

INSERT INTO `vidange` (`id`, `immatriculation`, `dateVidange`, `killometrage`, `killometrageNextVidange`, `typeHuile`, `quantityHuile`, `litrePrix`, `prixHT`, `prixTTC`, `soft_delete`) VALUES
(1, 'fff3445', '2023-01-01', 250000, 260000, '10W40', 6, 20, 120, 144, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `voiture`
--

CREATE TABLE `voiture` (
  `id` int(10) NOT NULL,
  `immatriculation` varchar(30) NOT NULL,
  `marque` varchar(10) NOT NULL,
  `model` varchar(10) NOT NULL,
  `carburant` varchar(10) NOT NULL,
  `consomation_moyenne` varchar(10) DEFAULT NULL,
  `puissance_fiscale` varchar(10) DEFAULT NULL,
  `date_1er_immatru` varchar(20) DEFAULT NULL,
  `etat` varchar(10) NOT NULL,
  `killometrage` varchar(10) DEFAULT NULL,
  `date_dernier_controle_tech` varchar(20) DEFAULT NULL,
  `km_dernirer_vidange` varchar(10) DEFAULT NULL,
  `km_dernier_courroie` varchar(10) DEFAULT NULL,
  `img_path` varchar(255) DEFAULT NULL,
  `soft_delete` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `voiture`
--

INSERT INTO `voiture` (`id`, `immatriculation`, `marque`, `model`, `carburant`, `consomation_moyenne`, `puissance_fiscale`, `date_1er_immatru`, `etat`, `killometrage`, `date_dernier_controle_tech`, `km_dernirer_vidange`, `km_dernier_courroie`, `img_path`, `soft_delete`) VALUES
(1, 'fff3445', 'audi', '2019', '677', '79', '88', '2022-11-08', 'ett', '3000', '2022-11-02', '8877', '788', '', NULL),
(2, 'sdfsdfdsf', 'sdfdsfsd', 'dsfdsfds', 'dsfdfs', '', '', 'null', 'En pane', '', 'null', '', '', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `chauffeur`
--
ALTER TABLE `chauffeur`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `demande`
--
ALTER TABLE `demande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_voiture` (`voiture`),
  ADD KEY `FK_client` (`client`),
  ADD KEY `FK_chauffeur` (`chauffeur`);

--
-- Indexes for table `reparation`
--
ALTER TABLE `reparation`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vidange`
--
ALTER TABLE `vidange`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `voiture`
--
ALTER TABLE `voiture`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `chauffeur`
--
ALTER TABLE `chauffeur`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `demande`
--
ALTER TABLE `demande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `reparation`
--
ALTER TABLE `reparation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `vidange`
--
ALTER TABLE `vidange`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `voiture`
--
ALTER TABLE `voiture`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
