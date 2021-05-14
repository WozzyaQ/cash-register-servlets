-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema projectdb
-- -----------------------------------------------------
-- Database for final project
DROP SCHEMA IF EXISTS `projectdb` ;

-- -----------------------------------------------------
-- Schema projectdb
--
-- Database for final project
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `projectdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `projectdb` ;

-- -----------------------------------------------------
-- Table `projectdb`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projectdb`.`product` ;

CREATE TABLE IF NOT EXISTS `projectdb`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `code` VARCHAR(10) NOT NULL,
  `stock_amount` INT UNSIGNED NOT NULL,
  `description` VARCHAR(100) NULL,
  `price` DECIMAL(15,2) UNSIGNED NOT NULL,
  `last_edited` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projectdb`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projectdb`.`role` ;

CREATE TABLE IF NOT EXISTS `projectdb`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` ENUM("CASHIER", "COMMODITY_EXPERT", "SENIOR_CASHIER", "ON_HOLD") NOT NULL DEFAULT 'ON_HOLD',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `role_UNIQUE` (`role` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projectdb`.`worker`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projectdb`.`worker` ;

CREATE TABLE IF NOT EXISTS `projectdb`.`worker` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(15) NULL,
  `name` VARCHAR(20) NULL,
  `surname` VARCHAR(20) NULL,
  `email` VARCHAR(45) NULL,
  `password` CHAR(128) NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE,
  INDEX `fk_worker_role1_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_worker_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `projectdb`.`role` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projectdb`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projectdb`.`order` ;

CREATE TABLE IF NOT EXISTS `projectdb`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_by_worker_id` INT NOT NULL,
  `is_closed` TINYINT(1) NULL DEFAULT 0,
  `date_closed` TIMESTAMP NULL,
  `date_created` TIMESTAMP NOT NULL DEFAULT now(),
  `comment` VARCHAR(100) NULL,
  `last_edited` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ordered_by` VARCHAR(45) NULL,
  `total` DECIMAL(15,2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_order_worker1_idx` (`created_by_worker_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_worker1`
    FOREIGN KEY (`created_by_worker_id`)
    REFERENCES `projectdb`.`worker` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projectdb`.`order_product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projectdb`.`order_product` ;

CREATE TABLE IF NOT EXISTS `projectdb`.`order_product` (
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT UNSIGNED NOT NULL DEFAULT 1,
  `price` DECIMAL(15,2) NULL,
  `last_edited` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`, `product_id`),
  INDEX `fk_order_has_product_product1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_order_has_product_order1_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_has_product_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `projectdb`.`order` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_has_product_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `projectdb`.`product` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;

USE `projectdb`;

DELIMITER $$

USE `projectdb`$$
DROP TRIGGER IF EXISTS `projectdb`.`bi_order_product_update_total_amount` $$
USE `projectdb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `projectdb`.`bi_order_product_update_total_amount` BEFORE INSERT ON `order_product` FOR EACH ROW
BEGIN
	DECLARE newStockAmount int;
    
    -- set price in order_product according to current
    -- price in product
    SET NEW.price = (select price from product where id = NEW.product_id);
    
    -- calculate new amount of current inserted product
    SET newStockAmount = (select product.stock_amount from product where id = NEW.product_id) - NEW.quantity;
    
    if (newStockAmount < 0) THEN
		SIGNAL sqlstate '45001' SET MESSAGE_TEXT = "Not enough products in stock";
	END IF;
    
    -- update the product's amount
UPDATE product 
SET 
    product.stock_amount = newStockAmount
WHERE
    id = NEW.product_id;
    
UPDATE `order` 
SET 
    total = total + NEW.price * NEW.quantity
WHERE
    `order`.`id` = NEW.order_id;
END$$


USE `projectdb`$$
DROP TRIGGER IF EXISTS `projectdb`.`bu_order_product_return_in_stock_and_recalculate_total` $$
USE `projectdb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `projectdb`.`bu_order_product_return_in_stock_and_recalculate_total` BEFORE UPDATE ON `order_product` FOR EACH ROW
BEGIN
	DECLARE newStockAmount int;
    
    DECLARE oldTotalProductOrder DECIMAL(15,2);
    DECLARE newTotalProductOrder DECIMAL (15,2);
    
    -- calculate new total in node
    set newTotalProductOrder = NEW.price * NEW.quantity;
    
    -- calculate old total in node
    set oldTotalProductOrder = OLD.price * OLD.quantity;
    
    -- calculate new stock amount
    set newStockAmount = (select product.stock_amount from product where id = OLD.product_id) + OLD.quantity - NEW.quantity;
    
    if (newStockAmount < 0) THEN
		SIGNAL sqlstate '45001' set message_text = "NOT ENOUGH PRODUCTS IN STOCK";
	END IF;
	UPDATE product 
    
-- recalculate stock amount
SET 
    product.stock_amount = newStockAmount
WHERE
    product.id = OLD.product_id;
    
UPDATE `order` 

-- recalculate total
SET 
    total = total - oldTotalProductOrder + newTotalProductOrder
WHERE
    `order`.`id` = OLD.order_id;
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
