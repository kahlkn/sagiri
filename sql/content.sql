

DROP TABLE IF EXISTS `t_article_category`;
CREATE TABLE `t_article_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章分类ID',
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
) ENGINE=InnoDB AUTO_INCREMENT=11001001 DEFAULT CHARSET=utf8mb4 COMMENT='文章分类表';

INSERT INTO `t_article_category` VALUES (11001001, NULL, '未分类', '', 0, 'system', '2021-02-25 00:00:01', 'system', '2021-02-25 00:00:01', 1);

DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `title` varchar(100) NOT NULL COMMENT '文章标题',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '文章类型',
  `category_id` bigint(20) NOT NULL DEFAULT '11001001' COMMENT '文章分类ID',
  `author_id` varchar(32) NOT NULL COMMENT '作者ID',
  `content` longtext NOT NULL COMMENT '文章内容',
  `format` varchar(40) NOT NULL DEFAULT '' COMMENT '文章格式',
  `origin` varchar(200) NOT NULL DEFAULT '' COMMENT '文章来源',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态：0 草稿，1 发布',
  `release_time` datetime DEFAULT NULL COMMENT '发布时间',
  `creator_id` varchar(32) NOT NULL COMMENT '创建者ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater_id` varchar(32) NOT NULL COMMENT '更新者ID',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `alive_flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`id`),
  KEY `idx_type` (`type`) USING BTREE,
  KEY `idx_category_id` (`category_id`) USING BTREE,
  KEY `idx_author_id` (`author_id`) USING BTREE,
  KEY `idx_format` (`format`) USING BTREE,
  KEY `idx_status` (`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=110000100001 DEFAULT CHARSET=utf8mb4 COMMENT='文章表';












