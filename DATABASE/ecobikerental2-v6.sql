-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ecobikerental2
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bike`
--

DROP TABLE IF EXISTS `bike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bike` (
  `bike_id` int NOT NULL,
  `bike_barcode` varchar(45) NOT NULL,
  `bike_type` int NOT NULL,
  `dock` int DEFAULT NULL,
  `bike_brand` varchar(45) NOT NULL,
  `bike_img_url` text,
  `bike_is_being_used` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`bike_id`),
  KEY `bike_type` (`bike_type`),
  KEY `dock` (`dock`),
  CONSTRAINT `bike_ibfk_1` FOREIGN KEY (`bike_type`) REFERENCES `biketype` (`bike_type_id`),
  CONSTRAINT `bike_ibfk_2` FOREIGN KEY (`dock`) REFERENCES `dock` (`dock_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bike`
--

LOCK TABLES `bike` WRITE;
/*!40000 ALTER TABLE `bike` DISABLE KEYS */;
INSERT INTO `bike` VALUES (1,'111',1,1,'Thong Nhat',NULL,0),(2,'222',2,2,'Giant',NULL,0),(3,'333',3,3,'Kymco',NULL,0),(4,'444',1,4,'Thong Nhat',NULL,0),(5,'555',2,5,'Giant',NULL,0),(6,'666',2,5,'Giant',NULL,0),(7,'777',3,5,'Kymco',NULL,0), (8,'112',1,1,'Avibus',NULL,0), (9,'113',2,1,'Kasawa',NULL,0), (10,'114',3,1,'ASAMA',NULL,0),
                          (11,'115',1,1,'Asama',NULL,0),(12,'116',3,1,'Giant',NULL,0),(13,'117',3,1,'Kymco',NULL,0),(14,'118',1,1,'Misao',NULL,0),(15,'119',2,1,'Giant',NULL,0),(16,'110',2,1,'Asagi',NULL,0),(17,'221',3,2,'Kymco',NULL,0), (18,'223',1,2,'Avibus',NULL,0), (19,'224',2,2,'Kasawa',NULL,0), (20,'225',3,2,'ASAMA',NULL,0),
                          (21,'226',1,2,'Asama',NULL,0),(22,'227',3,2,'Giant',NULL,0),(23,'228',3,2,'Kymco',NULL,0),(24,'229',1,2,'Misao',NULL,0),(25,'220',2,2,'Giant',NULL,0),(26,'331',2,3,'Asagi',NULL,0),(27,'332',3,3,'Kymco',NULL,0), (28,'334',1,3,'Avibus',NULL,0), (29,'335',2,3,'Kasawa',NULL,0), (30,'336',3,3,'ASAMA',NULL,0),
                          (31,'337',1,3,'Asama',NULL,0),(32,'338',3,3,'Giant',NULL,0),(33,'339',3,3,'Kymco',NULL,0),(34,'330',1,3,'Misao',NULL,0),(35,'441',2,4,'Giant',NULL,0),(36,'442',2,4,'Asagi',NULL,0),(37,'443',3,4,'Kymco',NULL,0), (38,'445',1,4,'Avibus',NULL,0), (39,'446',2,4,'Kasawa',NULL,0), (40,'447',3,4,'ASAMA',NULL,0),
                          (41,'448',1,4,'Asama',NULL,0),(42,'449',3,4,'Giant',NULL,0),(43,'440',3,4,'Kymco',NULL,0),(44,'551',1,5,'Misao',NULL,0),(45,'552',2,5,'Giant',NULL,0),(46,'553',2,5,'Asagi',NULL,0),(47,'554',3,5,'Kymco',NULL,0), (48,'556',1,5,'Avibus',NULL,0), (49,'557',2,5,'Kasawa',NULL,0), (50,'558',3,5,'ASAMA',NULL,0);
/*!40000 ALTER TABLE `bike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `biketype`
--

DROP TABLE IF EXISTS `biketype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `biketype` (
  `bike_type_id` int NOT NULL,
  `bike_type_name` varchar(45) NOT NULL,
  `bike_type_deposit` int NOT NULL,
  `bike_type_base_fee` int NOT NULL,
  `bike_type_extra_fee` int NOT NULL,
  PRIMARY KEY (`bike_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biketype`
--

LOCK TABLES `biketype` WRITE;
/*!40000 ALTER TABLE `biketype` DISABLE KEYS */;
INSERT INTO `biketype` VALUES (1,'Standard bike',400000,10000,3000),(2,'Twin bike',550000,15000,4500),(3,'Standard e-bike',700000,15000,4500);
/*!40000 ALTER TABLE `biketype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dock`
--

DROP TABLE IF EXISTS `dock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dock` (
  `dock_id` int NOT NULL,
  `dock_name` varchar(45) NOT NULL,
  `dock_address` varchar(45) NOT NULL,
  `dock_total_bike` int NOT NULL,
  `dock_area` int NOT NULL,
  `dock_img_url` text,
  PRIMARY KEY (`dock_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dock`
--

LOCK TABLES `dock` WRITE;
/*!40000 ALTER TABLE `dock` DISABLE KEYS */;
INSERT INTO `dock` VALUES (1,'Ha Dong','100 Quang Trung',11,120,NULL),(2,'Thanh Xuan','182 Luong The Vinh',12,100,''),(3,'Hai Ba Trung','1 Dai Co Viet',13,90,''),(4,'Cau Giay','20 Xuan Thuy',14,110,NULL),(5,'Nam Tu Liem','12 My Dinh',15,130,NULL);
/*!40000 ALTER TABLE `dock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ebike`
--

DROP TABLE IF EXISTS `ebike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ebike` (
  `ebike_id` int NOT NULL,
  `ebike_battery` int NOT NULL,
  `ebike_license` varchar(11) NOT NULL,
  PRIMARY KEY (`ebike_id`),
  CONSTRAINT `ebike_ibfk_1` FOREIGN KEY (`ebike_id`) REFERENCES `bike` (`bike_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ebike`
--

LOCK TABLES `ebike` WRITE;
/*!40000 ALTER TABLE `ebike` DISABLE KEYS */;
INSERT INTO `ebike` VALUES (3,100,'30A90238'),(7,100,'29B67211');
/*!40000 ALTER TABLE `ebike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `iv_id` int NOT NULL AUTO_INCREMENT,
  `iv_time_rental` varchar(10) NOT NULL,
  `iv_rental_fees` int NOT NULL,
  `bike` int NOT NULL,
  `iv_pay_deposit_trx` varchar(45) DEFAULT NULL,
  `iv_refund_trx` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`iv_id`),
  KEY `bike` (`bike`),
  KEY `iv_pay_deposit_trx` (`iv_pay_deposit_trx`),
  KEY `iv_refund_trx` (`iv_refund_trx`),
  CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`bike`) REFERENCES `bike` (`bike_id`),
  CONSTRAINT `invoice_ibfk_2` FOREIGN KEY (`iv_pay_deposit_trx`) REFERENCES `transaction` (`trx_id`),
  CONSTRAINT `invoice_ibfk_3` FOREIGN KEY (`iv_refund_trx`) REFERENCES `transaction` (`trx_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (17,'00:01:34',0,5,'9485b0c79c','316e837b07'),(18,'00:03:45',0,1,'1e1155a6e1','27ab2c2fa5'),(19,'00:00:00',0,1,'74b82fda20','fbbf786c91'),(20,'00:00:33',0,1,'8a58d0c9d9','31e9b627b4'),(21,'00:00:04',0,3,'14c959c4d5','2f6e551a99'),(22,'00:00:03',0,3,'b5074cc88c','897938aa32'),(23,'00:00:05',0,7,'ddeb669968','ee4488cbfa'),(24,'00:00:01',0,6,'ef39197998','ea6bd04465'),(25,'00:00:10',0,6,'99a0c8d38f','404cbfa198'),(26,'00:00:06',0,6,'5f0d78e826','f193919e31'),(27,'00:00:08',0,6,'3838d9ed8b','40a093dfd1'),(28,'00:00:27',0,6,'a04d46c754','4a1ac9edd4'),(29,'00:00:08',0,6,'7541af29d3','a1a4775782'),(30,'00:00:04',0,6,'90e5c654b3','cd353d694a'),(31,'00:00:00',0,4,'a2819ecdb1','75caacfcec'),(32,'00:00:36',0,2,'016b2f36c2','6bc1fe37df'),(33,'00:00:27',0,1,'959acee5d2','29bcd02ee8');
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `trx_id` varchar(45) NOT NULL,
  `trx_cc_number` varchar(45) DEFAULT NULL,
  `trx_cc_owner` varchar(45) DEFAULT NULL,
  `trx_date_expired` varchar(5) DEFAULT NULL,
  `trx_command` varchar(45) DEFAULT NULL,
  `trx_content` varchar(45) DEFAULT NULL,
  `trx_amount` int NOT NULL,
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`trx_id`),
  KEY `trx_cc_number` (`trx_cc_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES ('016b2f36c2','4242424242424242','Group 13','08/27','pay','abcxyz',550000,'2023-08-16 17:37:46'),('14c959c4d5','1234567','Group 13','08/21','pay','abcxyz',700000,'2023-08-13 01:39:37'),('1bf22088a4','4242424242424242','Group 13','08/27','pay','Pay deposit',400000,'2023-08-16 21:47:14'),('1e1155a6e1','4242424242424242','Group 13','08/27','pay','abcxyz',400000,'2023-08-12 19:25:26'),('27ab2c2fa5','4242424242424242','Group 13','08/27','refund','request refund',400000,'2023-08-12 19:29:19'),('29bcd02ee8','4242424242424242','Group 13','08/27','refund','request refund',400000,'2023-08-16 22:29:00'),('2f6e551a99','1234567','Group 13','08/21','refund','request refund',700000,'2023-08-13 01:39:51'),('316e837b07','4242424242424242','Group 13','08/27','refund','request refund',550000,'2023-08-12 18:21:04'),('31d9c67fb9','4242424242424242','Group 13','08/27','pay','deposit',400000,'2023-08-16 22:06:08'),('31e9b627b4','4242424242424242','Group 13','08/27','refund','request refund',400000,'2023-08-13 01:24:20'),('3838d9ed8b','4242424242424242','Group 13','08/27','pay','abcxyz',550000,'2023-08-15 01:56:38'),('404cbfa198','4242424242424242','Group 13','08/27','refund','request refund',550000,'2023-08-15 01:51:10'),('40a093dfd1','4242424242424242','Group 13','08/27','refund','request refund',550000,'2023-08-15 01:56:55'),('4a1ac9edd4','131313','Group 13','08/27','refund','request refund',550000,'2023-08-15 10:13:40'),('5f0d78e826','4242424242424242','Group 13','08/27','pay','abcxyz',550000,'2023-08-15 01:51:42'),('650fddb1e5','4242424242424242','Group 13','08/27','pay','abcxyz',700000,'2023-08-16 16:55:18'),('6bc1fe37df','4242424242424242','Group 13','08/27','refund','request refund',550000,'2023-08-16 17:38:29'),('74b82fda20','4242424242424242','Group 13','08/27','pay','abcxyz',400000,'2023-08-12 19:39:22'),('7541af29d3','4242424242424242','Group 13','08/27','pay','abcxyz',550000,'2023-08-15 16:04:01'),('75caacfcec','4242424242424242','Group 13','08/27','refund','request refund',400000,'2023-08-16 17:04:36'),('897938aa32','4242424242424242','Group 13','08/27','refund','request refund',700000,'2023-08-13 01:40:18'),('8a58d0c9d9','4242424242424242','Group 13','08/27','pay','abcxyz',400000,'2023-08-13 01:23:39'),('90e5c654b3','4242424242424242','Group 13','08/27','pay','abcxyz',550000,'2023-08-15 16:05:10'),('9485b0c79c','4242424242424242','Group 13','08/27','pay','abcxyz',550000,'2023-08-12 18:19:05'),('959acee5d2','4242424242424242','Group 13','08/27','pay','pay deposit',400000,'2023-08-16 22:28:23'),('99a0c8d38f','4242424242424242','Group 13','08/27','pay','abcxyz',550000,'2023-08-15 01:50:53'),('a04d46c754','131313','Group 13','08/27','pay','abcxyz',550000,'2023-08-15 10:13:03'),('a1a4775782','4242424242424242','Group 13','08/27','refund','request refund',550000,'2023-08-15 16:04:24'),('a2819ecdb1','4242424242424242','Group 13','08/27','pay','abcxyz',400000,'2023-08-16 17:04:26'),('b5074cc88c','4242424242424242','Group 13','08/27','pay','abcxyz',700000,'2023-08-13 01:40:09'),('cd353d694a','4242424242424242','Group 13','08/27','refund','request refund',550000,'2023-08-15 16:05:20'),('d0e6fc3a73','4242424242424242','Group 13','08/27','pay','abcxyz',700000,'2023-08-16 16:52:42'),('ddeb669968','4242424242424242','Group 13','08/27','pay','abcxyz',700000,'2023-08-13 23:09:08'),('ea6bd04465','4242424242424242','Group 13','08/27','refund','request refund',550000,'2023-08-15 01:01:41'),('ee4488cbfa','4242424242424242','Group 13','08/27','refund','request refund',700000,'2023-08-13 23:09:18'),('ef39197998','4242424242424242','Group 13','08/27','pay','abcxyz',550000,'2023-08-15 01:01:36'),('f193919e31','4242424242424242','Group 13','08/27','refund','request refund',550000,'2023-08-15 01:51:53'),('fbbf786c91','4242424242424242','Group 13','08/27','refund','request refund',400000,'2023-08-12 19:39:31');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-18 23:57:02
