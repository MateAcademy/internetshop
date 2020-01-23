ALTER SCHEMA `shop`
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;

USE `shop`;

CREATE TABLE `items` (
  `item_id`     int NOT NULL AUTO_INCREMENT,
  `name`        varchar(45)  DEFAULT NULL,
  `price`       double       DEFAULT NULL,
  `description` varchar(45)  DEFAULT NULL,
  PRIMARY KEY (`item_id`)
);

insert into shop.items
values ('1', 'car', '900', 'made in Ukraine');
insert into shop.items
values ('2', 'car2', '1100', 'made in Ukraine');
insert into shop.items
values ('3', 'car3', '1300', 'made in Germany');
insert into shop.items
values ('4', 'car4', '1500', 'made in France');
insert into shop.items
values ('5', 'car5', '13600', 'made in Russia');

ALTER TABLE `shop`.`items`
  CHANGE COLUMN `name` `name` VARCHAR(45) CHARACTER SET 'utf8mb4'
COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  CHANGE COLUMN `price` `price` DECIMAL(10, 0) NOT NULL,
  CHANGE COLUMN `description` `description` VARCHAR(1000) CHARACTER SET 'utf8mb4'
COLLATE 'utf8mb4_0900_ai_ci' NULL;

CREATE TABLE `shop`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`order_id`)
);

CREATE TABLE `shop`.`orders_items` (
  `idorders_items_id` INT NOT NULL AUTO_INCREMENT,
  `order_id`          INT NOT NULL,
  `item_id`           INT NOT NULL,
  PRIMARY KEY (`idorders_items_id`),
  INDEX `orders_items_orders_fl_idx` (`order_id` ASC) VISIBLE,
  INDEX `orders_items_items_fk_idx` (`item_id` ASC) VISIBLE,
  CONSTRAINT `orders_items_orders_fl`
  FOREIGN KEY (`order_id`)
  REFERENCES `shop`.`orders` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `orders_items_items_fk`
  FOREIGN KEY (`item_id`)
  REFERENCES `shop`.`items` (`item_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

ALTER TABLE `shop`.`orders`
  ADD COLUMN `user_id` INT NOT NULL
  AFTER `order_id`,
  ADD INDEX `ordrs_users_fk_idx` (`user_id` ASC) VISIBLE;
;
ALTER TABLE `shop`.`orders`
  ADD CONSTRAINT `ordrs_users_fk`
FOREIGN KEY (`user_id`)
REFERENCES `shop`.`users` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

INSERT INTO `shop`.`orders` (`order_id`, `user_id`) VALUES ('1', '1');
INSERT INTO `shop`.`orders` (`order_id`, `user_id`) VALUES ('2', '1');
INSERT INTO `shop`.`orders` (`order_id`, `user_id`) VALUES ('3', '2');

INSERT INTO `shop`.`orders_items` (`idorders_items_id`, `order_id`, `item_id`) VALUES ('1', '1', '1');
INSERT INTO `shop`.`orders_items` (`idorders_items_id`, `order_id`, `item_id`) VALUES ('2', '1', '2');
INSERT INTO `shop`.`orders_items` (`idorders_items_id`, `order_id`, `item_id`) VALUES ('3', '2', '4');
INSERT INTO `shop`.`orders_items` (`idorders_items_id`, `order_id`, `item_id`) VALUES ('4', '3', '2');
INSERT INTO `shop`.`orders_items` (`idorders_items_id`, `order_id`, `item_id`) VALUES ('5', '3', '5');

ALTER TABLE `shop`.`users`
ADD COLUMN `bucket_id` VARCHAR(45) NULL AFTER `salt`,
CHANGE COLUMN `roles` `salt` VARCHAR(45) NULL ;

CREATE TABLE `shop`.`user_roles` (
  `iduser_roles_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(45) NULL,
  `role_id` VARCHAR(45) NULL,
  PRIMARY KEY (`iduser_roles_id`));

CREATE TABLE `shop`.`roles` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(45) NULL,
  PRIMARY KEY (`role_id`));

INSERT INTO `shop`.`roles` (`role_id`, `role_name`) VALUES ('1', 'ADMIN');
INSERT INTO `shop`.`roles` (`role_id`, `role_name`) VALUES ('2', 'USER');
