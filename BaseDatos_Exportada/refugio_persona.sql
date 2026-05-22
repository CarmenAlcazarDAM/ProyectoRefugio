CREATE DATABASE  IF NOT EXISTS `refugio` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `refugio`;
-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: refugio
-- ------------------------------------------------------
-- Server version	8.0.45

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
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `dni` char(9) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellidos` varchar(30) DEFAULT NULL,
  `telefono` varchar(12) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES ('11111111A','Ana','Gómez','600111222','ana.gomez@email.com','Puente Genil'),('11223344K','Elena','Prieto Jiménez','600998877','elena.prieto@email.com','Calle Don Gonzalo 25, Puente Genil'),('12341242X','Ernesto','García','643236341','PRUEBA\"GMAIL.COM','Calle imaginaria'),('12345678Z','Carmen María','Alcázar Martín','600123456','carmenmariaalcazarmartin@gmail.com','Calle Puente Genil, 4, 1-1, Córdoba'),('15654523E','Carmen','Alcázar','432423452','PRUEBA@GMAIL.COM','CALLE CALLE'),('22222222B','David','López','600333444','david.lopez@email.com','Puente Genil'),('23456789H','Laura','Fernández Pérez','623456789','laura.fernandez@hotmail.com','Avenida Libertad 45, 3ºB'),('33333333C','María','Sánchez','600555666','maria.sanchez@email.com','Puente Genil'),('34567890V','Miguel','López Sánchez','634567890','miguel.lopez@yahoo.com','Calle Rosal 7, Bajo'),('44444444D','Carlos','Reyes Mora','644556677','carlos.reyes@email.com','Calle Lemoniez 8, Puente Genil'),('55443322L','Ricardo','Ruiz Ocaña','611554433','ricardo.ruiz@email.com','Cuesta del Molino 8, Puente Genil'),('55555555E','Sofía','Navarro Ortiz','655667788','sofia.navarro@email.com','Paseo del Romeral 12, Puente Genil'),('66666666F','Miguel','Carmona Vega','666778899','miguel.carmona@email.com','Barrio de la Pitilla 3, Puente Genil'),('87654321A','Luis','García Ruiz','698765432','lugaruz@gmail.com','Calle Puente Genil, 4, 1-1, Córdoba'),('87654321S','Carmen','Alcazar','652443601','prueba@prueba.com','Calle luna');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-22 12:46:11
