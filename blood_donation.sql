-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2019 at 07:14 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `blood_donation`
--

-- --------------------------------------------------------

--
-- Table structure for table `new_user`
--

CREATE TABLE `new_user` (
  `id` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(20) NOT NULL,
  `mobile` varchar(30) NOT NULL,
  `blood_group` varchar(5) NOT NULL,
  `latitude` varchar(100) NOT NULL,
  `longitude` varchar(100) NOT NULL,
  `postal_address` varchar(1000) NOT NULL,
  `age` int(11) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `profile_pic` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `new_user`
--

INSERT INTO `new_user` (`id`, `email`, `password`, `name`, `mobile`, `blood_group`, `latitude`, `longitude`, `postal_address`, `age`, `gender`, `profile_pic`) VALUES
(9, 'uniqueshivam@gmail.com', 'a29d1598024f9e87beab4b98411d48ce', 'shivam kumar', '8622035642', 'O+VE', '22.6339568', '88.4167293', '40/2,Anand Vision Apartment, Post Office Rd, Mahendra Colony, Cantonment, Rajbari, Dum Dum, Kolkata, West Bengal 700028, India', 22, 'MALE', '0'),
(12, 'ankitjb@live.in', 'ankit.2112', 'Ankit kumar', '8252033476', 'B+VE', '25.7043744', '85.3063129', 'bakarial tola', 21, 'FEMALE', '0'),
(13, 'rahulkumar6611@gmail.com', 'frndsfrever', 'Rahul kumar', '9062500103', 'O+VE', '25.7123744', '85.3123129', 'paschim darwaza', 30, 'MALE', '0'),
(14, 'aftabroshan02@gmail.com', 'roshan.2109', 'Roshan aftab', '9123805498', 'AB+VE', '25.7143744', '85.3143129', 'nawab bahadur road', 26, 'MALE', '0'),
(15, 'mailmeshivam.2112@gmail.com', 'shivam@2112', 'Manish Malhotra', '9835265212', 'AB-VE', '25.7174744', '85.3194129', 'khajeklan', 21, 'MALE', '0'),
(16, 'rishavraj@gmail.com', 'b4a7927a2c39e8e653247e129314420e', 'rishav raj', '9001234598', 'O-VE', '22.6361398', '88.4188447', 'dum dum cantonment municipal corporation', 32, 'MALE', '0'),
(17, 'avinash2112@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b', 'avinash', '9835265212', 'B+VE', '22.5399082', '88.329245', '15/2, Ibrahim Rd, Alipore Body Guard Lines, Alipore, Kolkata, West Bengal 700023, India', 23, 'MALE', '0'),
(18, 'ankitkr0696@gmail.com', 'a29d1598024f9e87beab4b98411d48ce', 'ankit', '8252033476', 'A+VE', '22.6340398', '88.4167447', '40/2,Anand Vision Apartment, Post Office Rd, Mahendra Colony, Cantonment, Rajbari, Dum Dum, Kolkata, West Bengal 700028, India', 24, 'MALE', '0'),
(19, 'aditya.2112@gmail.com', '07455967a262bd16404fbcf803a34a9c', 'aditya', '9334293912', 'A+VE', '22.6339559', '88.4167327', '40/2,Anand Vision Apartment, Post Office Rd, Mahendra Colony, Cantonment, Rajbari, Dum Dum, Kolkata, West Bengal 700028, India', 22, 'MALE', 'aditya.2112@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `new_user`
--
ALTER TABLE `new_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `new_user`
--
ALTER TABLE `new_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
