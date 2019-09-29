CREATE DATABASE IF NOT EXISTS `sooksook`;

DROP TABLE IF EXISTS `sooksook`.`user`;

CREATE TABLE `sooksook`.`user` (
  `id` varchar(23) NOT NULL,
  `nickname` varchar(45) NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8mb4;

CREATE TABLE `sooksook`.`favorite` (
  `user_id` varchar(23) NOT NULL,
  `plant_id` bigint(11) NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`user_id`, `plant_id`)
) DEFAULT CHARSET=utf8mb4;

CREATE TABLE `sooksook`.`comment` (
  `id` bigint(11)   NOT NULL AUTO_INCREMENT,
  `root_id` bigint(11)   NOT NULL,
  `user_id` varchar(23) NOT NULL,
  `plant_id` bigint(11) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `contents` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX (`plant_id`)
) DEFAULT CHARSET=utf8mb4;