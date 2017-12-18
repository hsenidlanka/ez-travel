-- MySQL dump 10.13  Distrib 5.6.31, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: ez-travel
-- ------------------------------------------------------
-- Server version	5.6.31-0ubuntu0.15.10.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `contact_number` varchar(20) DEFAULT NULL,
  `nic` varchar(10) DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `user_status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (18,'menuka@gmail.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','Menuka','Ishan','2015-03-31','0716542398255','910455658v','Male',1),(20,'menuka2@gmail.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','Menuka','Ishan','2015-03-31','0716542398255','910455658v','Male',0),(21,'menuka3@gmail.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','Menuka','Ishan','2015-03-31','0716542398255','910455658v','Male',1),(22,'menuka11@gmail.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','Menuka','Ishan','1991-03-16','0716542398255','910455658v','Male',1),(24,'menuka112@gmail.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','Menuka','Ishan','1991-03-16','0716542398255','910455658v','Male',1),(25,'menuka11dfgh2@gmail.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','Menuka','Ishan','1991-03-16','0716542398255','910455658v','Male',1),(26,'fgh@gmail.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','Menuka','Ishan','1991-03-16','0716542398255','910455658v','Male',1),(27,'Test@Menfluka.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','First','Last','1993-11-18','0716548523','912345687v','Male',1),(28,'Test@Menuka.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','First','Last','1993-11-18','0716548523','912345687v','Male',1),(29,'Test@Menuka.csom','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','First','Last','1993-11-18','0716548523','912345687v','Male',1),(31,'Tesmnt@Menuka.csom','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','First','Last','1993-11-18','0716548523','912345687v','Male',1),(32,'Tesmnt@Mxenuka.csom','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','First','Last','1993-11-18','0716548523','912345687v','Male',1),(33,'Tesdmnt@Mxenfddfdduka.csom','password','First','Last','1993-11-18','0716548523','912345687v','Male',1);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_current_location`
--

DROP TABLE IF EXISTS `customer_current_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_current_location` (
  `customer_id` int(10) unsigned NOT NULL DEFAULT '0',
  `longitude` float(10,6) DEFAULT NULL,
  `latitude` float(10,6) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_current_location`
--

LOCK TABLES `customer_current_location` WRITE;
/*!40000 ALTER TABLE `customer_current_location` DISABLE KEYS */;
INSERT INTO `customer_current_location` VALUES (18,0.000000,0.000000,'2017-09-08 16:28:00'),(20,0.000000,0.000000,'2017-09-08 17:35:34'),(21,0.000000,0.000000,'2017-09-08 17:49:27'),(22,0.000000,0.000000,'2017-09-08 17:49:57'),(24,0.000000,0.000000,'2017-09-11 09:34:53'),(25,0.000000,0.000000,'2017-09-11 09:39:37'),(26,0.000000,0.000000,'2017-09-11 09:40:18'),(27,0.000000,0.000000,'2017-09-18 08:59:59'),(28,0.000000,0.000000,'2017-09-18 09:01:10'),(29,0.000000,0.000000,'2017-09-18 09:04:27'),(31,0.000000,0.000000,'2017-09-18 09:56:51'),(32,0.000000,0.000000,'2017-09-18 10:13:22'),(33,0.000000,0.000000,'2017-12-13 16:40:30');
/*!40000 ALTER TABLE `customer_current_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `driver` (
  `driver_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `license_number` varchar(20) DEFAULT NULL,
  `contact_number` varchar(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `driver_status` tinyint(1) DEFAULT NULL,
  `confirmed_by` int(11) DEFAULT NULL,
  `nic` varchar(10) NOT NULL,
  PRIMARY KEY (`driver_id`),
  KEY `confirmed_by_idx` (`confirmed_by`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
INSERT INTO `driver` VALUES (3,'Tesdbmnt@Mxenuka.csom','password','First','Last','111dff3','0716548523','1993-11-18','Male',2,2,'912345687v'),(5,'Tesdxbmnt@Mxenuka.csom','password','First','Last','111dff3','0716548523','1993-11-18','Male',2,2,'912345687v'),(6,'Menuka@Ishan.csom','xxxxBug','First','Last','111dff3','0716548523','1993-11-18','Male',2,2,'912345687v'),(8,'ssss@Ishan.csom','TestNewPass','First','Last','111dff3','0716548523','1993-11-18','Male',2,2,'912345687v'),(10,'ssss@Ishfan.csom','password','Tgest','xxxxBug','111dff3','0714561238','1993-11-18','Male',2,NULL,'912345687v'),(14,'vidu@vidya.csom','password','First','Last','111dff3','0716548523','1993-11-18','Male',2,NULL,'912345687v');
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driver_current_location`
--

DROP TABLE IF EXISTS `driver_current_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `driver_current_location` (
  `driver_id` int(11) NOT NULL,
  `longitude` float(10,6) DEFAULT NULL,
  `latitude` float(10,6) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`driver_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver_current_location`
--

LOCK TABLES `driver_current_location` WRITE;
/*!40000 ALTER TABLE `driver_current_location` DISABLE KEYS */;
INSERT INTO `driver_current_location` VALUES (3,63.034000,18.230000,'2017-12-14 15:01:41'),(5,0.000000,0.000000,'2017-12-13 15:35:44'),(6,0.000000,0.000000,'2017-12-13 15:36:04'),(7,0.000000,0.000000,'2017-12-13 15:40:35'),(8,0.000000,0.000000,'2017-12-13 15:43:06'),(10,0.000000,0.000000,'2017-12-13 15:32:41'),(12,0.000000,0.000000,'2017-12-13 15:49:42'),(13,0.000000,0.000000,'2017-12-13 09:25:56'),(14,0.000000,0.000000,'2017-12-13 09:26:13');
/*!40000 ALTER TABLE `driver_current_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driver_feedback`
--

DROP TABLE IF EXISTS `driver_feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `driver_feedback` (
  `feedback_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL,
  `customer_id` int(11) NOT NULL,
  `driver_id` int(11) NOT NULL,
  `hire_id` int(11) NOT NULL,
  `feedback_status` tinyint(1) NOT NULL,
  PRIMARY KEY (`feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver_feedback`
--

LOCK TABLES `driver_feedback` WRITE;
/*!40000 ALTER TABLE `driver_feedback` DISABLE KEYS */;
INSERT INTO `driver_feedback` VALUES (1,'Ez-travel is the best app',3,3,2,0),(2,'Ez-travel is the best app',3,3,2,0);
/*!40000 ALTER TABLE `driver_feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driver_image`
--

DROP TABLE IF EXISTS `driver_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `driver_image` (
  `driver_id` int(11) NOT NULL,
  `driver_licence` longblob,
  `revenue_licence` longblob,
  `insurance` longblob,
  `driver_image` longblob,
  PRIMARY KEY (`driver_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver_image`
--

LOCK TABLES `driver_image` WRITE;
/*!40000 ALTER TABLE `driver_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `driver_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `is_admin` tinyint(1) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `nic` varchar(10) DEFAULT NULL,
  `contact_number` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `feedback_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text,
  `customer_id` int(11) DEFAULT NULL,
  `driver_id` int(11) DEFAULT NULL,
  `hire_id` int(11) DEFAULT NULL,
  `feedback_status` tinyint(1) NOT NULL,
  PRIMARY KEY (`feedback_id`),
  KEY `customer_id_idx` (`customer_id`),
  KEY `driver_id_idx` (`driver_id`),
  KEY `hire_id_idx` (`hire_id`),
  CONSTRAINT `driver_idy` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`driver_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `hire_idy` FOREIGN KEY (`hire_id`) REFERENCES `hire` (`hire_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,'Ez-travel is the best app',18,3,2,0),(2,'Ez-travel is the best app',3,3,2,0),(3,'Ez-travel is the best app',3,3,2,0);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hire`
--

DROP TABLE IF EXISTS `hire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hire` (
  `hire_id` int(11) NOT NULL AUTO_INCREMENT,
  `start_location_latitude` float(10,6) DEFAULT NULL,
  `start_location_longitude` float(10,6) DEFAULT NULL,
  `end_location_latitude` float(10,6) DEFAULT NULL,
  `end_location_longitude` float(10,6) DEFAULT NULL,
  `vehicle_type` varchar(10) DEFAULT NULL,
  `length` double DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` varchar(200) DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  `driver_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`hire_id`),
  KEY `customer_id_idx` (`customer_id`),
  KEY `driver_id_idx` (`driver_id`),
  CONSTRAINT `driver_idx` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`driver_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hire`
--

LOCK TABLES `hire` WRITE;
/*!40000 ALTER TABLE `hire` DISABLE KEYS */;
INSERT INTO `hire` VALUES (1,63.034000,18.230000,4.738459,15.283740,'van',18,124.22,'1993-11-18',NULL,18,8),(2,63.034000,18.230000,NULL,NULL,'van',NULL,NULL,'1993-11-18',NULL,26,NULL),(4,63.034000,18.230000,NULL,NULL,'budget',NULL,NULL,'1993-11-18',NULL,31,NULL),(5,63.034000,18.230000,NULL,NULL,'van',NULL,NULL,'1993-11-18',NULL,21,NULL),(6,83.433998,58.230000,NULL,NULL,'hybrid',NULL,NULL,'1993-11-18','12:45',32,NULL),(7,55.764000,23.830000,NULL,NULL,'hybrid',NULL,NULL,'1993-11-18','12:45',26,NULL);
/*!40000 ALTER TABLE `hire` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-18 16:01:15
