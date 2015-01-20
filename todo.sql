DROP SCHEMA IF EXISTS `todo` ;
CREATE SCHEMA IF NOT EXISTS `todo` DEFAULT CHARACTER SET utf8 ;

USE todo;

DROP TABLE IF EXISTS `task` ;
CREATE TABLE IF NOT EXISTS `task` (
  `task_id` INT(11) NOT NULL auto_increment,
  `name` VARCHAR(255) DEFAULT '',
  `description` TEXT DEFAULT NULL,
  `status` TINYINT(1) NOT NULL DEFAULT 0,
  `category_id` INT(11) NOT NULL DEFAULT 1,
  `last_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY ( `task_id` )
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS `category` ;
CREATE TABLE IF NOT EXISTS `category` (
  `category_id` INT(11) NOT NULL auto_increment,
  `name` VARCHAR(255) DEFAULT '',
  PRIMARY KEY ( `category_id` )
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `category` (`category_id`, `name`) VALUES (1,'作業');
INSERT INTO `category` (`category_id`, `name`) VALUES (2,'報告');
INSERT INTO `category` (`category_id`, `name`) VALUES (3,'プライベート');
