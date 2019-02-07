CREATE DATABASE  IF NOT EXISTS `airlinedb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `airlinedb`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: airlinedb
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `airline_company`
--

DROP TABLE IF EXISTS `airline_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airline_company` (
  `idairline_company` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `webSite` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`idairline_company`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airline_company`
--

LOCK TABLES `airline_company` WRITE;
/*!40000 ALTER TABLE `airline_company` DISABLE KEYS */;
INSERT INTO `airline_company` VALUES (1,'British Airways','Waterside PO Box 365 Harmondsworth','United Kingdom','britishairways.com','britishairways@gmail.com'),(2,'Lufthansa','Am Flugplatz 64','Germany','www.lufthansa.com','lufthansa@gmail.com'),(3,'American Airlines','4333 Amon Carter BoulevardFort','United States','www.americanairlines.com','americanairlines@gmail.com'),(4,'United Airlines','233 S. Wacker Drive Chicago, IL 60606','United States','www.unitedairlines.com','unitedairlines@gmail.com'),(5,'Turkish Airlines','Türk Hava Yolları Yatırımcı İlişkileri','Turkey','www.ta.com','ta@gmail.com');
/*!40000 ALTER TABLE `airline_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airplane`
--

DROP TABLE IF EXISTS `airplane`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airplane` (
  `idairplane` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `airplane_type` int(11) NOT NULL,
  `airplane_comp` int(11) NOT NULL,
  PRIMARY KEY (`idairplane`),
  KEY `airplane_type_idx` (`airplane_type`),
  KEY `airplane_comp_idx` (`airplane_comp`),
  CONSTRAINT `airplane_comp` FOREIGN KEY (`airplane_comp`) REFERENCES `airline_company` (`idairline_company`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `airplane_type` FOREIGN KEY (`airplane_type`) REFERENCES `airplane_types` (`idairplane_types`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airplane`
--

LOCK TABLES `airplane` WRITE;
/*!40000 ALTER TABLE `airplane` DISABLE KEYS */;
INSERT INTO `airplane` VALUES (1,'Nikola Tesla',1,1),(2,'Aircraft',1,2),(3,'Aircraft2',1,3),(4,'Aircraft3',1,2);
/*!40000 ALTER TABLE `airplane` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airplane_licence`
--

DROP TABLE IF EXISTS `airplane_licence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airplane_licence` (
  `idairplane_licence` int(11) NOT NULL AUTO_INCREMENT,
  `licence` varchar(45) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`idairplane_licence`),
  KEY `type_idx` (`type`),
  CONSTRAINT `type` FOREIGN KEY (`type`) REFERENCES `airplane_types` (`idairplane_types`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airplane_licence`
--

LOCK TABLES `airplane_licence` WRITE;
/*!40000 ALTER TABLE `airplane_licence` DISABLE KEYS */;
INSERT INTO `airplane_licence` VALUES (1,'AA-1234567',1),(2,'ZZ-7654321',2);
/*!40000 ALTER TABLE `airplane_licence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airplane_maker`
--

DROP TABLE IF EXISTS `airplane_maker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airplane_maker` (
  `idairplane_maker` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  PRIMARY KEY (`idairplane_maker`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airplane_maker`
--

LOCK TABLES `airplane_maker` WRITE;
/*!40000 ALTER TABLE `airplane_maker` DISABLE KEYS */;
INSERT INTO `airplane_maker` VALUES (1,'Boeing','Cikago','SAD'),(2,'Airbus SAS','Tuluz','Francuska'),(3,'Embraer','Sao Paolo','Brazil'),(4,'Tupolev','Moskva','Rusija');
/*!40000 ALTER TABLE `airplane_maker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airplane_types`
--

DROP TABLE IF EXISTS `airplane_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airplane_types` (
  `idairplane_types` int(11) NOT NULL AUTO_INCREMENT,
  `maker` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `licence` varchar(45) NOT NULL,
  `pass_num` int(11) NOT NULL,
  PRIMARY KEY (`idairplane_types`),
  KEY `maker_idx` (`maker`),
  CONSTRAINT `maker` FOREIGN KEY (`maker`) REFERENCES `airplane_maker` (`idairplane_maker`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airplane_types`
--

LOCK TABLES `airplane_types` WRITE;
/*!40000 ALTER TABLE `airplane_types` DISABLE KEYS */;
INSERT INTO `airplane_types` VALUES (1,1,'Airbus','BR',100),(2,1,'Fast','BR',50);
/*!40000 ALTER TABLE `airplane_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airport`
--

DROP TABLE IF EXISTS `airport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airport` (
  `idairport` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `IATA` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `runway_num` int(11) NOT NULL,
  `terminal_num` int(11) NOT NULL,
  PRIMARY KEY (`idairport`),
  UNIQUE KEY `IATA_UNIQUE` (`IATA`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airport`
--

LOCK TABLES `airport` WRITE;
/*!40000 ALTER TABLE `airport` DISABLE KEYS */;
INSERT INTO `airport` VALUES (2,'Logan','2222','Boston','US',2,3),(3,'JFK','3333','New York','US',2,3),(4,'Hetrow','2203','London','United Kingdom',3,6),(6,'Nikola Tesla','1180','Belgrade','Serbia',3,3),(7,'NovAeorodrom','9999','Nis','Serbia',2,2);
/*!40000 ALTER TABLE `airport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airport_cord`
--

DROP TABLE IF EXISTS `airport_cord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airport_cord` (
  `idairport_cord` int(11) NOT NULL AUTO_INCREMENT,
  `airport` int(11) NOT NULL,
  `lat` double NOT NULL,
  `lng` double NOT NULL,
  PRIMARY KEY (`idairport_cord`),
  KEY `airport_idx` (`airport`),
  CONSTRAINT `airport` FOREIGN KEY (`airport`) REFERENCES `airport` (`idairport`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airport_cord`
--

LOCK TABLES `airport_cord` WRITE;
/*!40000 ALTER TABLE `airport_cord` DISABLE KEYS */;
INSERT INTO `airport_cord` VALUES (3,6,44.7866,20.4489),(4,4,51.5074,-0.1278),(5,3,40.7128,-74.0059),(6,2,42.3601,-71.0589);
/*!40000 ALTER TABLE `airport_cord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flight` (
  `idflight` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL,
  `start_airport` int(11) NOT NULL,
  `end_airport` int(11) NOT NULL,
  `airplane` int(11) NOT NULL,
  `duration` varchar(45) NOT NULL,
  `expect_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `start_gate` int(11) NOT NULL,
  `end_gate` int(11) NOT NULL,
  `unexp_airport` int(11) DEFAULT NULL,
  `carter` tinyint(4) DEFAULT NULL,
  `canceled` tinyint(4) DEFAULT NULL,
  `reservation` int(11) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `planned_date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `planned_time` time DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`idflight`),
  KEY `start_airoport_idx` (`start_airport`),
  KEY `end_airport_idx` (`end_airport`),
  KEY `airplane_idx` (`airplane`),
  KEY `unexp_airport_idx` (`unexp_airport`),
  CONSTRAINT `airplane` FOREIGN KEY (`airplane`) REFERENCES `airplane` (`idairplane`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `end_airport` FOREIGN KEY (`end_airport`) REFERENCES `airport` (`idairport`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `start_airoport` FOREIGN KEY (`start_airport`) REFERENCES `airport` (`idairport`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `unexp_airport` FOREIGN KEY (`unexp_airport`) REFERENCES `airport` (`idairport`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES (31,1,6,3,1,'1h:30min','2017-09-01 16:00:00','2017-09-01 16:00:00',4,10,NULL,0,0,98,'2017-09-01','2017-09-01','14:30:00','16:00:00',0),(32,2,3,6,1,'1h:30min','2017-09-02 16:00:00','2017-09-02 16:00:00',10,4,NULL,0,0,99,'2017-09-02','2017-09-02','14:30:00','16:00:00',0),(33,3,6,4,1,'1h:30min','2017-09-01 13:00:00','2017-09-01 13:00:00',4,16,NULL,0,0,99,'2017-09-01','2017-09-01','11:30:00','13:00:00',0),(34,4,4,3,1,'1h:30min','2017-09-01 17:00:00','2017-09-01 17:00:00',16,11,NULL,0,0,99,'2017-09-01','2017-09-01','15:30:00','17:00:00',0),(35,5,3,4,1,'1h:30min','2017-09-02 13:00:00','2017-09-02 13:00:00',15,17,NULL,0,0,100,'2017-09-02','2017-09-02','11:30:00','13:00:00',0),(36,6,4,6,1,'1h:30min','2017-09-02 18:00:00','2017-09-02 18:00:00',17,4,NULL,0,0,100,'2017-09-02','2017-09-02','16:30:00','18:00:00',0),(39,8,2,3,1,'12h:30min','2017-08-29 06:00:00','2017-08-28 17:33:46',22,11,NULL,0,1,100,'2017-08-28','2017-08-29','17:30:00','06:00:00',3),(40,9,2,3,1,'12h:30min','2017-08-28 06:00:00','2017-08-28 06:00:00',22,11,NULL,0,0,100,'2017-08-27','2017-08-28','17:30:00','06:00:00',0),(41,10,6,4,1,'12h:30min','2017-09-01 06:00:00','2017-09-01 06:00:00',6,20,NULL,0,0,100,'2017-08-31','2017-09-01','17:30:00','06:00:00',0),(42,11,6,4,1,'12h:30min','2017-08-31 06:00:00','2017-08-31 06:00:00',6,20,NULL,0,0,100,'2017-08-30','2017-08-31','17:30:00','06:00:00',0),(43,12,6,4,1,'12h:30min','2017-09-05 06:00:00','2017-09-05 06:00:00',6,20,NULL,0,0,100,'2017-09-04','2017-09-05','17:30:00','06:00:00',0),(44,13,6,4,1,'12h:30min','2017-08-30 06:00:00','2017-08-30 06:00:00',6,20,NULL,0,0,100,'2017-08-29','2017-08-30','17:30:00','06:00:00',0),(45,14,6,4,1,'12h:30min','2017-08-29 06:00:00','2017-08-29 06:00:00',6,20,NULL,0,0,100,'2017-08-28','2017-08-29','17:30:00','06:00:00',0);
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight_crew`
--

DROP TABLE IF EXISTS `flight_crew`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flight_crew` (
  `idflight_crew` int(11) NOT NULL AUTO_INCREMENT,
  `job` int(11) NOT NULL,
  `let` int(11) NOT NULL,
  `korisnik` int(11) NOT NULL,
  PRIMARY KEY (`idflight_crew`),
  KEY `let_idx` (`let`),
  KEY `korisnik_idx` (`korisnik`),
  CONSTRAINT `korisnik` FOREIGN KEY (`korisnik`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `let` FOREIGN KEY (`let`) REFERENCES `flight` (`idflight`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight_crew`
--

LOCK TABLES `flight_crew` WRITE;
/*!40000 ALTER TABLE `flight_crew` DISABLE KEYS */;
INSERT INTO `flight_crew` VALUES (10,0,31,23),(11,0,39,23),(12,2,39,24),(13,2,31,24);
/*!40000 ALTER TABLE `flight_crew` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gates`
--

DROP TABLE IF EXISTS `gates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gates` (
  `idgates` int(11) NOT NULL AUTO_INCREMENT,
  `airport` int(11) NOT NULL,
  `terminal` varchar(45) NOT NULL,
  `gate_name` varchar(45) NOT NULL,
  PRIMARY KEY (`idgates`),
  KEY `airoport_idx` (`airport`),
  CONSTRAINT `airoport` FOREIGN KEY (`airport`) REFERENCES `airport` (`idairport`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gates`
--

LOCK TABLES `gates` WRITE;
/*!40000 ALTER TABLE `gates` DISABLE KEYS */;
INSERT INTO `gates` VALUES (4,6,'T1','A1'),(5,6,'T1','A2'),(6,6,'T2','A1'),(7,6,'T2','A2'),(8,6,'T3','A2'),(9,6,'T3','A1'),(10,3,'T3','A1'),(11,3,'T3','A2'),(12,3,'T2','A2'),(13,3,'T2','A1'),(14,3,'T1','A1'),(15,3,'T1','A2'),(16,4,'T1','A1'),(17,4,'T2','A1'),(18,4,'T3','A1'),(19,4,'T4','A1'),(20,4,'T5','A1'),(21,4,'T6','A1'),(22,2,'T1','A1'),(23,2,'T2','A1'),(24,2,'T3','A1'),(25,7,'T1','A1');
/*!40000 ALTER TABLE `gates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `made_final`
--

DROP TABLE IF EXISTS `made_final`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `made_final` (
  `idmade_final` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `tip` varchar(45) NOT NULL,
  PRIMARY KEY (`idmade_final`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `made_final`
--

LOCK TABLES `made_final` WRITE;
/*!40000 ALTER TABLE `made_final` DISABLE KEYS */;
INSERT INTO `made_final` VALUES (4,'Airbus33','1'),(5,'Airbus55','1'),(6,'Airbus77','1');
/*!40000 ALTER TABLE `made_final` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `napravljeni`
--

DROP TABLE IF EXISTS `napravljeni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `napravljeni` (
  `idnapravljeni` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`idnapravljeni`),
  KEY `tipski_idx` (`type`),
  CONSTRAINT `tipski` FOREIGN KEY (`type`) REFERENCES `airplane_types` (`idairplane_types`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `napravljeni`
--

LOCK TABLES `napravljeni` WRITE;
/*!40000 ALTER TABLE `napravljeni` DISABLE KEYS */;
INSERT INTO `napravljeni` VALUES (3,'Lenovo',1),(4,'Bla',1);
/*!40000 ALTER TABLE `napravljeni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pilot_licence`
--

DROP TABLE IF EXISTS `pilot_licence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pilot_licence` (
  `idpilot_licence` int(11) NOT NULL AUTO_INCREMENT,
  `pilot` int(11) NOT NULL,
  `licence` int(11) NOT NULL,
  PRIMARY KEY (`idpilot_licence`),
  KEY `pilot_idx` (`pilot`),
  KEY `licence_idx` (`licence`),
  CONSTRAINT `licence` FOREIGN KEY (`licence`) REFERENCES `airplane_licence` (`idairplane_licence`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pilot` FOREIGN KEY (`pilot`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pilot_licence`
--

LOCK TABLES `pilot_licence` WRITE;
/*!40000 ALTER TABLE `pilot_licence` DISABLE KEYS */;
INSERT INTO `pilot_licence` VALUES (5,23,1);
/*!40000 ALTER TABLE `pilot_licence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ponude`
--

DROP TABLE IF EXISTS `ponude`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ponude` (
  `idponude` int(11) NOT NULL AUTO_INCREMENT,
  `kupac` int(11) NOT NULL,
  `prodavac` int(11) NOT NULL,
  `avion` int(11) NOT NULL,
  `ponuda` double NOT NULL,
  `odobreno` int(11) NOT NULL,
  PRIMARY KEY (`idponude`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ponude`
--

LOCK TABLES `ponude` WRITE;
/*!40000 ALTER TABLE `ponude` DISABLE KEYS */;
INSERT INTO `ponude` VALUES (6,1,2,2,350,0),(7,2,1,1,250,0),(8,3,1,1,222,0);
/*!40000 ALTER TABLE `ponude` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proba`
--

DROP TABLE IF EXISTS `proba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proba` (
  `idproba` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idproba`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proba`
--

LOCK TABLES `proba` WRITE;
/*!40000 ALTER TABLE `proba` DISABLE KEYS */;
/*!40000 ALTER TABLE `proba` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `radar_center`
--

DROP TABLE IF EXISTS `radar_center`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `radar_center` (
  `idradar_center` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  PRIMARY KEY (`idradar_center`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `radar_center`
--

LOCK TABLES `radar_center` WRITE;
/*!40000 ALTER TABLE `radar_center` DISABLE KEYS */;
INSERT INTO `radar_center` VALUES (1,'London','London','UK'),(2,'Paris','Paris','France'),(3,'Rome','Rome','Italy'),(4,'Zagreb','Zagreb','Hrvatska'),(5,'Dublin','Dublin','Ireland'),(6,'Newfoundland','Newfoundland','Canada'),(7,'Nova Scotia','Nova Scotia','Canada');
/*!40000 ALTER TABLE `radar_center` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `radar_cord`
--

DROP TABLE IF EXISTS `radar_cord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `radar_cord` (
  `idradar_cord` int(11) NOT NULL AUTO_INCREMENT,
  `radar_centar` int(11) NOT NULL,
  `lat` double NOT NULL,
  `lng` double NOT NULL,
  PRIMARY KEY (`idradar_cord`),
  KEY `radar_centar1_idx` (`radar_centar`),
  CONSTRAINT `radar_centar1` FOREIGN KEY (`radar_centar`) REFERENCES `radar_center` (`idradar_center`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `radar_cord`
--

LOCK TABLES `radar_cord` WRITE;
/*!40000 ALTER TABLE `radar_cord` DISABLE KEYS */;
INSERT INTO `radar_cord` VALUES (1,1,51.5074,0.1278),(2,2,48.8566,2.3522),(3,3,41.9028,12.4964),(4,4,45.815,15.9819),(5,5,53.3498,-6.2603),(6,6,53.1355,-57.6604),(7,7,44.682,-63.7443);
/*!40000 ALTER TABLE `radar_cord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `radar_flight`
--

DROP TABLE IF EXISTS `radar_flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `radar_flight` (
  `idradar_flight` int(11) NOT NULL AUTO_INCREMENT,
  `let` int(11) NOT NULL,
  `centar` int(11) NOT NULL,
  PRIMARY KEY (`idradar_flight`),
  KEY `let_idx` (`let`),
  KEY `radar_idx` (`centar`),
  CONSTRAINT `centar1` FOREIGN KEY (`centar`) REFERENCES `radar_center` (`idradar_center`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `let1` FOREIGN KEY (`let`) REFERENCES `flight` (`idflight`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `radar_flight`
--

LOCK TABLES `radar_flight` WRITE;
/*!40000 ALTER TABLE `radar_flight` DISABLE KEYS */;
INSERT INTO `radar_flight` VALUES (3,41,2),(4,41,3),(5,41,4),(6,42,2),(7,42,3),(8,42,4),(9,43,2),(10,43,3),(11,43,4),(13,44,2),(14,44,3),(15,44,4),(19,45,2),(20,45,3),(21,45,4),(22,33,2),(23,33,3),(24,33,4),(25,34,7),(26,34,6),(27,34,5),(28,31,5),(29,31,2),(30,31,4),(31,32,4),(32,32,2),(33,32,5),(34,35,5),(35,35,6),(36,35,7),(37,36,4),(38,36,3),(39,36,2);
/*!40000 ALTER TABLE `radar_flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `register_user`
--

DROP TABLE IF EXISTS `register_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `register_user` (
  `idregister_user` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `type` int(11) NOT NULL,
  `airline` varchar(45) NOT NULL,
  `birthdate` date NOT NULL,
  PRIMARY KEY (`idregister_user`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `register_user`
--

LOCK TABLES `register_user` WRITE;
/*!40000 ALTER TABLE `register_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `register_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `idreservation` int(11) NOT NULL AUTO_INCREMENT,
  `passport` int(11) NOT NULL,
  `people` int(11) NOT NULL,
  `flight` int(11) NOT NULL,
  `number` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idreservation`),
  KEY `flight_idx` (`flight`),
  CONSTRAINT `flight` FOREIGN KEY (`flight`) REFERENCES `flight` (`idflight`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rezervacije`
--

DROP TABLE IF EXISTS `rezervacije`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rezervacije` (
  `idrezervacije` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(45) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  `passport` int(11) NOT NULL,
  `people` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `flight` int(11) NOT NULL,
  PRIMARY KEY (`idrezervacije`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezervacije`
--

LOCK TABLES `rezervacije` WRITE;
/*!40000 ALTER TABLE `rezervacije` DISABLE KEYS */;
INSERT INTO `rezervacije` VALUES (1,'Mina','Milosavljevic',1213456,1,2,3),(2,'Mina','Milosavljevic',12345,3,111234,3),(3,'Nikola','Antonijevic',12345,3,111234,3),(4,'Mira','Milosavljevic',19031993,3,111234,3),(5,'Mama','Milosavljevic',10081972,3,10000001,3),(6,'','',0,1,10000001,3),(7,'Mina','BlabLA',124561432,2,10000001,3),(8,'Bla ','asgadfhg',14141,2,10000001,3),(9,'Bla ','asgadfhg',14141,2,10000002,3),(10,'adsfg','dsafg',324,3,10000001,3),(11,'Dejan','Milosavljevic',10081972,1,10000001,0),(12,'Kiki','Antonijevic',2203995,7,10000001,3),(13,'Mala','asfgsd',254123,1,10000001,3),(14,'NIKI','SDG',23431,1,10000001,3),(15,'NIKI','SDG',23431,1,10000002,3),(16,'Nikola ','Antonijevic',23431,5,10000001,3),(17,'Nikola ','Antonijevic',23431,5,10000002,3),(18,'Mala','Mala',123456,1,10000001,4),(19,'Mala','Mala',123456,1,10000002,4),(20,'Mala','Mala',123456,1,10000003,4),(21,'Bla ','SDAG',23431,3,10000001,6),(22,'Bla ','SDAG',23431,3,10000002,7),(23,'Mala','Milosavljevic',124235,1,10000001,3),(24,'Ker','Mala',23512345,4,10000001,3),(25,'sdf','sdf',3512,1,10000001,10),(26,'sdftjry','sdf',3512,1,10000002,10),(27,'sdftjry','sdf',3512,1,10000003,8),(28,'asg','dsafg',342,1,10000001,3),(29,'Kiki','Antonijevic',123456789,1,10000001,3),(30,'Mina','Milosavljevic',123456789,8,10000001,3),(31,'Mina','Milosavljevic',123456789,3,10000001,3),(32,'Mina','Milosavljevic',123456789,3,10000002,4),(33,'Mina','Milosavljevic',123456789,1,10000001,3),(34,'Mina','Milosavljevic',123456789,1,10000002,3),(35,'bLA','Antonijevic',123456789,1,10000001,6),(36,'bLA','Antonijevic',123456789,1,10000002,7),(37,'SDA','Antonijevic',123456789,1,10000001,6),(38,'SDA','Antonijevic',123456789,1,10000002,7),(39,'Final','Final',123456789,1,10000001,4),(40,'Final','Konacno',123456789,1,10000001,4),(41,'asg','sdfg',123456789,1,10000001,4),(42,'asg','sdfg',123456789,1,10000002,4),(43,'asg','sdfg',123456789,1,10000003,3),(44,'bLA','Bla',123456789,1,10000001,3),(45,'Final','Final',123456789,1,10000001,4),(46,'Final','Final',123456789,1,10000002,3),(47,'sadg','asda',123456789,1,10000001,3),(48,'sadg','asda',123456789,1,10000002,4),(49,'Mina','Mala',123456789,1,10000001,6),(50,'Mina','Mala',123456789,1,10000002,7),(51,'Proba','Proba',123456789,1,10000001,6),(52,'Proba','Proba',123456789,1,10000002,7),(53,'Mina','Milosavljevic',123456789,1,10000001,13),(54,'Mina','Milosavljevic',123456789,1,10000002,14),(55,'Presedanje','Final',123456789,1,10000001,19),(56,'Presedanje','Final',123456789,1,10000002,20),(57,'KCKC','CKC',123456789,1,10000001,19),(58,'KCKC','CKC',123456789,1,10000002,20),(59,'Snezana','Milosavljevic',123456789,1,10000001,12),(60,'Nikola','Antonijevic',123456789,1,10000001,12),(61,'Mira','Milosavljevic',123456789,1,10000001,12),(62,'Mira','Milosavljevic',123456789,8,10000002,3),(63,'Mira','Milosavljevic',123456789,8,10000003,4),(64,'Mira','Milosavljevic',123456789,8,10000004,3),(65,'Mira','Milosavljevic',123456789,8,10000005,6),(66,'Mira','Milosavljevic',123456789,8,10000006,7),(67,'Mira','Milosavljevic',123456789,8,10000007,13),(68,'Mira','Milosavljevic',123456789,8,10000008,19),(69,'Mira','Milosavljevic',123456789,8,10000009,20),(70,'Mira','Milosavljevic',123456789,1,10000001,31),(71,'Mira','Milosavljevic',123456789,1,10000002,33),(72,'Mira','Milosavljevic',123456789,1,10000003,34),(73,'Mira','Milosavljevic',123456789,1,10000004,31),(74,'Mira','Milosavljevic',123456789,1,10000005,32);
/*!40000 ALTER TABLE `rezervacije` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `type` int(11) NOT NULL,
  `airline` varchar(45) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  PRIMARY KEY (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (23,'pilot','Mmmm1@23','Mira','Milosavljevic','miramilosavljevic@gmail.com',1,'British Airways','1993-03-19'),(24,'stuart','Mmmm1@23','Mina','Milosavljevic','nagazna@gmail.com',4,'British Airways','1995-04-02'),(26,'crew','Mmmm1@23','Nikola','Antonijevic','antonijevic@gmail.com',5,'British Airways','1995-03-22'),(27,'admin','mMMM1@23','Dejan','Milosavljevic','dejan@gmail.com',3,'British Airways','1972-02-08'),(28,'novkorisnik','Aaaa1@23','Snezana','Milosavljevic','nov@gmail.com',1,'British Airways','1972-08-08');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-30 11:36:05
