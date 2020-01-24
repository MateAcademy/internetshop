CREATE SCHEMA `shop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `shop`.`items` (
  `item_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`item_id`));

CREATE TABLE `shop`.`orders` (
  `order_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`order_id`));
  
  CREATE TABLE `shop`.`baskets` (
  `basket_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NULL,
  PRIMARY KEY (`basket_id`));
  
  CREATE TABLE `shop`.`users` (
  `user_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `token` VARCHAR(45) NULL,
  PRIMARY KEY (`user_id`));

CREATE TABLE `shop`.`roles` (
  `role_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`));
  
  CREATE TABLE `shop`.`users_roles` (
  `users_roles_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NOT NULL,
  `role_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`users_roles_id`),
  INDEX `user_id_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `role_id_fk_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `shop`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `role_id_fk`
    FOREIGN KEY (`role_id`)
    REFERENCES `shop`.`roles` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
	
	
	INSERT INTO `shop`.`users` (`user_id`, `name`, `surname`, `email`, `phone`, `login`, `password`, `token`) VALUES ('1', 'Sergey', 'Klunniy', 'ava@gmail.com', '+380501430700', 'ava', '1', 'eee-rrr-ttt');
	
CREATE TABLE `shop`.`items_baskets` (
  `items_baskets_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `basket_id` BIGINT(20) NULL,
  `item_id` BIGINT(20) NULL,
  PRIMARY KEY (`items_baskets_id`),
  INDEX `bask_id_fk_idx` (`basket_id` ASC) VISIBLE,
  INDEX `item_id_fk_idx` (`item_id` ASC) VISIBLE,
  CONSTRAINT `basket_id_fk`
    FOREIGN KEY (`basket_id`)
    REFERENCES `shop`.`baskets` (`basket_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `item_id_fk`
    FOREIGN KEY (`item_id`)
    REFERENCES `shop`.`items` (`item_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
	
	ALTER TABLE `shop`.`orders` 
ADD INDEX `order_id_fk_idx` (`user_id` ASC) VISIBLE;
;
ALTER TABLE `shop`.`orders` 
ADD CONSTRAINT `order_id_fk`
  FOREIGN KEY (`user_id`)
  REFERENCES `shop`.`users` (`user_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

CREATE TABLE `shop`.`orders_items` (
  `orders_items_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `item_id` BIGINT(20) NULL,
  `order_id` BIGINT(20) NULL,
  PRIMARY KEY (`orders_items_id`),
  INDEX `item_id_fk_idx` (`item_id` ASC) VISIBLE,
  INDEX `order_id_fk_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `item_fk`
    FOREIGN KEY (`item_id`)
    REFERENCES `shop`.`items` (`item_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `order_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `shop`.`orders` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
	
	ALTER TABLE `shop`.`baskets` 
CHANGE COLUMN `user_id` `user_id` BIGINT(20) NULL ,
ADD INDEX `user_id_fk_idxx` (`user_id` ASC) VISIBLE;
;
ALTER TABLE `shop`.`baskets` 
ADD CONSTRAINT `basket_fk_users`
  FOREIGN KEY (`user_id`)
  REFERENCES `shop`.`users` (`user_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
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

INSERT INTO `shop`.`users` (`name`, `surname`,`email`, `phone`,`login`, `password`, `token`)
VALUES ('Serge', 'Klunniy', 'ava@gmail.com', '555', 'ava', '1', 'rrr-ttt');

INSERT INTO `shop`.`users` (`name`, `surname`,`email`, `phone`,`login`, `password`, `token`)
VALUES ('Serge2', 'Klunniy2', 'ava2@gmail.com', '555', 'admin', '1', 'rrr-ttt');

INSERT INTO `shop`.`orders` (`order_id`, `user_id`) VALUES ('1', '2');
INSERT INTO `shop`.`orders` (`order_id`, `user_id`) VALUES ('2', '2');
INSERT INTO `shop`.`orders` (`order_id`, `user_id`) VALUES ('3', '3');

INSERT INTO `shop`.`roles` (`role_id`, `role_name`) VALUES ('1', 'ADMIN');
INSERT INTO `shop`.`roles` (`role_id`, `role_name`) VALUES ('2', 'USER');

INSERT INTO `shop`.`items` (`item_id`, `name`, `price`, `description`) VALUES ('1', 'laptop', '12000', 'Ukraine');
INSERT INTO `shop`.`items` (`item_id`, `name`, `price`, `description`) VALUES ('2', 'ipad', '23000', 'USA');

INSERT INTO `shop`.`orders_items` (`orders_items_id`, `item_id`, `order_id`) VALUES ('1', '1', '1');
INSERT INTO `shop`.`orders_items` (`orders_items_id`, `item_id`, `order_id`) VALUES ('2', '2', '1');
INSERT INTO `shop`.`orders_items` (`orders_items_id`, `item_id`, `order_id`) VALUES ('3', '1', '2');

