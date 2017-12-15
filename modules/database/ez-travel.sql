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
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (18,'menuka@gmail.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','Menuka','Ishan','2015-03-31','0716542398255','910455658v','Male',1),(20,'menuka2@gmail.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','Menuka','Ishan','2015-03-31','0716542398255','910455658v','Male',0),(21,'menuka3@gmail.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','Menuka','Ishan','2015-03-31','0716542398255','910455658v','Male',1),(22,'menuka11@gmail.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','Menuka','Ishan','1991-03-16','0716542398255','910455658v','Male',1),(24,'menuka112@gmail.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','Menuka','Ishan','1991-03-16','0716542398255','910455658v','Male',1),(25,'menuka11dfgh2@gmail.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','Menuka','Ishan','1991-03-16','0716542398255','910455658v','Male',1),(26,'fgh@gmail.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','Menuka','Ishan','1991-03-16','0716542398255','910455658v','Male',1),(27,'Test@Menfluka.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','First','Last','1993-11-18','0716548523','912345687v','Male',1),(28,'Test@Menuka.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','First','Last','1993-11-18','0716548523','912345687v','Male',1),(29,'Test@Menuka.csom','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','First','Last','1993-11-18','0716548523','912345687v','Male',1),(31,'Tesmnt@Menuka.csom','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','First','Last','1993-11-18','0716548523','912345687v','Male',1),(32,'Tesmnt@Mxenuka.csom','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','First','Last','1993-11-18','0716548523','912345687v','Male',1),(33,'Tesdmnt@Mxenfddfdduka.csom','password','First','Last','1993-11-18','0716548523','912345687v','Male',1);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `customer_current_location`
--

LOCK TABLES `customer_current_location` WRITE;
/*!40000 ALTER TABLE `customer_current_location` DISABLE KEYS */;
INSERT INTO `customer_current_location` VALUES (18,0.000000,0.000000,'2017-09-08 16:28:00'),(20,0.000000,0.000000,'2017-09-08 17:35:34'),(21,0.000000,0.000000,'2017-09-08 17:49:27'),(22,0.000000,0.000000,'2017-09-08 17:49:57'),(24,0.000000,0.000000,'2017-09-11 09:34:53'),(25,0.000000,0.000000,'2017-09-11 09:39:37'),(26,0.000000,0.000000,'2017-09-11 09:40:18'),(27,0.000000,0.000000,'2017-09-18 08:59:59'),(28,0.000000,0.000000,'2017-09-18 09:01:10'),(29,0.000000,0.000000,'2017-09-18 09:04:27'),(31,0.000000,0.000000,'2017-09-18 09:56:51'),(32,0.000000,0.000000,'2017-09-18 10:13:22'),(33,0.000000,0.000000,'2017-12-13 16:40:30');
/*!40000 ALTER TABLE `customer_current_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
INSERT INTO `driver` VALUES (3,'Tesdbmnt@Mxenuka.csom','password','First','Last','111dff3','0716548523','1993-11-18','Male',2,2,'912345687v'),(5,'Tesdxbmnt@Mxenuka.csom','password','First','Last','111dff3','0716548523','1993-11-18','Male',2,2,'912345687v'),(6,'Menuka@Ishan.csom','password','First','Last','111dff3','0716548523','1993-11-18','Male',2,2,'912345687v'),(8,'ssss@Ishan.csom','password','First','Last','111dff3','0716548523','1993-11-18','Male',2,2,'912345687v'),(10,'ssss@Ishfan.csom','password','Tgest','xxxxBug','111dff3','0714561238','1993-11-18','Male',2,NULL,'912345687v'),(14,'vidu@vidya.csom','password','First','Last','111dff3','0716548523','1993-11-18','Male',2,NULL,'912345687v');
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `driver_current_location`
--

LOCK TABLES `driver_current_location` WRITE;
/*!40000 ALTER TABLE `driver_current_location` DISABLE KEYS */;
INSERT INTO `driver_current_location` VALUES (3,63.034000,18.230000,'2017-12-14 15:01:41'),(5,0.000000,0.000000,'2017-12-13 15:35:44'),(6,0.000000,0.000000,'2017-12-13 15:36:04'),(7,0.000000,0.000000,'2017-12-13 15:40:35'),(8,0.000000,0.000000,'2017-12-13 15:43:06'),(10,0.000000,0.000000,'2017-12-13 15:32:41'),(12,0.000000,0.000000,'2017-12-13 15:49:42'),(13,0.000000,0.000000,'2017-12-13 09:25:56'),(14,0.000000,0.000000,'2017-12-13 09:26:13');
/*!40000 ALTER TABLE `driver_current_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `driver_image`
--

LOCK TABLES `driver_image` WRITE;
/*!40000 ALTER TABLE `driver_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `driver_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hire`
--

LOCK TABLES `hire` WRITE;
/*!40000 ALTER TABLE `hire` DISABLE KEYS */;
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

-- Dump completed on 2017-12-14 17:13:56
