DROP TABLE IF EXISTS `person`;
CREATE TABLE `person`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES (1, 'sunfeilong2');
INSERT INTO `person` VALUES (2, 'sunfeilong2');
INSERT INTO `person` VALUES (17, '123');
INSERT INTO `person` VALUES (18, '123');
INSERT INTO `person` VALUES (19, '123');
INSERT INTO `person` VALUES (20, '123');