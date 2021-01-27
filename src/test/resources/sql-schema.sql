drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `item_Name` VARCHAR(40) DEFAULT NULL,
    `price` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `fk_cid` INT(11),
    FOREIGN KEY (fk_cid) REFERENCES customers(id),
    PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `ims`.`orderItem` (
    `fk_oid` INT(11),
    FOREIGN KEY (fk_oid) REFERENCES orders(id),
    `fk_iid` INT(11),
    FOREIGN KEY (fk_iid) REFERENCES items(id)   
);
