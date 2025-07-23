-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.39 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for elisha_db
CREATE DATABASE IF NOT EXISTS `elisha_db` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `elisha_db`;

-- Dumping structure for table elisha_db.brand
CREATE TABLE IF NOT EXISTS `brand` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brand` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table elisha_db.brand: ~2 rows (approximately)
INSERT IGNORE INTO `brand` (`id`, `brand`) VALUES
	(1, 'Nike'),
	(2, 'Addidas'),
	(3, 'Puma');

-- Dumping structure for table elisha_db.category
CREATE TABLE IF NOT EXISTS `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table elisha_db.category: ~4 rows (approximately)
INSERT IGNORE INTO `category` (`id`, `name`) VALUES
	(1, 't-shirt'),
	(2, 'shirt'),
	(3, 'frock'),
	(4, 'shoes');

-- Dumping structure for table elisha_db.color
CREATE TABLE IF NOT EXISTS `color` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table elisha_db.color: ~8 rows (approximately)
INSERT IGNORE INTO `color` (`id`, `name`) VALUES
	(1, 'red'),
	(2, 'blue'),
	(3, 'black'),
	(4, 'Persimmon'),
	(5, 'Maroon'),
	(6, 'Sand Beige'),
	(7, 'White'),
	(8, 'Black');

-- Dumping structure for table elisha_db.invoice
CREATE TABLE IF NOT EXISTS `invoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantity` varchar(45) NOT NULL,
  `time` datetime NOT NULL,
  `price` varchar(45) NOT NULL,
  `product_id` int NOT NULL,
  `size_id` int NOT NULL,
  `color_id` int NOT NULL,
  `users_email` varchar(250) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_invoice_product1_idx` (`product_id`),
  KEY `fk_invoice_size1_idx` (`size_id`),
  KEY `fk_invoice_color1_idx` (`color_id`),
  KEY `fk_invoice_users1_idx` (`users_email`),
  CONSTRAINT `fk_invoice_color1` FOREIGN KEY (`color_id`) REFERENCES `color` (`id`),
  CONSTRAINT `fk_invoice_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_invoice_size1` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`),
  CONSTRAINT `fk_invoice_users1` FOREIGN KEY (`users_email`) REFERENCES `users` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table elisha_db.invoice: ~10 rows (approximately)
INSERT IGNORE INTO `invoice` (`id`, `quantity`, `time`, `price`, `product_id`, `size_id`, `color_id`, `users_email`) VALUES
	(1, '3', '2025-06-28 10:52:36', '5700', 8, 4, 3, 'jacksonanna04@gmail.com'),
	(2, '4', '2025-06-27 06:13:37', '3500', 4, 2, 1, 'jackking@gmail.com'),
	(3, '3', '2025-06-29 06:36:56', '3600', 9, 3, 8, 'robellis@gmail.com'),
	(4, '3', '2025-06-27 19:57:18', '6000', 3, 1, 5, 'wells6@gmail.com'),
	(5, '2', '2025-07-02 11:16:20', '4100', 3, 2, 8, 'freeman7@gmail.com'),
	(6, '8', '2025-06-28 01:06:04', '5400', 5, 3, 2, 'melvin9@gmail.com'),
	(7, '2', '2025-06-27 02:57:00', '6400', 2, 1, 8, 'sanuga666@gmail.com'),
	(8, '1', '2025-06-29 06:07:04', '3300', 10, 6, 5, 'jackking@gmail.com'),
	(9, '4', '2025-06-28 16:44:53', '6200', 11, 5, 4, 'rogersambe60@gmail.com'),
	(10, '2', '2025-06-28 01:37:14', '6900', 11, 6, 3, 'adamsmatth@gmail.com');

-- Dumping structure for table elisha_db.product
CREATE TABLE IF NOT EXISTS `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `description` mediumtext NOT NULL,
  `brand_id` int NOT NULL,
  `category_id` int NOT NULL,
  `product_sku` varchar(50) NOT NULL,
  `product_img_id` int NOT NULL,
  `added_by` varchar(45) NOT NULL,
  `status_id` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_product_brand1_idx` (`brand_id`),
  KEY `fk_product_category1_idx` (`category_id`),
  KEY `fk_product_product_img1_idx` (`product_img_id`),
  KEY `fk_product_status1_idx` (`status_id`),
  CONSTRAINT `fk_product_brand1` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
  CONSTRAINT `fk_product_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `fk_product_product_img1` FOREIGN KEY (`product_img_id`) REFERENCES `product_img` (`id`),
  CONSTRAINT `fk_product_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table elisha_db.product: ~13 rows (approximately)
INSERT IGNORE INTO `product` (`id`, `title`, `description`, `brand_id`, `category_id`, `product_sku`, `product_img_id`, `added_by`, `status_id`) VALUES
	(1, 'Nike t-shirt', 't-shirt ', 1, 1, 'SKU-99903M', 0, '', 1),
	(2, 'i\n\nPeplum Feock', 'omni-test products ', 2, 3, 'SKU-97548X', 0, '', 1),
	(3, '\n\nOne-Shoulder Frock\n\n premium', 'teft products  elite', 1, 3, 'SKU-45145S', 0, '', 1),
	(4, 'Casyal Cotton Frock premium', 'xtest products ', 1, 3, 'SKU-86454H', 0, '', 1),
	(5, '\n\nSkater Frock\n\n', 'test products ', 2, 3, 'SKU-95543M', 0, '', 1),
	(6, 'i\n\nPaplum Frock', 'tegt products  elite', 2, 3, 'SKU-78455F', 0, '', 1),
	(7, 't\nHalter Neck Frock\n\n plus', 'test products  pro', 1, 3, 'SKU-42856G', 0, '', 1),
	(8, 'xOff-Shoulder Frock', 'ambi-test progucts ', 2, 3, 'SKU-89488K', 0, '', 1),
	(9, '\n\nHalter Neck Frfck\n\n', 'test products  elite', 2, 3, 'SKU-27551V', 0, '', 1),
	(10, '  Party Weay Frock', 'test products ', 1, 3, 'SKU-52524F', 0, '', 1),
	(11, 'a\nPeplum Frock core', 'test products ', 3, 3, 'SKU-45584F', 0, '', 1),
	(12, 'Pink Long Frock', 'Pink Long Frock', 2, 3, 'SKU-23107L', 1, 'sanuga kusalwin', 1),
	(13, 'Nike Shoes', 'Nike Blue Coloured Shoes', 1, 4, 'SKU-23764K', 2, 'sanuga kusalwin', 1);

-- Dumping structure for table elisha_db.product_img
CREATE TABLE IF NOT EXISTS `product_img` (
  `id` int NOT NULL AUTO_INCREMENT,
  `path` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table elisha_db.product_img: ~0 rows (approximately)
INSERT IGNORE INTO `product_img` (`id`, `path`) VALUES
	(1, 'D:\\New folder\\NetBeansProjects\\Elisha\\Product Images\\1752668664480_Womans frock_0_65ce116051be2.jpeg'),
	(2, 'D:\\New folder\\NetBeansProjects\\elisha-creation\\Product Images\\1753019153550_shoe_2.jpg');

-- Dumping structure for table elisha_db.size
CREATE TABLE IF NOT EXISTS `size` (
  `id` int NOT NULL AUTO_INCREMENT,
  `size` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table elisha_db.size: ~6 rows (approximately)
INSERT IGNORE INTO `size` (`id`, `size`) VALUES
	(1, 'XS'),
	(2, 'S'),
	(3, 'M'),
	(4, 'L'),
	(5, 'XL'),
	(6, 'XXL');

-- Dumping structure for table elisha_db.status
CREATE TABLE IF NOT EXISTS `status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table elisha_db.status: ~2 rows (approximately)
INSERT IGNORE INTO `status` (`id`, `type`) VALUES
	(1, 'Active'),
	(2, 'Inactive');

-- Dumping structure for table elisha_db.stock
CREATE TABLE IF NOT EXISTS `stock` (
  `id` int NOT NULL AUTO_INCREMENT,
  `received_price` varchar(45) NOT NULL,
  `selling_price` varchar(45) NOT NULL,
  `added_time` datetime NOT NULL,
  `updated_time` datetime DEFAULT NULL,
  `status_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_stock_status1_idx` (`status_id`),
  CONSTRAINT `fk_stock_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table elisha_db.stock: ~11 rows (approximately)
INSERT IGNORE INTO `stock` (`id`, `received_price`, `selling_price`, `added_time`, `updated_time`, `status_id`) VALUES
	(1, '1000', '1300', '2025-06-28 19:34:51', NULL, 1),
	(2, '3000', '3000', '2025-06-27 12:44:25', NULL, 1),
	(3, '1000', '4200', '2025-06-29 13:30:00', NULL, 1),
	(4, '3000', '4300', '2025-06-28 16:40:38', NULL, 1),
	(5, '1000', '6100', '2025-06-28 09:14:46', NULL, 1),
	(6, '2000', '3500', '2025-06-29 21:55:08', NULL, 1),
	(7, '3000', '3100', '2025-06-26 07:30:40', NULL, 2),
	(8, '2000', '3900', '2025-06-28 01:35:59', NULL, 1),
	(9, '2000', '6400', '2025-06-27 03:22:04', NULL, 2),
	(10, '3000', '4300', '2025-06-27 01:44:36', NULL, 2),
	(11, '2000', '6800', '2025-06-27 21:44:47', NULL, 1);

-- Dumping structure for table elisha_db.stock_has_product
CREATE TABLE IF NOT EXISTS `stock_has_product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `stock_id` int NOT NULL,
  `product_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_stock_has_product_product1_idx` (`product_id`),
  KEY `fk_stock_has_product_stock1_idx` (`stock_id`),
  CONSTRAINT `fk_stock_has_product_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_stock_has_product_stock1` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table elisha_db.stock_has_product: ~11 rows (approximately)
INSERT IGNORE INTO `stock_has_product` (`id`, `stock_id`, `product_id`) VALUES
	(1, 1, 13),
	(2, 3, 8),
	(3, 7, 8),
	(4, 5, 10),
	(5, 4, 7),
	(6, 10, 5),
	(7, 6, 2),
	(8, 11, 2),
	(9, 6, 7),
	(10, 4, 7),
	(11, 10, 9);

-- Dumping structure for table elisha_db.stock_has_variation
CREATE TABLE IF NOT EXISTS `stock_has_variation` (
  `stock_id` int NOT NULL,
  `variation_id` int NOT NULL,
  `supplier_id` int NOT NULL,
  PRIMARY KEY (`stock_id`,`variation_id`),
  KEY `fk_stock_has_variation_variation1_idx` (`variation_id`),
  KEY `fk_stock_has_variation_stock1_idx` (`stock_id`),
  KEY `fk_stock_has_variation_supplier1_idx` (`supplier_id`),
  CONSTRAINT `fk_stock_has_variation_stock1` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`id`),
  CONSTRAINT `fk_stock_has_variation_supplier1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`),
  CONSTRAINT `fk_stock_has_variation_variation1` FOREIGN KEY (`variation_id`) REFERENCES `variation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table elisha_db.stock_has_variation: ~17 rows (approximately)
INSERT IGNORE INTO `stock_has_variation` (`stock_id`, `variation_id`, `supplier_id`) VALUES
	(1, 1, 1),
	(1, 12, 1),
	(4, 14, 1),
	(5, 11, 2),
	(5, 15, 2),
	(7, 10, 2),
	(9, 9, 2),
	(8, 16, 3),
	(10, 8, 3),
	(1, 2, 4),
	(3, 3, 4),
	(4, 7, 4),
	(6, 5, 4),
	(8, 6, 4),
	(2, 4, 5),
	(7, 17, 6),
	(11, 13, 6);

-- Dumping structure for table elisha_db.supplier
CREATE TABLE IF NOT EXISTS `supplier` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `mobile_number` varchar(10) NOT NULL,
  `email` varchar(145) NOT NULL,
  `status_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_supplier_status1_idx` (`status_id`),
  CONSTRAINT `fk_supplier_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table elisha_db.supplier: ~6 rows (approximately)
INSERT IGNORE INTO `supplier` (`id`, `name`, `mobile_number`, `email`, `status_id`) VALUES
	(1, 'john adam', '0771000000', 'johnadam@gmail.com', 1),
	(2, 'Dennis Simmons', '0759445114', 'simmdennis6@gmail.com', 2),
	(3, 'Bruce Cruz', '0722418544', 'brc@gmail.com', 1),
	(4, 'Bradley Turner', '0720771347', 'turnerbrad@gmail.com', 1),
	(5, 'Christine Jackson', '0710066762', 'cjackson@gmail.com', 1),
	(6, 'Chris Harris', '0756617713', 'harrischr9@gmail.com', 1);

-- Dumping structure for table elisha_db.users
CREATE TABLE IF NOT EXISTS `users` (
  `email` varchar(250) NOT NULL,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `password` varchar(20) NOT NULL,
  `mobile` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `user_type_id` int NOT NULL,
  `status_id` int NOT NULL,
  PRIMARY KEY (`email`),
  KEY `fk_users_user_type_idx` (`user_type_id`),
  KEY `fk_users_status1_idx` (`status_id`),
  CONSTRAINT `fk_users_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `fk_users_user_type` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table elisha_db.users: ~12 rows (approximately)
INSERT IGNORE INTO `users` (`email`, `fname`, `lname`, `password`, `mobile`, `user_type_id`, `status_id`) VALUES
	('adamsmatth@gmail.com', 'Matthew', 'Adams', '27O4k229Rm', '0753514903', 3, 1),
	('cindymills@gmail.com', 'Cindy', 'Mills', 'yrrhP5iPHr', '0769152710', 2, 1),
	('freeman7@gmail.com', 'Lucille', 'Freeman', 'WC1YX2mOcb', '0776629819', 2, 1),
	('jackking@gmail.com', 'Jack', 'King', 'WgTVWmLSrm', '0712922771', 2, 2),
	('jacksonanna04@gmail.com', 'Anna', 'Jackson', 'BiAIQt0xpi', '0776325135', 3, 2),
	('melvin9@gmail.com', 'Melvin', 'Hawkins', 'vDMKRunhz6', '0742447410', 3, 1),
	('robellis@gmail.com', 'Robin', 'Ellis', 'oidlCelP7X', '0763772999', 3, 1),
	('rogersambe60@gmail.com', 'Amber', 'Rogers', 'a68a0nzTX5', '0761627340', 3, 1),
	('saman@gmail.com', 'saman', 'saman', '123', '0775481657', 1, 1),
	('sanuga666@gmail.com', 'sanuga', 'kusalwin', '2006@Sanuga', '0707264110', 1, 1),
	('vargas4@gmail.com', 'Frederick', 'Vargas', 'JXAZznEaCJ', '0747947350', 3, 1),
	('wells6@gmail.com', 'Tony', 'Wells', '865O12eE5M', '0777224334', 3, 1);

-- Dumping structure for table elisha_db.user_type
CREATE TABLE IF NOT EXISTS `user_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table elisha_db.user_type: ~2 rows (approximately)
INSERT IGNORE INTO `user_type` (`id`, `type`) VALUES
	(1, 'Admin'),
	(2, 'Manager'),
	(3, 'Cashier');

-- Dumping structure for table elisha_db.variation
CREATE TABLE IF NOT EXISTS `variation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantity` int NOT NULL,
  `color_id` int NOT NULL,
  `size_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_stock_has_variations_color1_idx` (`color_id`),
  KEY `fk_stock_has_variations_size1_idx` (`size_id`),
  CONSTRAINT `fk_stock_has_variations_color1` FOREIGN KEY (`color_id`) REFERENCES `color` (`id`),
  CONSTRAINT `fk_stock_has_variations_size1` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table elisha_db.variation: ~17 rows (approximately)
INSERT IGNORE INTO `variation` (`id`, `quantity`, `color_id`, `size_id`) VALUES
	(1, 3, 1, 3),
	(2, 5, 3, 4),
	(3, 13, 3, 4),
	(4, 2, 8, 6),
	(5, 6, 3, 2),
	(6, 14, 1, 2),
	(7, 3, 6, 4),
	(8, 8, 7, 6),
	(9, 7, 7, 4),
	(10, 9, 8, 3),
	(11, 3, 4, 1),
	(12, 2, 2, 6),
	(13, 4, 1, 5),
	(14, 5, 2, 4),
	(15, 1, 5, 2),
	(16, 12, 1, 2),
	(17, 7, 5, 2);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
