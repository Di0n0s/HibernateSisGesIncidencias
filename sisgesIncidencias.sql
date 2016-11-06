CREATE DATABASE  IF NOT EXISTS `sisgesincidencias` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sisgesincidencias`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sisgesincidencias
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empleados` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `nombre_completo` varchar(100) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES ('amartinez','lmg254','Antonio Martínez Rodríguez','+34956214789'),('cnavarro','mav164','Camilo Navarro Montesino','+34687325815'),('jperez','trz254','Jose Pérez Fernández','+34985365235'),('sfernandez','pqb937','Sergio Fernández Moreno','+34684200587');
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historial`
--

DROP TABLE IF EXISTS `historial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historial` (
  `id_incidencia` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_evento` varchar(1) NOT NULL,
  `fecha_hora` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`id_incidencia`),
  KEY `username_FK_idx` (`username`),
  CONSTRAINT `username_FK` FOREIGN KEY (`username`) REFERENCES `empleados` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historial`
--

LOCK TABLES `historial` WRITE;
/*!40000 ALTER TABLE `historial` DISABLE KEYS */;
INSERT INTO `historial` VALUES (2,'I','2016/11/05-07:54:57:49','jperez'),(3,'U','2016/11/05-08:59:00:326','amartinez'),(4,'U','2016/11/05-09:00:06:405','amartinez'),(5,'U','2016/11/05-09:03:08:554','amartinez'),(6,'C','2016/11/05-09:31:56:577','amartinez'),(7,'C','2016/11/05-09:32:17:773','amartinez'),(8,'I','2016/11/05-10:54:04:881','jperez'),(9,'I','2016/11/05-10:54:09:475','jperez'),(10,'U','2016/11/06-12:40:12:162','jperez'),(11,'U','2016/11/06-12:40:14:896','jperez'),(12,'U','2016/11/06-12:43:28:538','sfernandez'),(13,'U','2016/11/06-07:10:41:830','cnavarro'),(14,'C','2016/11/06-07:38:09:170','amartinez'),(15,'I','2016/11/06-07:59:45:338','cnavarro'),(16,'C','2016/11/06-09:54:14:284','amartinez');
/*!40000 ALTER TABLE `historial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incidencias`
--

DROP TABLE IF EXISTS `incidencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `incidencias` (
  `id_incidencia` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_hora` varchar(45) NOT NULL,
  `origen` varchar(45) NOT NULL,
  `destino` varchar(45) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `detalle` varchar(100) NOT NULL,
  PRIMARY KEY (`id_incidencia`),
  KEY `origen_FK_idx` (`origen`),
  KEY `destino_FK_idx` (`destino`),
  CONSTRAINT `destino_FK` FOREIGN KEY (`destino`) REFERENCES `empleados` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `origen_FK` FOREIGN KEY (`origen`) REFERENCES `empleados` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incidencias`
--

LOCK TABLES `incidencias` WRITE;
/*!40000 ALTER TABLE `incidencias` DISABLE KEYS */;
INSERT INTO `incidencias` VALUES (1,'2014/12/01-15:01:22:31','jperez','amartinez','Normal','La impresora no funciona'),(9,'2016/11/05-08:12:07:488','amartinez','amartinez','Normal','La impresora no tiene toner'),(11,'2016/11/05-08:21:36:426','amartinez','jperez','Normal','La impresora no tiene toner'),(12,'2016/11/05-08:56:28:837','amartinez','jperez','Normal','La impresora no tiene toner'),(13,'2016/11/05-08:57:57:92','amartinez','jperez','Urgente','La impresora no tiene toner'),(14,'2016/11/05-08:59:00:135','amartinez','jperez','Urgente','La impresora no tiene toner'),(15,'2016/11/05-09:00:06:209','amartinez','jperez','Urgente','La impresora no tiene toner'),(16,'2016/11/05-09:03:08:356','amartinez','jperez','Urgente','La impresora no tiene toner'),(17,'2016/11/06-12:40:11:940','jperez','amartinez','Urgente','La impresora no tiene toner'),(18,'2016/11/06-12:40:14:720','jperez','amartinez','Urgente','La impresora no tiene toner'),(19,'2016/11/06-12:43:28:352','sfernandez','jperez','Urgente','La impresora no tiene toner'),(20,'2016/11/06-06:44:14:541','cnavarro','sfernandez','Normal','El ranking me está volviendo loco'),(21,'2016/11/06-06:44:31:272','cnavarro','sfernandez','Normal','El ranking me está volviendo loco'),(22,'2016/11/06-07:10:41:727','cnavarro','sfernandez','Urgente','El ranking me está volviendo loco');
/*!40000 ALTER TABLE `incidencias` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-06 23:15:14
