/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50558
 Source Host           : localhost:3306
 Source Schema         : parttime_job

 Target Server Type    : MySQL
 Target Server Version : 50558
 File Encoding         : 65001

 Date: 04/09/2018 09:40:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for applyinfo
-- ----------------------------
DROP TABLE IF EXISTS `applyinfo`;
CREATE TABLE `applyinfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` int(11) NOT NULL,
  `merchant_wantedjob_id` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keycode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对外键',
  `parameter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值',
  `fathernode` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for merchant_baseinfo
-- ----------------------------
DROP TABLE IF EXISTS `merchant_baseinfo`;
CREATE TABLE `merchant_baseinfo`  (
  `merchant_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商家id',
  `merchant_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家名',
  `merchant_username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家登陆用户名',
  `merchant_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家登陆密码',
  `merchant_telephone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家电话',
  `merchant_license` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业执照码',
  `merchant_idcard` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店主身份证',
  `merchant_hostname` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店主名',
  `merchant_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家邮箱',
  `power` int(11) NULL DEFAULT 1 COMMENT '权限',
  PRIMARY KEY (`merchant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for merchant_wantedjob
-- ----------------------------
DROP TABLE IF EXISTS `merchant_wantedjob`;
CREATE TABLE `merchant_wantedjob`  (
  `merchant_id` int(11) NOT NULL,
  `salary` decimal(10, 2) NULL DEFAULT NULL,
  `job_id` int(11) NULL DEFAULT NULL,
  `workplace` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `workdescp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `workcleanform` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `merchant_wantedjob_id` int(11) NOT NULL AUTO_INCREMENT,
  `workdemand` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `workcontent` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `posttime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `power` int(1) UNSIGNED NOT NULL DEFAULT 1,
  PRIMARY KEY (`merchant_wantedjob_id`) USING BTREE,
  INDEX `merchant_id`(`merchant_id`) USING BTREE,
  CONSTRAINT `merchant_wantedjob_ibfk_1` FOREIGN KEY (`merchant_id`) REFERENCES `merchant_baseinfo` (`merchant_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for student_baseinfo
-- ----------------------------
DROP TABLE IF EXISTS `student_baseinfo`;
CREATE TABLE `student_baseinfo`  (
  `stu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '兼职学生id',
  `stu_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '兼职学生姓名',
  `stu_username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '兼职学生用户名',
  `stu_password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '兼职学生密码',
  `stu_telephone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '兼职学生电话',
  `stu_idcard` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '兼职学生身份证',
  PRIMARY KEY (`stu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for student_wantedjob
-- ----------------------------
DROP TABLE IF EXISTS `student_wantedjob`;
CREATE TABLE `student_wantedjob`  (
  `stu_id` int(11) NOT NULL,
  `job` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职业名',
  `workplace` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在学校',
  `salary` decimal(10, 2) NULL DEFAULT NULL COMMENT '薪资',
  `worktime` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预期工作时间',
  PRIMARY KEY (`stu_id`) USING BTREE,
  CONSTRAINT `student_wantedjob_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `student_baseinfo` (`stu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;

--------------------------------------
-- ----------------------------
-- 后台管理员表
-- ----------------------------
DROP TABLE IF EXISTS `Admin`;
CREATE TABLE `Admin`  (
  `admin_id` int NOT NULL primary key AUTO_INCREMENT COMMENT '兼职发布表id',
  `admin_username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'admin用户名',
  `admin_password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'admin密码',
  `admin_jointime` date NOT NULL COMMENT 'admin加入时间',
  `admin_lastip` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT 'ip',
  `admin_permission` int NOT NULL COMMENT '管理员权限：是否为子管理 0/1'
);
-- ----------------------------
-- 广告表
-- ---------------------------- 
DROP TABLE IF EXISTS `Advertise`;
CREATE TABLE Advertise(
  id int NOT NULL primary key AUTO_INCREMENT,
  toURL varchar(500), 
  customer varchar(250) NOT NULL, 
  picture varchar(1000) NOT NULL, 
  describes varchar(1000),
  jointime date NOT NULL, 
  timeway int NOT NULL, 
  status int NOT NULL 
);
