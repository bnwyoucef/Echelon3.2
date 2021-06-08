-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 30, 2021 at 09:01 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `echelondb`
--

-- --------------------------------------------------------

--
-- Table structure for table `employeeposts`
--

CREATE TABLE `employeeposts` (
  `Numéro` int(11) NOT NULL,
  `Nom` varchar(100) NOT NULL,
  `Prénom` varchar(100) NOT NULL,
  `Grade` varchar(100) NOT NULL,
  `DernierEchelon` date NOT NULL,
  `PosteSupérieur` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employeeposts`
--

INSERT INTO `employeeposts` (`Numéro`, `Nom`, `Prénom`, `Grade`, `DernierEchelon`, `PosteSupérieur`) VALUES
(2, 'Benariba', 'Ahmed', 'Ingenieur d\'etat', '2013-05-05', 1),
(3, 'Smahat', 'Mohammed', 'doctor', '2021-04-04', 0),
(4, 'kelouche', 'Nadia', 'master2', '2021-04-04', 0),
(6, 'y', 'a', 'f', '2018-02-01', 1),
(7, 'r', 'z', 'a', '2019-08-15', 1),
(67, '[ty54]', '[ty74]', '[yt78]', '0000-00-00', 0),
(75, 'Smahat', 'Mohammed', 'doctor', '2012-05-08', 1);

-- --------------------------------------------------------

--
-- Table structure for table `historique`
--

CREATE TABLE `historique` (
  `Id` int(11) NOT NULL,
  `num_echelon` int(40) NOT NULL,
  `date_echelon` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `historique`
--

INSERT INTO `historique` (`Id`, `num_echelon`, `date_echelon`) VALUES
(2, 1, '2007-01-01'),
(2, 2, '2010-05-05'),
(2, 3, '2013-05-05'),
(4, 1, '2000-01-01'),
(4, 2, '2004-04-25'),
(4, 3, '2007-04-12'),
(4, 4, '2010-04-08'),
(4, 5, '2013-04-19'),
(4, 6, '2017-04-11'),
(4, 7, '2021-04-04'),
(6, 1, '2018-02-01'),
(75, 1, '2005-05-01'),
(75, 2, '2008-05-01'),
(75, 3, '2012-05-08');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(100) NOT NULL,
  `password` text NOT NULL,
  `admin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `admin`) VALUES
('admin', 'adminn', 1),
('root', 'root', 1),
('user', 'user', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employeeposts`
--
ALTER TABLE `employeeposts`
  ADD PRIMARY KEY (`Numéro`);

--
-- Indexes for table `historique`
--
ALTER TABLE `historique`
  ADD UNIQUE KEY `indexes` (`Id`,`num_echelon`,`date_echelon`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `password` (`password`) USING HASH;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employeeposts`
--
ALTER TABLE `employeeposts`
  MODIFY `Numéro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
