

DROP TABLE IF EXISTS `t_article_category`;
CREATE TABLE `t_article_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
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
) ENGINE=InnoDB AUTO_INCREMENT=10001001 DEFAULT CHARSET=utf8mb4 COMMENT='文章分类表';
INSERT INTO `t_article_category` VALUES (10001001, NULL, '未分类', '', 0, 'system', '2021-02-25 00:00:01', 'system', '2021-02-25 00:00:01', 1);


DROP TABLE IF EXISTS `t_article_tag`;
CREATE TABLE `t_article_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `name` varchar(50) NOT NULL COMMENT '标签名称',
  `slug` varchar(100) NOT NULL DEFAULT '' COMMENT '自定义访问路径',
  `description` varchar(200) NOT NULL DEFAULT '' COMMENT '标签描述',
  `sort` int(6) NOT NULL DEFAULT '0' COMMENT '排序',
  `creator_id` varchar(32) NOT NULL COMMENT '创建者ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater_id` varchar(32) NOT NULL COMMENT '更新者ID',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `alive_flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001001 DEFAULT CHARSET=utf8mb4 COMMENT='文章标签表';


DROP TABLE IF EXISTS `t_article_relation`;
CREATE TABLE `t_article_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关系ID',
  `article_id` bigint(20) NOT NULL COMMENT '文章ID',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '关系类型',
  `target_id` bigint(20) NOT NULL COMMENT '目标ID',
  `creator_id` varchar(32) NOT NULL COMMENT '创建者ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater_id` varchar(32) NOT NULL COMMENT '更新者ID',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `alive_flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章关系表';


-- https://en.wikipedia.org/wiki/Clean_URL#Slug
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `title` varchar(100) NOT NULL COMMENT '文章标题',
  `slug` varchar(100) NOT NULL DEFAULT '' COMMENT '自定义访问路径',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '文章类型',
  `author_id` varchar(32) NOT NULL COMMENT '作者ID',
  `thumbnail` varchar(150) NOT NULL DEFAULT '' COMMENT '缩略图',
  `format` varchar(40) NOT NULL DEFAULT '' COMMENT '文章格式',
  `content` longtext NOT NULL COMMENT '文章内容',
  `origin` varchar(200) NOT NULL DEFAULT '' COMMENT '文章来源',
  `release_time` datetime DEFAULT NULL COMMENT '发布时间',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态：0 草稿，1 发布',
  `creator_id` varchar(32) NOT NULL COMMENT '创建者ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater_id` varchar(32) NOT NULL COMMENT '更新者ID',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `alive_flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`id`),
  KEY `idx_type` (`type`) USING BTREE,
  KEY `idx_author_id` (`author_id`) USING BTREE,
  KEY `idx_format` (`format`) USING BTREE,
  KEY `idx_status` (`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100000100001 DEFAULT CHARSET=utf8mb4 COMMENT='文章表';


/*
分类：categories
标签：tags
`allow_feed` tinyint(4) NOT NULL DEFAULT '1' COMMENT '允许Feed：0 不允许，1 允许',
`allow_ping` tinyint(4) NOT NULL DEFAULT '1' COMMENT '允许Ping：0 不允许，1 允许',
`allow_comment` tinyint(4) NOT NULL DEFAULT '1' COMMENT '允许评论：0 不允许，1 允许',
*/













