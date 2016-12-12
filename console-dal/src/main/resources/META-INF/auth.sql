/*
Navicat MySQL Data Transfer

Source Server         : 10.65.216.64-fun49&50
Source Server Version : 50621
Source Host           : 10.65.216.64:3310
Source Database       : fcm3_func50

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-09-25 17:43:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_permission`;
CREATE TABLE `t_sys_permission` (
`PERMISSION_ID`  int(11) NOT NULL AUTO_INCREMENT ,
`PARENT_ID`  int(11) NULL DEFAULT NULL ,
`PERMISSION`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`DESCRIPTION`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`URL`  varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`TYPE`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`SORT`  int(11) NULL DEFAULT NULL ,
`VALID_FLG`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'Y' ,
PRIMARY KEY (`PERMISSION_ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=11

;

-- ----------------------------
-- Records of t_sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_permission` VALUES ('1', null, 'instOrder:query', '查询', '', 'B', null, 'Y'), ('2', null, 'instOrder:delete', '删除', '', 'B', null, 'Y'), ('3', null, 'instOrder:new', '增加', '', 'B', null, 'Y'), ('4', null, 'instOrder:update', '更改', '', 'B', null, 'Y'), ('5', null, 'order', '订单管理', null, 'M', null, 'Y'), ('6', '5', 'order:instOder', '机构订单管理', 'http://127.0.0.1/console/order/instOrder/init', 'M', null, 'Y'), ('7', '5', 'order:baidu', '百度', '#', 'M', null, 'Y'), ('8', null, 'system', '系统管理', '', 'M', null, 'Y'), ('9', '8', 'system:sina', '新浪', 'http://www.sina.com.cn', 'M', null, 'Y'), ('10', '7', 'order:baidu:subbaidu', '百度图片', 'http://pic.baidu.com', 'M', null, 'Y');
COMMIT;

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
`ROLE_ID`  int(11) NOT NULL ,
`ROLE_NAME`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`ROLE_ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_role` VALUES ('1', '管理员'), ('2', 'developer'), ('3', 'tester'), ('4', 'maintainer');
COMMIT;

-- ----------------------------
-- Table structure for t_sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_permission`;
CREATE TABLE `t_sys_role_permission` (
`RELATION_ID`  int(11) NOT NULL AUTO_INCREMENT ,
`ROLE_ID`  int(11) NOT NULL ,
`PERMISSION_ID`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`RELATION_ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=15

;

-- ----------------------------
-- Records of t_sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_role_permission` VALUES ('1', '1', '1'), ('2', '1', '2'), ('3', '1', '3'), ('4', '1', '4'), ('5', '2', '1'), ('6', '3', '1'), ('7', '3', '2'), ('8', '3', '3'), ('9', '1', '5'), ('10', '1', '6'), ('11', '1', '7'), ('12', '1', '8'), ('13', '1', '9'), ('14', '1', '10');
COMMIT;

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
`USER_ID`  int(11) NOT NULL AUTO_INCREMENT ,
`NAME`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`PASSWORD`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`FULL_NAME`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`EMAIL`  varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`PHONE`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`VALID_FLG`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'Y' ,
PRIMARY KEY (`USER_ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=4

;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_user` VALUES ('1', 'David', '123@123', null, '123@163.com', '021-56213306', 'Y'), ('2', 'Lucy', '123@123', null, '123@163.com', '021-56213306', 'Y'), ('3', 'wangshaobo', '1', '王少博', '11@163.com', '021-56213306', 'Y');
COMMIT;

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role` (
`RELATION_ID`  int(11) NOT NULL AUTO_INCREMENT ,
`USER_ID`  int(11) NOT NULL ,
`ROLE_ID`  int(11) NOT NULL ,
PRIMARY KEY (`RELATION_ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=6

;

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_user_role` VALUES ('1', '1', '1'), ('2', '1', '2'), ('3', '1', '3'), ('4', '2', '3'), ('5', '3', '1');
COMMIT;

-- ----------------------------
-- Auto increment value for t_sys_permission
-- ----------------------------
ALTER TABLE `t_sys_permission` AUTO_INCREMENT=11;

-- ----------------------------
-- Auto increment value for t_sys_role_permission
-- ----------------------------
ALTER TABLE `t_sys_role_permission` AUTO_INCREMENT=15;

-- ----------------------------
-- Auto increment value for t_sys_user
-- ----------------------------
ALTER TABLE `t_sys_user` AUTO_INCREMENT=4;

-- ----------------------------
-- Auto increment value for t_sys_user_role
-- ----------------------------
ALTER TABLE `t_sys_user_role` AUTO_INCREMENT=6;
