CREATE DATABASE  IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `test`;
-- MySQL dump 10.13  Distrib 5.5.16, for osx10.5 (i386)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.5.13

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
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `state` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `stateName` varchar(45) DEFAULT NULL,
  `code` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state`
--

LOCK TABLES `state` WRITE;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` VALUES (5,'Alabama',1),(6,'Arizona',2),(7,'Arkansas',3),(8,'California',4),(9,'Colorado',5),(10,'Connecticut',6),(11,'Delaware',7),(12,'Florida',8),(13,'Georgia',9),(14,'Idaho',10),(15,'Illinois',11),(16,'Indiana',12),(17,'Iowa',13),(18,'Kansas',14),(19,'Kentucky',15),(20,'Louisiana',16),(21,'Maine',17),(22,'Maryland',18),(23,'Massachusetts',19),(24,'Michigan',20),(25,'Minnesota',21),(26,'Mississippi',22),(27,'Missouri',23),(28,'Montana',24),(29,'Nebraska',25),(30,'Nevada',26),(31,'New Hampshire',27),(32,'New Jersey',28),(33,'New Mexico',29),(34,'New York',30),(35,'North Carolina',31),(36,'North Dakota',32),(37,'Ohio',33),(38,'Oklahoma',34),(39,'Oregon',35),(40,'Pennsylvania',36),(41,'Rhode Island',37),(42,'South Carolina',38),(43,'South Dakota',39),(44,'Tennessee',40),(45,'Texas',41),(46,'Utah',42),(47,'Vermont',43),(48,'Virginia',44),(49,'Washington',45),(50,'West Virginia',46),(51,'Wisconsin',47),(52,'Wyoming',48),(53,'Northeast Region',101),(54,'East North Central Region',102),(55,'Central Region',103),(56,'Southeast Region',104),(57,'West North Central Region',105),(58,'South Region',106),(59,'Southwest Region',107),(60,'Northwest Region',108),(61,'West Region',109),(62,'National',110);
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-21 16:04:31
