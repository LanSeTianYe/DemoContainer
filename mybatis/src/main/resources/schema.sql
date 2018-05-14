DROP TABLE if EXISTS `city`
CREATE TABLE `city` (
    `id` INT AUTO_INCREMENT,
    `name` VARCHAR(50),
    `state` VARCHAR(100),
    `country` VARCHAR(100),
    PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;