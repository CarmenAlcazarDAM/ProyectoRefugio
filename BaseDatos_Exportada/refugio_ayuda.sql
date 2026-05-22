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
-- Table structure for table `ayuda`
--

DROP TABLE IF EXISTS `ayuda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ayuda` (
  `dniVoluntario` char(9) NOT NULL,
  `idUbicacion` int unsigned NOT NULL,
  `fecha` date NOT NULL,
  `tarea` text,
  PRIMARY KEY (`dniVoluntario`,`idUbicacion`,`fecha`),
  KEY `idUbicacion` (`idUbicacion`),
  CONSTRAINT `ayuda_ibfk_1` FOREIGN KEY (`dniVoluntario`) REFERENCES `voluntario` (`dniVoluntario`) ON UPDATE CASCADE,
  CONSTRAINT `ayuda_ibfk_2` FOREIGN KEY (`idUbicacion`) REFERENCES `ubicacion` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ayuda`
--

LOCK TABLES `ayuda` WRITE;
/*!40000 ALTER TABLE `ayuda` DISABLE KEYS */;
INSERT INTO `ayuda` VALUES ('11111111A',13,'2026-05-01','Baño antiparasitario general para todos los perros del chenil 13.'),('22222222B',1,'2026-05-01','Revisión general de areneros y reposición de pienso seco y latas húmedas.'),('22222222B',3,'2026-04-30','Paseo individual para Brutus. Se mostró más relajado que el día anterior.'),('33333333C',4,'2026-05-01','Rocky no quiso pasear, se le hizo compañía y juegos de olfato dentro de su chenil.'),('33333333C',10,'2026-05-02','Paseo largo por los alrededores de la protectora para soltar energía. ¡Bianca exploró muchísimo!'),('33333333C',13,'2026-04-30','Recreo grupal de 1 hora. Toby y Luna han jugado mucho, Bianca prefirió tomar el sol.'),('44444444D',4,'2026-05-01','Rocky sigue desconfiado. Trabajo de habituación a la correa en el interior de su chenil.'),('44444444D',5,'2026-04-29','Primer acercamiento con Zeus (Rottweiler). Se ha mantenido distante pero ha aceptado premios.'),('44444444D',5,'2026-05-02','Paseo de 20 minutos con Zeus. Ya permite caricias en el cuello. ¡Gran avance!'),('55555555E',2,'2026-05-01','Control de temperatura y medicación para Salem en la zona de cuarentena.'),('55555555E',13,'2026-05-02','Revisión general de los perros sociables. Todo en orden, Chispa sigue ganando confianza.'),('66666666F',1,'2026-05-02','Limpieza general de bebederos y areneros. Reposición de arena aglomerante.'),('66666666F',13,'2026-05-01','Paseo en grupo: Bianca, Toby, Luna y Chispa. Mucha energía, ¡hubo que correr con ellos!');
/*!40000 ALTER TABLE `ayuda` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-22 12:46:10
