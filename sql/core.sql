


DROP TABLE IF EXISTS `t_article_category`;
CREATE TABLE `t_article_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章分类ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父分类ID',
  `code` varchar(40) NOT NULL COMMENT '分类代码',
  `name` varchar(50) NOT NULL COMMENT '分类名称',
  `description` varchar(200) NOT NULL DEFAULT '' COMMENT '分类描述',
  `creator_id` varchar(32) NOT NULL COMMENT '创建者ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater_id` varchar(32) NOT NULL COMMENT '更新者ID',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `alive_flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章分类表';

DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` varchar(32) NOT NULL COMMENT '文章ID',
  `number_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章的数字ID',
  `title` varchar(100) NOT NULL COMMENT '文章标题',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '文章类型',
  `category` varchar(40) NOT NULL COMMENT '文章分类',
  `author_id` varchar(32) NOT NULL COMMENT '作者ID',
  `author_name` varchar(50) NOT NULL COMMENT '作者名称',
  `content` longtext NOT NULL COMMENT '文章内容',
  `origin` varchar(300) NOT NULL DEFAULT '' COMMENT '文章来源',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态：0 草稿，1 发布',
  `release_time` datetime DEFAULT NULL COMMENT '发布时间',
  `creator_id` varchar(32) NOT NULL COMMENT '创建者ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater_id` varchar(32) NOT NULL COMMENT '更新者ID',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `alive_flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`number_id`),
  KEY `idx_id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';










CREATE TABLE `t_system_user` (
  `uid` varchar(32) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(200) NOT NULL,
  `avatar` varchar(350) DEFAULT '',
  `real_name` varchar(40) DEFAULT '',
  `phone_number` varchar(20) DEFAULT '',
  `introduce` varchar(500) DEFAULT '',
  `status` tinyint(4) DEFAULT '0',
  `role_code` varchar(50) NOT NULL,
  `register_type` tinyint(4) DEFAULT '0',
  `register_date` datetime DEFAULT NULL,
  `enabled` tinyint(4) DEFAULT '1',
  `creator_id` varchar(32) NOT NULL COMMENT '创建者ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater_id` varchar(32) NOT NULL COMMENT '更新者ID',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `alive_flag` tinyint(4) DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `idx_username` (`username`) USING HASH,
  KEY `idx_real_name` (`real_name`) USING BTREE,
  KEY `idx_phone_number` (`phone_number`) USING BTREE,
  KEY `idx_status` (`status`) USING BTREE,
  KEY `idx_role_code` (`role_code`) USING BTREE,
  KEY `idx_register_date` (`register_date`) USING BTREE,
  KEY `idx_enabled` (`enabled`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';






