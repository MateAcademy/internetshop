ALTER SCHEMA `shop`  DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_general_ci ;

USE `shop`;

CREATE TABLE `items` (
  `item_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ;

insert into shop.items values('1', 'car', '900', 'made in Ukraine');
insert into shop.items values('2', 'car2', '1100', 'made in Ukraine');
insert into shop.items values('3', 'car3', '1300', 'made in Germany');
insert into shop.items values('4', 'car4', '1500', 'made in France');
insert into shop.items values('5', 'car5', '13600', 'made in Russia');
