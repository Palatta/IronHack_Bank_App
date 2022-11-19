-- MySQL dump 10.13  Distrib 8.0.29, for Linux (x86_64)
--
-- Host: localhost    Database: mid_term_project_db2
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `account_type` varchar(31) NOT NULL,
  `account_id` int NOT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `penalty_amount` decimal(19,2) DEFAULT NULL,
  `penalty_currency` varchar(255) DEFAULT NULL,
  `creation_date` date DEFAULT NULL,
  `monthly_maint_amount` decimal(19,2) DEFAULT NULL,
  `monthly_maint_currency` varchar(255) DEFAULT NULL,
  `secret_key` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `min_amount` decimal(19,2) DEFAULT NULL,
  `min_currency` varchar(255) DEFAULT NULL,
  `credit_lim_amount` decimal(19,2) DEFAULT NULL,
  `credit_lim_currency` varchar(255) DEFAULT NULL,
  `interest_rate` decimal(19,2) DEFAULT NULL,
  `minimum_amount` decimal(19,2) DEFAULT NULL,
  `minimum_currency` varchar(255) DEFAULT NULL,
  `primary_owner_user_id` int DEFAULT NULL,
  `secondary_owner_user_id` int DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  KEY `FKokirjf6enynkpqx22om7gm7xd` (`primary_owner_user_id`),
  KEY `FKc82q2q19jinuyj4vp2cxoikcs` (`secondary_owner_user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES ('StudentChecking',52,2000.00,'USD',40.00,'USD','2022-10-18',12.00,'USD','key123','ACTIVE',NULL,NULL,NULL,NULL,NULL,NULL,NULL,7,NULL),('Savings',53,-460.00,'USD',40.00,'USD',NULL,12.00,'USD','key123','ACTIVE',NULL,NULL,NULL,NULL,0.50,100.00,'USD',8,NULL),('StudentChecking',103,2000.00,'USD',40.00,'USD','2022-10-18',12.00,'USD','key123','ACTIVE',NULL,NULL,NULL,NULL,NULL,NULL,NULL,12,NULL),('Savings',104,2000.00,'USD',40.00,'USD',NULL,12.00,'USD','key123','ACTIVE',NULL,NULL,NULL,NULL,0.40,1200.00,'USD',13,NULL),('Savings',105,-140.00,'USD',40.00,'USD',NULL,12.00,'USD','key123','ACTIVE',NULL,NULL,NULL,NULL,0.50,100.00,'USD',14,NULL);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (15);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_seq`
--

DROP TABLE IF EXISTS `my_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `my_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_seq`
--

LOCK TABLES `my_seq` WRITE;
/*!40000 ALTER TABLE `my_seq` DISABLE KEYS */;
INSERT INTO `my_seq` VALUES (201);
/*!40000 ALTER TABLE `my_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `dtype` varchar(31) NOT NULL,
  `user_id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `mailing_city` varchar(255) DEFAULT NULL,
  `mailing_street` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `hashed_key` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('account_holder',1,'Erica','1982-09-09','Roma','Via dei fori imperiali','Badalona','Calle del mar',NULL),('account_holder',2,'Mario','2004-09-09','Roma','Via dei fori imperiali','Badalona','Calle del mar',NULL),('Admins',3,'Martina',NULL,NULL,NULL,NULL,NULL,NULL),('ThirdParty',4,'Piero',NULL,NULL,NULL,NULL,NULL,'12a554cvx'),('account_holders',5,'Mario','2004-09-09','Roma','Via dei fori imperiali','Badalona','Calle del mar',NULL),('account_holders',6,'Erica','1984-09-09','Roma','Via dei fori imperiali','Badalona','Calle del mar',NULL),('account_holder',7,'Erica','2001-10-09','Barcelona','Carrer de Madrid','Barcelona','Carrer de Valencia',NULL),('account_holder',8,'Maria','2000-09-09','Roma','Via dei fori imperiali','Badalona','Calle del mar',NULL),('Admins',10,'Martina',NULL,NULL,NULL,NULL,NULL,NULL),('ThirdParty',11,'Mary',NULL,NULL,NULL,NULL,NULL,'12a554cvx'),('account_holder',12,'Erica','2001-10-09','Barcelona','Carrer de Madrid','Barcelona','Carrer de Valencia',NULL),('account_holder',13,'Maria','2000-09-09','Roma','Via dei fori imperiali','Badalona','Calle del mar',NULL),('account_holder',14,'Maria','2000-09-09','Roma','Via dei fori imperiali','Badalona','Calle del mar',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-17 20:05:00
