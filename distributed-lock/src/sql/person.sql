DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `id` int(11) unsigned zerofill NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `version` bigint(20) unsigned zerofill NOT NULL,
  `count` bigint(20) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `person` VALUES ('00000000001', 'name', '00000000000000000001', '00000000000000000001');