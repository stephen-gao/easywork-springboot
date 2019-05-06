/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80016
Source Host           : 192.168.162.134:3306
Source Database       : easywork

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-05-06 17:11:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `nickname` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '昵称',
  `tel` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `register_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '极光推送识别码',
  `open_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '微信openId',
  `balance` decimal(11,2) unsigned zerofill DEFAULT '000000000.00' COMMENT '余额',
  `vip` int(4) unsigned zerofill DEFAULT '0000' COMMENT 'vip 0/普通用户 1/v1',
  `status` int(2) unsigned zerofill DEFAULT '00' COMMENT '账号状态 0/正常 1/监控 2/异常 3/冻结 5/锁定 6/暂停 9/注销',
  `salt` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '盐值',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `edit_time` datetime DEFAULT NULL COMMENT '编辑时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('8f5689836eef11e9869d000c29242eff', 'zhangsan', 'E9B889441E30637EED6BD9EB71E8554E', '张三', null, null, null, null, '000000000.00', '0000', '00', 'f0dfe54b41159da25d4ffe8a75aa69cc', '2019-05-05 12:38:00', '2019-05-05 12:38:00');
INSERT INTO `user` VALUES ('9492e6f16eef11e9869d000c29242eff', 'lisi', 'FFC7B8BBFCA393E7DC187FD8026578E2', '李四', null, null, null, null, '000000000.00', '0000', '00', 'dc4ca5c1db4a7a6da810a55223a2b83a', '2019-05-05 12:38:09', '2019-05-05 12:38:09');
INSERT INTO `user` VALUES ('d7576b1b6f0011e9869d000c29242eff', 'wangwu', '97701F2531B14820624AD33C524BE751', '王五', null, null, null, null, '000000000.00', '0000', '00', 'e8f6572cadeeb68af32dcfd4cd6055b0', '2019-05-05 14:41:42', '2019-05-05 14:41:42');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  `country` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '国家',
  `province` int(16) DEFAULT NULL COMMENT '省会',
  `city` int(16) DEFAULT NULL COMMENT '城市',
  `id_card` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '身份证',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of user_info
-- ----------------------------
