/*
Navicat MySQL Data Transfer

Source Server         : linuxSQL
Source Server Version : 50724
Source Host           : 47.94.2.98:3306
Source Database       : design

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-03-13 19:44:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for breakdown_detail
-- ----------------------------
DROP TABLE IF EXISTS `breakdown_detail`;
CREATE TABLE `breakdown_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `statusCode` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '故障解决状态，0为待指派，1为已指派正在解决，2为已解决',
  `handler_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `handle_time` datetime DEFAULT NULL,
  `href_id` bigint(20) DEFAULT NULL,
  `handle_info` varchar(255) DEFAULT NULL,
  `isUrged` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='用来记录故障详情的表';

-- ----------------------------
-- Records of breakdown_detail
-- ----------------------------
INSERT INTO `breakdown_detail` VALUES ('2', null, '服务器异常！状态码：500    Url:http://localhost:8080', '0', null, '2019-03-11 11:59:07', null, '4', 'kkkk', null);
INSERT INTO `breakdown_detail` VALUES ('3', null, '服务器异常！状态码：500    Url:http://localhost:8080', '1', null, '2019-03-05 16:01:37', '2019-03-05 16:01:37', '4', null, null);
INSERT INTO `breakdown_detail` VALUES ('4', null, '服务器异常！状态码：500    Url:http://localhost:8080', '1', null, '2019-03-05 16:03:14', '2019-03-05 16:03:14', '4', null, null);
INSERT INTO `breakdown_detail` VALUES ('5', null, '服务器异常！    Url:http://localhost:8110/', '1', '104', '2019-03-05 17:08:48', '2019-03-08 16:27:07', '6', null, null);
INSERT INTO `breakdown_detail` VALUES ('6', null, '服务器异常！    Url:http://localhost:8999', '1', '104', '2019-03-05 17:13:58', '2019-03-08 16:28:05', '7', null, null);
INSERT INTO `breakdown_detail` VALUES ('7', null, '服务器异常！    Url:http://localhost:8111', '1', null, '2019-03-05 17:23:05', '2019-03-05 17:23:05', '8', null, null);
INSERT INTO `breakdown_detail` VALUES ('8', null, '服务器异常！    Url:http://localhost:8777', '1', null, '2019-03-05 17:36:38', '2019-03-05 17:36:38', '9', null, null);
INSERT INTO `breakdown_detail` VALUES ('9', null, 'VS DVD是VS v    url:http://www.baidu.com/', '1', '104', '2019-03-07 20:00:15', '2019-03-08 16:28:08', '10', null, null);
INSERT INTO `breakdown_detail` VALUES ('10', '404', '服务器异常！    Url:http://localhost:8110/', '1', '104', '2019-03-08 16:28:48', '2019-03-08 16:33:47', '6', null, null);
INSERT INTO `breakdown_detail` VALUES ('11', '404', '服务器异常！    Url:http://localhost:8110/', '1', '104', '2019-03-08 16:43:05', '2019-03-08 16:43:48', '6', null, null);
INSERT INTO `breakdown_detail` VALUES ('12', '500', '服务器异常！    Url:http://localhost:8110/', '2', '104', '2019-03-08 16:44:05', null, '6', null, '');
INSERT INTO `breakdown_detail` VALUES ('13', '404', '服务器异常！    Url:https://www.baidu.cm/', '1', '104', '2019-03-08 21:25:28', '2019-03-08 21:27:14', '11', null, '');
INSERT INTO `breakdown_detail` VALUES ('14', '404', '服务器异常！    Url:https://www.baidu.cm/', '2', '104', '2019-03-08 21:28:27', null, '11', null, '');

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `series` varchar(255) NOT NULL,
  `last_used` datetime DEFAULT NULL,
  `token` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`series`),
  UNIQUE KEY `UK_bqa9l0go97v49wwyx4c0u3kpd` (`token`),
  UNIQUE KEY `UK_f8t9fsfwc17s6qcbx0ath6l3h` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for profession_qualify
-- ----------------------------
DROP TABLE IF EXISTS `profession_qualify`;
CREATE TABLE `profession_qualify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `professional_name` varchar(20) COLLATE utf8mb4_persian_ci DEFAULT NULL COMMENT '专家名称',
  `type_code` int(11) DEFAULT NULL COMMENT '申请类别',
  `submit_time` datetime DEFAULT NULL COMMENT '申请时间',
  `urgent` int(11) DEFAULT NULL COMMENT '是否紧急(0.否，1.是)',
  `process_time` datetime DEFAULT NULL COMMENT '处理时间',
  `processor_id` bigint(20) DEFAULT NULL COMMENT '处理员id',
  `processor_code` int(11) DEFAULT NULL COMMENT '后台审核状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_persian_ci COMMENT='专家审核';

-- ----------------------------
-- Records of profession_qualify
-- ----------------------------

-- ----------------------------
-- Table structure for rate_management
-- ----------------------------
DROP TABLE IF EXISTS `rate_management`;
CREATE TABLE `rate_management` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` int(11) DEFAULT NULL COMMENT '费率编号',
  `price` decimal(65,2) DEFAULT NULL COMMENT '费率金额(小数点的为百分比)',
  `change_time` date DEFAULT NULL COMMENT '改变时间',
  `processor_id` bigint(20) DEFAULT NULL COMMENT '处理人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_persian_ci COMMENT='费率管理';

-- ----------------------------
-- Records of rate_management
-- ----------------------------

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmeds2u6ae5usj0ko0bqj3k0eo` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_organization
-- ----------------------------

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `order_num` int(11) NOT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3fekum3ead5klp7y4lckn5ohi` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=255 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', '\0', '顶级栏目', '100', null, '0', null, '0');
INSERT INTO `sys_resource` VALUES ('2', '\0', '权限配置', '7', '', '0', '', '1');
INSERT INTO `sys_resource` VALUES ('3', '\0', '角色管理', '102', '/role', '0', '/role', '2');
INSERT INTO `sys_resource` VALUES ('4', '\0', '权限管理', '103', '/resource', '0', '/resource', '2');
INSERT INTO `sys_resource` VALUES ('5', '\0', '用户管理', '101', '/user', '0', '/user', '2');
INSERT INTO `sys_resource` VALUES ('6', '\0', '编辑', '100', '/role/editor-role', '1', '/role/editor-role', '3');
INSERT INTO `sys_resource` VALUES ('7', '\0', '添加权限节点', '100', '/resource/add-permission', '1', '/resource/add-permission', '4');
INSERT INTO `sys_resource` VALUES ('8', '\0', '添加管理员', '100', '/user/add-user', '1', '/user/add-user', '5');
INSERT INTO `sys_resource` VALUES ('9', '\0', '添加角色', '100', '/role/add-role', '1', '/role/add-role', '3');
INSERT INTO `sys_resource` VALUES ('10', '\0', '删除角色', '100', '/role/delete', '1', '/role/delete', '3');
INSERT INTO `sys_resource` VALUES ('11', '\0', '删除用户', '100', '/user/delete-user', '1', '/user/delete-user', '5');
INSERT INTO `sys_resource` VALUES ('12', '\0', '删除权限', '100', '/resource/delete', '1', '/resource/delete', '4');
INSERT INTO `sys_resource` VALUES ('13', '\0', '启用', '100', '/user/available-user', '1', '/user/available-user', '3');
INSERT INTO `sys_resource` VALUES ('14', '\0', '修改管理员密码', '100', '/user/modify-password', '1', '/user/modify-password', '5');
INSERT INTO `sys_resource` VALUES ('16', null, '权限编辑', '100', '/resource/editor-permission', '1', '/resource/editor-permission', '4');
INSERT INTO `sys_resource` VALUES ('150', '\0', '编辑管理员信息', '100', '/user/edit-user', '1', '/user/edit-user', '5');
INSERT INTO `sys_resource` VALUES ('243', null, '知识库管理', '1', '', '0', '', '1');
INSERT INTO `sys_resource` VALUES ('244', null, '知识库列表', '1', '/admin/knowledge/showList', '0', '/admin/knowledge/showList', '243');
INSERT INTO `sys_resource` VALUES ('245', null, '故障管理', '1', '', '0', '', '1');
INSERT INTO `sys_resource` VALUES ('246', null, '故障列表', '1', '/admin/breakdown/showList', '0', '/admin/breakdown/showList', '245');
INSERT INTO `sys_resource` VALUES ('247', null, '故障监控管理', '1', '', '0', '', '1');
INSERT INTO `sys_resource` VALUES ('248', null, '监控URL管理', '1', '/admin/monitor/showList', '0', '/admin/monitor/showList', '247');
INSERT INTO `sys_resource` VALUES ('249', null, '投诉故障', '1', '', '0', '', '1');
INSERT INTO `sys_resource` VALUES ('250', null, '添加故障', '1', '/admin/complain/toaddcomplain', '0', '/admin/complain/toaddcomplain', '249');
INSERT INTO `sys_resource` VALUES ('251', null, '我的故障任务', '1', '', '0', '', '1');
INSERT INTO `sys_resource` VALUES ('252', null, '故障清单', '1', '/admin/mybreakdown/showList', '0', '/admin/mybreakdown/showList', '251');
INSERT INTO `sys_resource` VALUES ('253', null, '故障统计', '1', '', '0', '', '1');
INSERT INTO `sys_resource` VALUES ('254', null, '故障分类统计图', '1', '/admin/statistic', '0', '/admin/statistic', '253');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', null, '管理员', '管理员');
INSERT INTO `sys_role` VALUES ('33', null, '', '维修人员');
INSERT INTO `sys_role` VALUES ('34', null, '', '用户');
INSERT INTO `sys_role` VALUES ('35', null, '', '客服人员');

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `resource_id` bigint(20) DEFAULT NULL COMMENT '资源id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_persian_ci COMMENT='用户可查看资源表';

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resources`;
CREATE TABLE `sys_role_resources` (
  `sys_role_id` bigint(20) NOT NULL,
  `resources_id` bigint(20) NOT NULL,
  KEY `FKog6jj4v6yh9e1ilxk2mwuk75a` (`resources_id`),
  KEY `FKsqkqfd2hpr5cc2kbrtgoced2w` (`sys_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resources
-- ----------------------------
INSERT INTO `sys_role_resources` VALUES ('34', '249');
INSERT INTO `sys_role_resources` VALUES ('34', '250');
INSERT INTO `sys_role_resources` VALUES ('35', '243');
INSERT INTO `sys_role_resources` VALUES ('35', '244');
INSERT INTO `sys_role_resources` VALUES ('35', '245');
INSERT INTO `sys_role_resources` VALUES ('35', '246');
INSERT INTO `sys_role_resources` VALUES ('35', '249');
INSERT INTO `sys_role_resources` VALUES ('35', '250');
INSERT INTO `sys_role_resources` VALUES ('1', '2');
INSERT INTO `sys_role_resources` VALUES ('1', '3');
INSERT INTO `sys_role_resources` VALUES ('1', '6');
INSERT INTO `sys_role_resources` VALUES ('1', '9');
INSERT INTO `sys_role_resources` VALUES ('1', '10');
INSERT INTO `sys_role_resources` VALUES ('1', '13');
INSERT INTO `sys_role_resources` VALUES ('1', '4');
INSERT INTO `sys_role_resources` VALUES ('1', '7');
INSERT INTO `sys_role_resources` VALUES ('1', '12');
INSERT INTO `sys_role_resources` VALUES ('1', '16');
INSERT INTO `sys_role_resources` VALUES ('1', '5');
INSERT INTO `sys_role_resources` VALUES ('1', '8');
INSERT INTO `sys_role_resources` VALUES ('1', '11');
INSERT INTO `sys_role_resources` VALUES ('1', '14');
INSERT INTO `sys_role_resources` VALUES ('1', '150');
INSERT INTO `sys_role_resources` VALUES ('1', '243');
INSERT INTO `sys_role_resources` VALUES ('1', '244');
INSERT INTO `sys_role_resources` VALUES ('1', '245');
INSERT INTO `sys_role_resources` VALUES ('1', '246');
INSERT INTO `sys_role_resources` VALUES ('1', '247');
INSERT INTO `sys_role_resources` VALUES ('1', '248');
INSERT INTO `sys_role_resources` VALUES ('1', '249');
INSERT INTO `sys_role_resources` VALUES ('1', '250');
INSERT INTO `sys_role_resources` VALUES ('1', '253');
INSERT INTO `sys_role_resources` VALUES ('1', '254');
INSERT INTO `sys_role_resources` VALUES ('33', '249');
INSERT INTO `sys_role_resources` VALUES ('33', '250');
INSERT INTO `sys_role_resources` VALUES ('33', '251');
INSERT INTO `sys_role_resources` VALUES ('33', '252');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createtime` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `available` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `sex_type` int(11) DEFAULT NULL COMMENT '性别(0.男,1.女)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '2017-07-11 17:42:18', '$2a$10$SIU57gfkh/TsIVYALXBNAeDnQzkm652FT9cg4h8wtEfC306uliyYa', '2019-01-11 07:34:38', 'admin', '', '1191134106@qq.com', '15030103078', '1');
INSERT INTO `sys_user` VALUES ('104', '2019-03-08 14:42:05', '$2a$10$YMjVi7kABZS5biPQmPbl/.BbGZ0GMMyHGqZsxQB49trZWsb0xzfmG', '2019-03-08 14:42:05', 'repair1', '', '1191134106@qq.com', '13920121599', '1');
INSERT INTO `sys_user` VALUES ('105', '2019-03-09 09:27:39', '$2a$10$yd9M/S7CwlwtkJzRomvY2.vpU7m.FrgR7N6rKQztlQ8Alb6nvfBje', '2019-03-09 09:27:39', 'kefu', '', '1191134106@qq.com', '13920121594', '1');
INSERT INTO `sys_user` VALUES ('106', '2019-03-09 09:28:14', '$2a$10$QaWhDdYNfn54VbtvpmZJP.wWdK/3V.mB4rTjCG4i/XwXzYsJuO5nK', '2019-03-09 09:28:14', 'user', '', '1191134106@qq.com', '13920121599', '1');

-- ----------------------------
-- Table structure for sys_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_roles`;
CREATE TABLE `sys_user_roles` (
  `sys_user_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  KEY `FKdpvc6d7xqpqr43dfuk1s27cqh` (`roles_id`),
  KEY `FKd0ut7sloes191bygyf7a3pk52` (`sys_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_roles
-- ----------------------------
INSERT INTO `sys_user_roles` VALUES ('1', '1');
INSERT INTO `sys_user_roles` VALUES ('104', '33');
INSERT INTO `sys_user_roles` VALUES ('105', '35');
INSERT INTO `sys_user_roles` VALUES ('106', '34');

-- ----------------------------
-- Table structure for t_knowledge
-- ----------------------------
DROP TABLE IF EXISTS `t_knowledge`;
CREATE TABLE `t_knowledge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `writer_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='知识库信息表';

-- ----------------------------
-- Records of t_knowledge
-- ----------------------------
INSERT INTO `t_knowledge` VALUES ('6', '擦拭擦拭从', '啊是擦撒擦', '1', '2019-03-04 21:23:27');
INSERT INTO `t_knowledge` VALUES ('7', '擦撒撒吃撒', '啊是擦撒擦', '1', '2019-03-04 21:23:32');
INSERT INTO `t_knowledge` VALUES ('8', '擦撒擦擦撒', '撒擦撒吃撒从', '1', '2019-03-04 21:23:38');

-- ----------------------------
-- Table structure for t_monitor_href
-- ----------------------------
DROP TABLE IF EXISTS `t_monitor_href`;
CREATE TABLE `t_monitor_href` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `href` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='用来存储需要监控的服务器链接地址的表';

-- ----------------------------
-- Records of t_monitor_href
-- ----------------------------
INSERT INTO `t_monitor_href` VALUES ('2', 'https://www.baidu.com/', '0');
INSERT INTO `t_monitor_href` VALUES ('3', 'https://blog.csdn.net/qq_37856300', '0');
INSERT INTO `t_monitor_href` VALUES ('4', 'http://localhost:8080', '1');
INSERT INTO `t_monitor_href` VALUES ('6', 'http://localhost:8110/', '1');
INSERT INTO `t_monitor_href` VALUES ('7', 'http://localhost:8999', '1');
INSERT INTO `t_monitor_href` VALUES ('10', 'http://www.baidu.com/', '0');
INSERT INTO `t_monitor_href` VALUES ('11', 'https://www.baidu.cm/', '1');
