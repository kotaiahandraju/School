-- MySQL dump 10.13  Distrib 5.5.44, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: ndb
-- ------------------------------------------------------
-- Server version	5.5.44-0ubuntu0.14.04.1

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
-- Table structure for table `status_list1`
--

DROP TABLE IF EXISTS `status_list1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status_list1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` timestamp NULL DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='The list of statuses ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_list1`
--

LOCK TABLES `status_list1` WRITE;
/*!40000 ALTER TABLE `status_list1` DISABLE KEYS */;
INSERT INTO `status_list1` VALUES (1,'2015-08-10 09:29:04','2015-08-10 09:29:04','Confirmed'),(2,'2015-08-10 09:29:04','2015-08-10 09:29:04','Assigned'),(3,'2015-08-10 09:29:04','2015-08-10 09:29:04','Ordered'),(4,'2015-08-10 09:29:04','2015-08-10 09:29:04','Cancel'),(5,'2015-08-10 09:29:04','2015-08-10 09:29:04','Paid'),(6,'2015-08-10 09:29:04','2015-08-10 09:29:04','Reject'),(7,'2015-08-10 09:29:04','2015-08-10 09:29:04','Sample Collected'),(8,'2015-08-10 09:29:04','2015-08-10 09:29:04','Reports Uploaded'),(9,'2015-08-10 09:29:04','2015-08-10 09:29:04','Closed'),(10,'2015-08-10 09:29:04','2015-08-10 09:29:04','Reports Couriered'),(11,'2015-08-10 09:29:04','2015-08-10 09:29:04','Lab Test In Progress'),(12,'2015-08-13 09:22:10','2015-08-13 09:22:10','Testing'),(13,'2015-08-14 07:45:37','2015-08-14 07:45:37','Reached Site'),(14,'2015-08-14 07:45:37','2015-08-14 07:45:37','En Route'),(16,'2015-08-10 09:29:17','2015-08-10 09:29:17','Work in Progress'),(17,'2015-08-10 09:29:16','2015-08-10 09:29:16','Inspection Completed'),(18,'2015-08-10 09:29:16','2015-08-10 09:29:16','Order Completed'),(19,'2015-08-10 09:29:06','2015-08-10 09:29:06','Price On Inspection');
/*!40000 ALTER TABLE `status_list1` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-14 13:16:04
