

DROP TABLE IF EXISTS `t_web_address_category`;
CREATE TABLE `t_web_address_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '网址分类ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父分类ID',
  `name` varchar(50) NOT NULL COMMENT '分类名称',
  `description` varchar(200) NOT NULL DEFAULT '' COMMENT '分类描述',
  `sort` int(6) NOT NULL DEFAULT '0' COMMENT '排序',
  `creator_id` varchar(32) NOT NULL COMMENT '创建者ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater_id` varchar(32) NOT NULL COMMENT '更新者ID',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `alive_flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11001001 DEFAULT CHARSET=utf8mb4 COMMENT='网址分类表';

INSERT INTO `t_web_address_category` VALUES (11001001, NULL, '未分类', '', 0, 'system', '2021-02-25 00:00:01', 'system', '2021-02-25 00:00:01', 1);

DROP TABLE IF EXISTS `t_web_address`;
CREATE TABLE `t_web_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '网址ID',
  `category_id` bigint(20) NOT NULL DEFAULT '11001001' COMMENT '网址分类ID',
  `name` varchar(100) NOT NULL COMMENT '网页名称',
  `address` varchar(300) NOT NULL COMMENT '网页地址',
  `icon` varchar(500) NOT NULL DEFAULT '' COMMENT '网页图标',
  `description` varchar(300) NOT NULL DEFAULT '' COMMENT '网页描述',
  `sort` int(6) NOT NULL DEFAULT '0' COMMENT '排序',
  `creator_id` varchar(32) NOT NULL COMMENT '创建者ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater_id` varchar(32) NOT NULL COMMENT '更新者ID',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `alive_flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`id`),
  KEY `idx_category_id` (`category_id`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE,
  KEY `idx_url` (`url`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1100010001 DEFAULT CHARSET=utf8mb4 COMMENT='网页地址表';







