/*
 Navicat Premium Data Transfer

 Source Server         : syscode
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35)
 Source Host           : localhost:3306
 Source Schema         : gl-core

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35)
 File Encoding         : 65001

 Date: 21/03/2024 09:17:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gl_account
-- ----------------------------
DROP TABLE IF EXISTS `gl_account`;
CREATE TABLE `gl_account`  (
  `id` int NOT NULL,
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gl_account
-- ----------------------------
INSERT INTO `gl_account` VALUES (-197070846, 'glancake', '123');

-- ----------------------------
-- Table structure for gl_mes
-- ----------------------------
DROP TABLE IF EXISTS `gl_mes`;
CREATE TABLE `gl_mes`  (
  `id` int NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `account_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gl_mes
-- ----------------------------
INSERT INTO `gl_mes` VALUES (-1371475967, 'hello', '2024-03-21 08:44:00', -197070846);
INSERT INTO `gl_mes` VALUES (268529666, 'hello2', '2024-03-21 08:50:01', -197070846);
INSERT INTO `gl_mes` VALUES (1010847745, 'hello2', '2024-03-21 08:51:29', -197070846);
INSERT INTO `gl_mes` VALUES (1417736194, 'how is everything going', '2024-03-21 08:43:26', -197070846);

SET FOREIGN_KEY_CHECKS = 1;
