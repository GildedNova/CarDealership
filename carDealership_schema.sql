DROP DATABASE IF EXISTS carDealership;
CREATE DATABASE carDealership;

USE carDealership;


-- Users 
DROP TABLE IF EXISTS `Employee`;
CREATE TABLE `Employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Privilege` varchar(45) NOT NULL,
  `Password` varchar(64) NOT NULL,
  `Active` BOOLEAN NOT NULL,
  PRIMARY KEY (`id`)
);

-- BEGIN tables for car attributes --
DROP TABLE IF EXISTS `BodyStyle`;
CREATE TABLE `BodyStyle` (
  `Style` varchar(25) NOT NULL,
  PRIMARY KEY (`Style`)
);

DROP TABLE IF EXISTS `Color`;
CREATE TABLE `Color` (
  `Color` varchar(25) NOT NULL,
  PRIMARY KEY (`Color`)
);

DROP TABLE IF EXISTS `Make`;
CREATE TABLE `Make` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `EmployeeId` INT NOT NULL,
  `DateAdded` DATETIME DEFAULT Now(),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_makeEmployeeId` FOREIGN KEY (`EmployeeId`) REFERENCES `Employee` (`id`)
);

DROP TABLE IF EXISTS `Model`;
CREATE TABLE `Model` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `makeid` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_makeid` FOREIGN KEY (`id`) REFERENCES `Make` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS `Transmission`;
CREATE TABLE `Transmission` (
  `Transmission` varchar(25) NOT NULL,
  PRIMARY KEY (`Transmission`)
);
-- END tables for car attributes --


DROP TABLE IF EXISTS `Car`;
CREATE TABLE `Car` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `VIN` varchar(45) NOT NULL,
  `MakeID` INT NOT NULL,
  `ModelID` INT NOT NULL,
  `Type` varchar(45) NOT NULL,
  `BodyStyle` varchar(45) NOT NULL,
  `Year` INT NOT NULL,
  `Transmission` varchar(45) NOT NULL,
  `Color` varchar(45) NOT NULL,
  `InteriorColor` varchar(45) NOT NULL,
  `Milage` INT NOT NULL,
  `MSRP` decimal(12,2) NOT NULL,
  `SalePrice` decimal(12,2) NOT NULL,
  `Description` VARCHAR(1000),
  `isFeatured` BOOLEAN DEFAULT FALSE,
  `isSold` BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (`id`),
  KEY `fk_make` (`MakeID`),
  KEY `fk_model` (`ModelID`),
  CONSTRAINT `fk_make` FOREIGN KEY (`MakeID`) REFERENCES `Make` (`id`),
  CONSTRAINT `fk_model` FOREIGN KEY (`ModelID`) REFERENCES `Model` (`id`)
);


-- Sales relevant tables-----------------------
DROP TABLE IF EXISTS `PurchaseType`;
CREATE TABLE `PurchaseType`(
`Type` VARCHAR(20) PRIMARY KEY
);

DROP TABLE IF EXISTS `Sale`;
CREATE TABLE `Sale` (
`id` INT NOT NULL AUTO_INCREMENT,
`DateAdded` DATETIME DEFAULT Now(),
`Name` VARCHAR(50) NOT NULL,
`Phone` VARCHAR(20),
`Email` VARCHAR (50),
`Street1` VARCHAR(50) NOT NULL,
`Street2` VARCHAR(50),
`City` VARCHAR(30) NOT NULL,
`State` CHAR(2) NOT NULL,
`Zip` VARCHAR(5) NOT NULL,
`PurchasePrice` DECIMAL(12,2) NOT NULL,
`PurchaseType` VARCHAR(20) NOT NULL,
`EmployeeId` INT NOT NULL,
`CarId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_purchaseType` FOREIGN KEY (`PurchaseType`) REFERENCES `PurchaseType` (`Type`),
  CONSTRAINT `fk_carId` FOREIGN KEY (`CarId`) REFERENCES `Car` (`id`),
  CONSTRAINT `fk_saleEmployeeId` FOREIGN KEY (`EmployeeId`) REFERENCES `Employee` (`id`)
);

-- Specials ---------------------------------------------------------
DROP TABLE IF EXISTS `Special`;
CREATE TABLE `Special`(
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`Title` VARCHAR(50) NOT NULL,
`Body` VARCHAR(1000) NOT NULL,
`isActive` BOOLEAN DEFAULT TRUE
);


