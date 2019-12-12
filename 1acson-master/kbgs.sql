-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.6-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping data for table kbgs.account: ~8 rows (approximately)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`ID`, `Username`, `Password`, `User_type`) VALUES
	(1, 'Admin', '123', 'admin'),
	(2, 'acc', '979d472a84804b9f647bc185a877a8b5', 'user'),
	(3, 'acson', '827ccb0eea8a706c4c34a16891f84e7b', 'user'),
	(4, 'ben', '202cb962ac59075b964b07152d234b70', 'Admin'),
	(5, 'bengarde', '202cb962ac59075b964b07152d234b70', 'User'),
	(6, 'king', '202cb962ac59075b964b07152d234b70', 'User'),
	(7, 'paul', '202cb962ac59075b964b07152d234b70', 'User'),
	(8, 'meme', 'ddb30680a691d157187ee1cf9e896d03', 'User');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- Dumping data for table kbgs.products: ~5 rows (approximately)
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`Barcode`, `Description`, `Brand`, `Item_type`, `Quantity`, `Price`) VALUES
	(10001, 'M16', 'Japan', 'Rifle', 16, 20),
	(10003, 'M14', 'US', 'Rifle', 33, 5),
	(10005, 'm357', 'us', 'Pistol', 64, 320),
	(10009, 'm4', 'china', 'Rifle', 22, 100),
	(10011, 'desert45', 'us', 'pistol', 28, 50);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;

-- Dumping data for table kbgs.user_logger: ~8 rows (approximately)
/*!40000 ALTER TABLE `user_logger` DISABLE KEYS */;
INSERT INTO `user_logger` (`ID`, `Username`, `Action`) VALUES
	(6, 'king', 'login account'),
	(7, 'paul', 'login account'),
	(8, 'meme', 'login account'),
	(8, 'meme', 'logout account/cashout'),
	(8, 'meme', 'login account'),
	(8, 'meme', 'logout account/cashout'),
	(8, 'meme', 'logout account/cashout'),
	(8, 'meme', 'logout account/cashout');
/*!40000 ALTER TABLE `user_logger` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
