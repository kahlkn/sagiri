

DROP TABLE IF EXISTS `t_web_page_address_category`;
CREATE TABLE `t_web_page_address_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '网页地址分类ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父分类ID',
  `name` varchar(50) NOT NULL COMMENT '分类名称',
  `description` varchar(150) NOT NULL DEFAULT '' COMMENT '分类描述',
  `creator_id` varchar(32) NOT NULL COMMENT '创建者ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater_id` varchar(32) NOT NULL COMMENT '更新者ID',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `alive_flag` tinyint(4) DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='网页地址分类表';

DROP TABLE IF EXISTS `t_web_page_address`;
CREATE TABLE `t_web_page_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '网页地址ID',
  `name` varchar(100) NOT NULL COMMENT '地址名称',
  `url` varchar(300) NOT NULL COMMENT '网页地址',
  `icon` varchar(500) NOT NULL DEFAULT '' COMMENT '网站图标',
  `description` varchar(300) NOT NULL DEFAULT '' COMMENT '地址描述',
  `creator_id` varchar(32) NOT NULL COMMENT '创建者ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater_id` varchar(32) NOT NULL COMMENT '更新者ID',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `alive_flag` tinyint(4) DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`) USING BTREE,
  KEY `idx_url` (`url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='网页地址表';







