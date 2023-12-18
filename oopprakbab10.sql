-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 17, 2023 at 11:58 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `oopprakbab10`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_datapengeluaran`
--

CREATE TABLE `tb_datapengeluaran` (
  `ID` int NOT NULL,
  `NamaBuah` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Warnabuah` varchar(20) NOT NULL,
  `tanggalpengeluaran` int NOT NULL,
  `deskripsipengeluaran` varchar(25) NOT NULL,
  `totalpengeluaran` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_datapengeluaran`
--

INSERT INTO `tb_datapengeluaran` (`ID`, `NamaBuah`, `Warnabuah`, `tanggalpengeluaran`, `deskripsipengeluaran`, `totalpengeluaran`) VALUES
(2, 'as', 'as', 12, 'wre', 12),
(3, 'apel', 'merah', 10, 'busuk', 12000);

-- --------------------------------------------------------

--
-- Table structure for table `tb_tokobuah`
--

CREATE TABLE `tb_tokobuah` (
  `ID` int NOT NULL,
  `namabuah` varchar(20) NOT NULL,
  `warnabuah` varchar(11) NOT NULL,
  `hargabuah` varchar(20) NOT NULL,
  `jumlahbuah` int NOT NULL,
  `pendapatanbuah` int NOT NULL,
  `pengeluaranbuah` int NOT NULL,
  `totalpendapatan` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_tokobuah`
--

INSERT INTO `tb_tokobuah` (`ID`, `namabuah`, `warnabuah`, `hargabuah`, `jumlahbuah`, `pendapatanbuah`, `pengeluaranbuah`, `totalpendapatan`) VALUES
(4, 'apel', 'merah', '12000', 12, 120000, 1000, 110000),
(5, 'jambu', 'hijau', '5000', 20, 100000, 0, 100000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_datapengeluaran`
--
ALTER TABLE `tb_datapengeluaran`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tb_tokobuah`
--
ALTER TABLE `tb_tokobuah`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_datapengeluaran`
--
ALTER TABLE `tb_datapengeluaran`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tb_tokobuah`
--
ALTER TABLE `tb_tokobuah`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
