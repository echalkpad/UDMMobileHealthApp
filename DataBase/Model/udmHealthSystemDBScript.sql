SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `udmhealthsystem` ;
CREATE SCHEMA IF NOT EXISTS `udmhealthsystem` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `udmhealthsystem` ;

-- -----------------------------------------------------
-- Table `udmhealthsystem`.`State`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `udmhealthsystem`.`State` ;

CREATE TABLE IF NOT EXISTS `udmhealthsystem`.`State` (
  `idState` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `stateCode` VARCHAR(2) NULL,
  PRIMARY KEY (`idState`),
  UNIQUE INDEX `stateCode_UNIQUE` (`stateCode` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `udmhealthsystem`.`Physician`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `udmhealthsystem`.`Physician` ;

CREATE TABLE IF NOT EXISTS `udmhealthsystem`.`Physician` (
  `idPhysician` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  `street` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `phoneNumber` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `zip` VARCHAR(5) NULL,
  `idState` INT NOT NULL,
  PRIMARY KEY (`idPhysician`),
  INDEX `fk_Physician_State1_idx` (`idState` ASC),
  CONSTRAINT `fk_Physician_State1`
    FOREIGN KEY (`idState`)
    REFERENCES `udmhealthsystem`.`State` (`idState`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `udmhealthsystem`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `udmhealthsystem`.`User` ;

CREATE TABLE IF NOT EXISTS `udmhealthsystem`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  `dob` DATE NULL,
  `email` VARCHAR(45) NULL,
  `phoneNumber` VARCHAR(45) NULL,
  `ssn` VARCHAR(45) NULL,
  `street` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `zipCode` VARCHAR(5) NULL,
  `idState` INT NULL,
  `idPhysician` INT NULL,
  PRIMARY KEY (`idUser`),
  INDEX `fk_User_State_idx` (`idState` ASC),
  INDEX `fk_User_Physician1_idx` (`idPhysician` ASC),
  CONSTRAINT `fk_User_State`
    FOREIGN KEY (`idState`)
    REFERENCES `udmhealthsystem`.`State` (`idState`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Physician1`
    FOREIGN KEY (`idPhysician`)
    REFERENCES `udmhealthsystem`.`Physician` (`idPhysician`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `udmhealthsystem`.`Scale`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `udmhealthsystem`.`Scale` ;

CREATE TABLE IF NOT EXISTS `udmhealthsystem`.`Scale` (
  `idScale` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `symbol` VARCHAR(10) NOT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`idScale`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `udmhealthsystem`.`Measurement_Type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `udmhealthsystem`.`Measurement_Type` ;

CREATE TABLE IF NOT EXISTS `udmhealthsystem`.`Measurement_Type` (
  `idMeasurementType` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `Scale_idScale` INT NOT NULL,
  PRIMARY KEY (`idMeasurementType`),
  INDEX `fk_Measurement_Type_Scale1_idx` (`Scale_idScale` ASC),
  CONSTRAINT `fk_Measurement_Type_Scale1`
    FOREIGN KEY (`Scale_idScale`)
    REFERENCES `udmhealthsystem`.`Scale` (`idScale`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `udmhealthsystem`.`Measurement_Attribute`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `udmhealthsystem`.`Measurement_Attribute` ;

CREATE TABLE IF NOT EXISTS `udmhealthsystem`.`Measurement_Attribute` (
  `idMeasurement` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `idMeasurementType` INT NOT NULL,
  PRIMARY KEY (`idMeasurement`),
  INDEX `fk_MeasurementAttribute_MeasurementType1_idx` (`idMeasurementType` ASC),
  CONSTRAINT `fk_MeasurementAttribute_MeasurementType1`
    FOREIGN KEY (`idMeasurementType`)
    REFERENCES `udmhealthsystem`.`Measurement_Type` (`idMeasurementType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `udmhealthsystem`.`User_Measurement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `udmhealthsystem`.`User_Measurement` ;

CREATE TABLE IF NOT EXISTS `udmhealthsystem`.`User_Measurement` (
  `idUserMeasurement` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `idUser` INT NOT NULL,
  `idMeasurementType` INT NOT NULL,
  PRIMARY KEY (`idUserMeasurement`),
  INDEX `fk_UserMeasurement_User1_idx` (`idUser` ASC),
  INDEX `fk_UserMeasurement_MeasurementType1_idx` (`idMeasurementType` ASC),
  CONSTRAINT `fk_UserMeasurement_User1`
    FOREIGN KEY (`idUser`)
    REFERENCES `udmhealthsystem`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserMeasurement_MeasurementType1`
    FOREIGN KEY (`idMeasurementType`)
    REFERENCES `udmhealthsystem`.`Measurement_Type` (`idMeasurementType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `udmhealthsystem`.`User_Measurement_Attribute`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `udmhealthsystem`.`User_Measurement_Attribute` ;

CREATE TABLE IF NOT EXISTS `udmhealthsystem`.`User_Measurement_Attribute` (
  `idUserMeasurementAttribute` INT NOT NULL AUTO_INCREMENT,
  `idMeasurementAttribute` INT NOT NULL,
  `value` VARCHAR(45) NOT NULL,
  `idUserMeasurement` INT NOT NULL,
  PRIMARY KEY (`idUserMeasurementAttribute`),
  INDEX `fk_Measurement_MeasurementAttribute1_idx` (`idMeasurementAttribute` ASC),
  INDEX `fk_MeasurementAttribute_UserMeasurement1_idx` (`idUserMeasurement` ASC),
  CONSTRAINT `fk_Measurement_MeasurementAttribute1`
    FOREIGN KEY (`idMeasurementAttribute`)
    REFERENCES `udmhealthsystem`.`Measurement_Attribute` (`idMeasurement`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MeasurementAttribute_UserMeasurement1`
    FOREIGN KEY (`idUserMeasurement`)
    REFERENCES `udmhealthsystem`.`User_Measurement` (`idUserMeasurement`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `udmhealthsystem`.`Notification_Type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `udmhealthsystem`.`Notification_Type` ;

CREATE TABLE IF NOT EXISTS `udmhealthsystem`.`Notification_Type` (
  `idNotificationType` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`idNotificationType`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `udmhealthsystem`.`Status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `udmhealthsystem`.`Status` ;

CREATE TABLE IF NOT EXISTS `udmhealthsystem`.`Status` (
  `idStatus` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(20) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`idStatus`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `udmhealthsystem`.`Notification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `udmhealthsystem`.`Notification` ;

CREATE TABLE IF NOT EXISTS `udmhealthsystem`.`Notification` (
  `idNotification` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `idNotificationType` INT NOT NULL,
  `idStatus` INT NOT NULL,
  `idUserMeasurement` INT NOT NULL,
  INDEX `fk_Notification_NotificationType1_idx` (`idNotificationType` ASC),
  INDEX `fk_Notification_Status1_idx` (`idStatus` ASC),
  INDEX `fk_Notification_UserMeasurement1_idx` (`idUserMeasurement` ASC),
  PRIMARY KEY (`idNotification`),
  CONSTRAINT `fk_Notification_NotificationType1`
    FOREIGN KEY (`idNotificationType`)
    REFERENCES `udmhealthsystem`.`Notification_Type` (`idNotificationType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Notification_Status1`
    FOREIGN KEY (`idStatus`)
    REFERENCES `udmhealthsystem`.`Status` (`idStatus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Notification_UserMeasurement1`
    FOREIGN KEY (`idUserMeasurement`)
    REFERENCES `udmhealthsystem`.`User_Measurement` (`idUserMeasurement`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `udmhealthsystem`.`request_logs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `udmhealthsystem`.`request_logs` ;

CREATE TABLE IF NOT EXISTS `udmhealthsystem`.`request_logs` (
  `id_request` INT NOT NULL AUTO_INCREMENT,
  `request_xml` BLOB NULL,
  `response_xml` BLOB NULL,
  `service_name` VARCHAR(45) NULL,
  `response_code` VARCHAR(45) NULL,
  `response_message` VARCHAR(255) NULL,
  `create_date` DATETIME NULL,
  `update_date` DATETIME NULL,
  PRIMARY KEY (`id_request`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `udmhealthsystem`.`services`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `udmhealthsystem`.`services` ;

CREATE TABLE IF NOT EXISTS `udmhealthsystem`.`services` (
  `id_service` INT NOT NULL AUTO_INCREMENT,
  `service_name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  `logging` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id_service`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `udmhealthsystem`.`State`
-- -----------------------------------------------------
START TRANSACTION;
USE `udmhealthsystem`;
INSERT INTO `udmhealthsystem`.`State` (`idState`, `name`, `stateCode`) VALUES (1, 'Michigan', 'MI');
INSERT INTO `udmhealthsystem`.`State` (`idState`, `name`, `stateCode`) VALUES (2, 'California', 'CA');
INSERT INTO `udmhealthsystem`.`State` (`idState`, `name`, `stateCode`) VALUES (3, 'Florida', 'FL');

COMMIT;


-- -----------------------------------------------------
-- Data for table `udmhealthsystem`.`Scale`
-- -----------------------------------------------------
START TRANSACTION;
USE `udmhealthsystem`;
INSERT INTO `udmhealthsystem`.`Scale` (`idScale`, `name`, `symbol`, `description`) VALUES (1, 'Fahrenheit', '°F', 'Fahrenheit');
INSERT INTO `udmhealthsystem`.`Scale` (`idScale`, `name`, `symbol`, `description`) VALUES (2, 'Celsius', '°C', 'Celsius');
INSERT INTO `udmhealthsystem`.`Scale` (`idScale`, `name`, `symbol`, `description`) VALUES (3, 'Millimeter of mercury', ' MmHg', 'Millimeter of mercury');

COMMIT;


-- -----------------------------------------------------
-- Data for table `udmhealthsystem`.`Measurement_Type`
-- -----------------------------------------------------
START TRANSACTION;
USE `udmhealthsystem`;
INSERT INTO `udmhealthsystem`.`Measurement_Type` (`idMeasurementType`, `code`, `description`, `Scale_idScale`) VALUES (1, 'BLOOD_PRESSURE', 'Blood pressure', 3);
INSERT INTO `udmhealthsystem`.`Measurement_Type` (`idMeasurementType`, `code`, `description`, `Scale_idScale`) VALUES (2, 'TEMPERATURE_FAHRENHEIT', 'Temperature in Fahrenheit', 1);
INSERT INTO `udmhealthsystem`.`Measurement_Type` (`idMeasurementType`, `code`, `description`, `Scale_idScale`) VALUES (3, 'TEMPERATURE_CELSIUS', 'Temperature in Celsius', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `udmhealthsystem`.`Measurement_Attribute`
-- -----------------------------------------------------
START TRANSACTION;
USE `udmhealthsystem`;
INSERT INTO `udmhealthsystem`.`Measurement_Attribute` (`idMeasurement`, `name`, `description`, `idMeasurementType`) VALUES (1, 'TEMPERATURE', 'Temperature', 2);
INSERT INTO `udmhealthsystem`.`Measurement_Attribute` (`idMeasurement`, `name`, `description`, `idMeasurementType`) VALUES (2, 'TEMPERATURE', 'Temperature', 3);
INSERT INTO `udmhealthsystem`.`Measurement_Attribute` (`idMeasurement`, `name`, `description`, `idMeasurementType`) VALUES (3, 'Systolic', 'Systolic', 1);
INSERT INTO `udmhealthsystem`.`Measurement_Attribute` (`idMeasurement`, `name`, `description`, `idMeasurementType`) VALUES (4, 'Diastolic', 'Diastolic', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `udmhealthsystem`.`Notification_Type`
-- -----------------------------------------------------
START TRANSACTION;
USE `udmhealthsystem`;
INSERT INTO `udmhealthsystem`.`Notification_Type` (`idNotificationType`, `name`, `description`) VALUES (1, 'CONTACT_USER', 'Contact User');
INSERT INTO `udmhealthsystem`.`Notification_Type` (`idNotificationType`, `name`, `description`) VALUES (2, 'CONTACT_DOCTOR', 'Contacnt Doctor');
INSERT INTO `udmhealthsystem`.`Notification_Type` (`idNotificationType`, `name`, `description`) VALUES (3, 'CONTACT_EMERGENCY_SUPPORT', 'Emergency Support');

COMMIT;


-- -----------------------------------------------------
-- Data for table `udmhealthsystem`.`Status`
-- -----------------------------------------------------
START TRANSACTION;
USE `udmhealthsystem`;
INSERT INTO `udmhealthsystem`.`Status` (`idStatus`, `code`, `description`) VALUES (1, 'USER_NOTIFIED', 'The user has been notified');
INSERT INTO `udmhealthsystem`.`Status` (`idStatus`, `code`, `description`) VALUES (2, 'ACTION_TAKEN', 'An action has been taken');
INSERT INTO `udmhealthsystem`.`Status` (`idStatus`, `code`, `description`) VALUES (3, 'CANCELED', 'Action canceled');

COMMIT;


-- -----------------------------------------------------
-- Data for table `udmhealthsystem`.`services`
-- -----------------------------------------------------
START TRANSACTION;
USE `udmhealthsystem`;
INSERT INTO `udmhealthsystem`.`services` (`id_service`, `service_name`, `description`, `enabled`, `logging`) VALUES (1, 'CREATE_USER_REQUEST', 'Create user web service', true, true);

COMMIT;

