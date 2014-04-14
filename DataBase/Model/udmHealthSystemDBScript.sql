SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `udmhealthsystem` ;
CREATE SCHEMA IF NOT EXISTS `udmhealthsystem` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `udmhealthsystem` ;

-- -----------------------------------------------------
-- Table `udmhealthsystem`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `udmhealthsystem`.`User` ;

CREATE TABLE IF NOT EXISTS `udmhealthsystem`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  `dob` VARCHAR(25) NULL,
  `email` VARCHAR(45) NULL,
  `phoneNumber` VARCHAR(45) NULL,
  `ssn` VARCHAR(45) NULL,
  `street` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `zipCode` VARCHAR(5) NULL,
  `password` VARCHAR(255) NULL,
  `state` VARCHAR(45) NULL,
  `userType` VARCHAR(45) NULL,
  PRIMARY KEY (`idUser`))
ENGINE = InnoDB
COMMENT = '	';


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
  PRIMARY KEY (`idMeasurementType`, `Scale_idScale`),
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
  `idMeasurementAttribute` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `idMeasurementType` INT NOT NULL,
  PRIMARY KEY (`idMeasurementAttribute`),
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
    REFERENCES `udmhealthsystem`.`Measurement_Attribute` (`idMeasurementAttribute`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MeasurementAttribute_UserMeasurement1`
    FOREIGN KEY (`idUserMeasurement`)
    REFERENCES `udmhealthsystem`.`User_Measurement` (`idUserMeasurement`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `udmhealthsystem`.`medical_staff`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `udmhealthsystem`.`medical_staff` ;

CREATE TABLE IF NOT EXISTS `udmhealthsystem`.`medical_staff` (
  `medical_staff_pk` INT NOT NULL AUTO_INCREMENT,
  `speciality` VARCHAR(45) NOT NULL,
  `idUser` INT NOT NULL,
  PRIMARY KEY (`medical_staff_pk`),
  INDEX `fk_medical_staff_User1_idx` (`idUser` ASC),
  CONSTRAINT `fk_medical_staff_User1`
    FOREIGN KEY (`idUser`)
    REFERENCES `udmhealthsystem`.`User` (`idUser`)
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


-- -----------------------------------------------------
-- Table `udmhealthsystem`.`authorities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `udmhealthsystem`.`authorities` ;

CREATE TABLE IF NOT EXISTS `udmhealthsystem`.`authorities` (
  `authority_pk` INT NOT NULL AUTO_INCREMENT,
  `authority` VARCHAR(45) NOT NULL,
  `Description` VARCHAR(45) NULL,
  PRIMARY KEY (`authority_pk`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `udmhealthsystem`.`web_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `udmhealthsystem`.`web_users` ;

CREATE TABLE IF NOT EXISTS `udmhealthsystem`.`web_users` (
  `web_user_pk` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NULL,
  `enabled` VARCHAR(45) NULL,
  PRIMARY KEY (`web_user_pk`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `udmhealthsystem`.`user_authorities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `udmhealthsystem`.`user_authorities` ;

CREATE TABLE IF NOT EXISTS `udmhealthsystem`.`user_authorities` (
  `user_authority_pk` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `authority_pk` INT NOT NULL,
  PRIMARY KEY (`user_authority_pk`),
  INDEX `fk_user_authorities_Authority1_idx` (`authority_pk` ASC),
  CONSTRAINT `fk_user_authorities_Authority1`
    FOREIGN KEY (`authority_pk`)
    REFERENCES `udmhealthsystem`.`authorities` (`authority_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `udmhealthsystem`.`user_access`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `udmhealthsystem`.`user_access` ;

CREATE TABLE IF NOT EXISTS `udmhealthsystem`.`user_access` (
  `user_access_pk` INT NOT NULL AUTO_INCREMENT,
  `enabled` TINYINT(1) NOT NULL DEFAULT true,
  `medical_staff_pk` INT NOT NULL,
  `idUser` INT NOT NULL,
  PRIMARY KEY (`user_access_pk`),
  INDEX `fk_user_access_medical_staff1_idx` (`medical_staff_pk` ASC),
  INDEX `fk_user_access_User1_idx` (`idUser` ASC),
  CONSTRAINT `fk_user_access_medical_staff1`
    FOREIGN KEY (`medical_staff_pk`)
    REFERENCES `udmhealthsystem`.`medical_staff` (`medical_staff_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_access_User1`
    FOREIGN KEY (`idUser`)
    REFERENCES `udmhealthsystem`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `udmhealthsystem`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `udmhealthsystem`;
INSERT INTO `udmhealthsystem`.`User` (`idUser`, `firstName`, `lastName`, `dob`, `email`, `phoneNumber`, `ssn`, `street`, `city`, `zipCode`, `password`, `state`, `userType`) VALUES (1, 'Oscar', 'Martinez', '09/14/1984', 'oscar@gmail.com', '248-284-3694', '2336-36-3698', '1900 Brockton', 'Royal Oak', '48067', 'oscar', 'MI', NULL);
INSERT INTO `udmhealthsystem`.`User` (`idUser`, `firstName`, `lastName`, `dob`, `email`, `phoneNumber`, `ssn`, `street`, `city`, `zipCode`, `password`, `state`, `userType`) VALUES (2, 'Jose', 'Lopez', '02/11/1977', 'jose@jose.com', '248-248-2244', '233-55-5545', 'Calle 14', 'Troy', '46067', 'jose', 'MI', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `udmhealthsystem`.`Scale`
-- -----------------------------------------------------
START TRANSACTION;
USE `udmhealthsystem`;
INSERT INTO `udmhealthsystem`.`Scale` (`idScale`, `name`, `symbol`, `description`) VALUES (1, 'Fahrenheit', '°F', 'Fahrenheit');
INSERT INTO `udmhealthsystem`.`Scale` (`idScale`, `name`, `symbol`, `description`) VALUES (2, 'Celsius', '°C', 'Celsius');
INSERT INTO `udmhealthsystem`.`Scale` (`idScale`, `name`, `symbol`, `description`) VALUES (3, 'Millimeter of mercury', ' MmHg', 'Millimeter of mercury');
INSERT INTO `udmhealthsystem`.`Scale` (`idScale`, `name`, `symbol`, `description`) VALUES (4, 'Molar Concentration', 'mg/dL', ' mass concentration is measured in mg/dL');

COMMIT;


-- -----------------------------------------------------
-- Data for table `udmhealthsystem`.`Measurement_Type`
-- -----------------------------------------------------
START TRANSACTION;
USE `udmhealthsystem`;
INSERT INTO `udmhealthsystem`.`Measurement_Type` (`idMeasurementType`, `code`, `description`, `Scale_idScale`) VALUES (1, 'BLOOD_PRESSURE', 'Blood pressure', 3);
INSERT INTO `udmhealthsystem`.`Measurement_Type` (`idMeasurementType`, `code`, `description`, `Scale_idScale`) VALUES (2, 'FAHRENHEIT', 'Temperature in Fahrenheit', 1);
INSERT INTO `udmhealthsystem`.`Measurement_Type` (`idMeasurementType`, `code`, `description`, `Scale_idScale`) VALUES (3, 'CELSIUS', 'Temperature in Celsius', 2);
INSERT INTO `udmhealthsystem`.`Measurement_Type` (`idMeasurementType`, `code`, `description`, `Scale_idScale`) VALUES (4, 'BLOOD_SUGAR', 'blood sugar concentration', 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `udmhealthsystem`.`Measurement_Attribute`
-- -----------------------------------------------------
START TRANSACTION;
USE `udmhealthsystem`;
INSERT INTO `udmhealthsystem`.`Measurement_Attribute` (`idMeasurementAttribute`, `name`, `description`, `idMeasurementType`) VALUES (1, 'FAHRENHEIT', 'Temperature', 2);
INSERT INTO `udmhealthsystem`.`Measurement_Attribute` (`idMeasurementAttribute`, `name`, `description`, `idMeasurementType`) VALUES (2, 'CELSIUS', 'Temperature', 3);
INSERT INTO `udmhealthsystem`.`Measurement_Attribute` (`idMeasurementAttribute`, `name`, `description`, `idMeasurementType`) VALUES (3, 'SYSTOLIC', 'Systolic', 1);
INSERT INTO `udmhealthsystem`.`Measurement_Attribute` (`idMeasurementAttribute`, `name`, `description`, `idMeasurementType`) VALUES (4, 'DIASTOLIC', 'Diastolic', 1);
INSERT INTO `udmhealthsystem`.`Measurement_Attribute` (`idMeasurementAttribute`, `name`, `description`, `idMeasurementType`) VALUES (5, 'BLOOD_SUGAR', 'The blood sugar concentration', 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `udmhealthsystem`.`medical_staff`
-- -----------------------------------------------------
START TRANSACTION;
USE `udmhealthsystem`;
INSERT INTO `udmhealthsystem`.`medical_staff` (`medical_staff_pk`, `speciality`, `idUser`) VALUES (NULL, 'Doctor', 2);

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
INSERT INTO `udmhealthsystem`.`services` (`id_service`, `service_name`, `description`, `enabled`, `logging`) VALUES (2, 'LOGIN_USER', 'Validates email and password', true, true);
INSERT INTO `udmhealthsystem`.`services` (`id_service`, `service_name`, `description`, `enabled`, `logging`) VALUES (3, 'SEND_BLOOD_PRESSURE', 'Send Blood Pressure measument', true, true);
INSERT INTO `udmhealthsystem`.`services` (`id_service`, `service_name`, `description`, `enabled`, `logging`) VALUES (4, 'TEMPERATURE_MEASUREMENT', 'Body Temperature', true, true);
INSERT INTO `udmhealthsystem`.`services` (`id_service`, `service_name`, `description`, `enabled`, `logging`) VALUES (5, 'BLOOD_SUGAR_MEASUREMENT', 'Blood Sugar Level', true, true);

COMMIT;


-- -----------------------------------------------------
-- Data for table `udmhealthsystem`.`authorities`
-- -----------------------------------------------------
START TRANSACTION;
USE `udmhealthsystem`;
INSERT INTO `udmhealthsystem`.`authorities` (`authority_pk`, `authority`, `Description`) VALUES (1, 'DOCTOR', 'Doctor Authority');
INSERT INTO `udmhealthsystem`.`authorities` (`authority_pk`, `authority`, `Description`) VALUES (2, 'NURSE', 'Nurse Authority');
INSERT INTO `udmhealthsystem`.`authorities` (`authority_pk`, `authority`, `Description`) VALUES (3, 'ADMIN', 'Sytem Administrator');
INSERT INTO `udmhealthsystem`.`authorities` (`authority_pk`, `authority`, `Description`) VALUES (NULL, 'VIEW_LOG_XML', 'Admin');

COMMIT;


-- -----------------------------------------------------
-- Data for table `udmhealthsystem`.`web_users`
-- -----------------------------------------------------
START TRANSACTION;
USE `udmhealthsystem`;
INSERT INTO `udmhealthsystem`.`web_users` (`web_user_pk`, `username`, `password`, `enabled`) VALUES (1, 'oscar@gmail.com', 'ff62e57bf729b39b0df7a5c28077838250ef0c01b0cf97c24833fc051f0c6761bd14ca9d5077034b', 'true');
INSERT INTO `udmhealthsystem`.`web_users` (`web_user_pk`, `username`, `password`, `enabled`) VALUES (NULL, 'jose@jose', 'ff62e57bf729b39b0df7a5c28077838250ef0c01b0cf97c24833fc051f0c6761bd14ca9d5077034b', 'true');

COMMIT;


-- -----------------------------------------------------
-- Data for table `udmhealthsystem`.`user_authorities`
-- -----------------------------------------------------
START TRANSACTION;
USE `udmhealthsystem`;
INSERT INTO `udmhealthsystem`.`user_authorities` (`user_authority_pk`, `username`, `authority_pk`) VALUES (1, 'oscar@gmail.com', 1);
INSERT INTO `udmhealthsystem`.`user_authorities` (`user_authority_pk`, `username`, `authority_pk`) VALUES (2, 'oscar@gmail.com', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `udmhealthsystem`.`user_access`
-- -----------------------------------------------------
START TRANSACTION;
USE `udmhealthsystem`;
INSERT INTO `udmhealthsystem`.`user_access` (`user_access_pk`, `enabled`, `medical_staff_pk`, `idUser`) VALUES (NULL, true, 1, 1);

COMMIT;

