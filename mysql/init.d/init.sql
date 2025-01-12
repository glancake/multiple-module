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
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gl_account
-- ----------------------------
DROP TABLE IF EXISTS `gl_account`;
CREATE TABLE `gl_account`
(
    `id`       int NOT NULL,
    `account`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gl_account
-- ----------------------------
INSERT INTO `gl_account`
VALUES (-197070846, 'glancake', '123');

-- ----------------------------
-- Table structure for gl_mes
-- ----------------------------
DROP TABLE IF EXISTS `gl_mes`;
CREATE TABLE `gl_mes`
(
    `id`         int      NOT NULL,
    `content`    text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
    `create_at`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `account_id` int NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gl_mes
-- ----------------------------
INSERT INTO `gl_mes`
VALUES (-1371475967, 'hello', '2024-03-21 08:44:00', -197070846);
INSERT INTO `gl_mes`
VALUES (268529666, 'hello2', '2024-03-21 08:50:01', -197070846);
INSERT INTO `gl_mes`
VALUES (1010847745, 'hello2', '2024-03-21 08:51:29', -197070846);
INSERT INTO `gl_mes`
VALUES (1417736194, 'how is everything going', '2024-03-21 08:43:26', -197070846);



CREATE TABLE permissions
(
    id          BIGINT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE user_permissions
(
    user_id       BIGINT,
    permission_id BIGINT,
    PRIMARY KEY (user_id, permission_id)
);

CREATE TABLE organizations
(
    organization_id BIGINT PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    description     TEXT,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    owner_user_id   BIGINT,
    status          ENUM('active', 'inactive', 'pending') DEFAULT 'pending'
);

CREATE TABLE organization_members
(
    organization_member_id BIGINT PRIMARY KEY,
    organization_id        BIGINT,
    user_id                BIGINT,
    role                   ENUM('owner', 'admin', 'member') DEFAULT 'member',
    join_date              TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status                 ENUM('active', 'pending', 'blocked') DEFAULT 'active',
    UNIQUE KEY unique_member (organization_id, user_id) -- 避免重复成员
);

CREATE TABLE organization_join_requests
(
    request_id           BIGINT PRIMARY KEY,
    organization_id      BIGINT,
    user_id              BIGINT,
    request_type         ENUM('join', 'leave') NOT NULL,
    request_date         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status               ENUM('pending', 'approved', 'rejected') DEFAULT 'pending',
    processed_by_user_id BIGINT,
    processed_date       TIMESTAMP
);
