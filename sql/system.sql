

DROP TABLE IF EXISTS `t_system_user`;
CREATE TABLE `t_system_user` (
  `uid` varchar(32) NOT NULL COMMENT '用户ID',
  `number_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户的数字ID',
  `username` varchar(40) NOT NULL COMMENT '用户名',
  `password` varchar(200) NOT NULL COMMENT '密码',
  `avatar` varchar(300) DEFAULT '' COMMENT '头像',
  `real_name` varchar(40) DEFAULT '' COMMENT '真实姓名',
  `phone_number` varchar(20) DEFAULT '' COMMENT '手机号',
  `introduce` varchar(300) DEFAULT '' COMMENT '个人介绍',
  `status` tinyint(4) DEFAULT '0' COMMENT '用户状态',
  `register_type` tinyint(4) DEFAULT '0' COMMENT '注册类型',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  `enabled` tinyint(4) DEFAULT '1' COMMENT '是否启用：1 已启用，0 未启用',
  `creator_id` varchar(32) NOT NULL COMMENT '创建者ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater_id` varchar(32) NOT NULL COMMENT '更新者ID',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `alive_flag` tinyint(4) DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`number_id`),
  KEY `idx_username` (`username`) USING BTREE,
  KEY `idx_real_name` (`real_name`) USING BTREE,
  KEY `idx_phone_number` (`phone_number`) USING BTREE,
  KEY `idx_status` (`status`) USING BTREE,
  KEY `idx_register_time` (`register_time`) USING BTREE,
  KEY `idx_enabled` (`enabled`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';




DROP TABLE IF EXISTS `t_option`;
CREATE TABLE `t_option` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '选项ID',
  `owner` varchar(50) DEFAULT '' COMMENT '拥有者标识',
  `name` varchar(80) NOT NULL COMMENT '选项名称',
  `value` text NOT NULL COMMENT '选项的值',
  `creator_id` varchar(32) NOT NULL DEFAULT 'system' COMMENT '创建者ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater_id` varchar(32) NOT NULL DEFAULT 'system' COMMENT '更新者ID',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `alive_flag` tinyint(4) DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`id`),
  KEY `idx_owner` (`owner`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE,
  KEY `idx_sort` (`sort`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='选项表';

--   `description` varchar(200) DEFAULT '' COMMENT '选项描述',
--   `sort` tinyint(4) DEFAULT '0' COMMENT '排序',




DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典的ID',
  `group` varchar(50) DEFAULT '' COMMENT '字典的组',
  `code` varchar(50) DEFAULT '' COMMENT '字典Code',
  `name` varchar(50) NOT NULL COMMENT '字典名称',
  `value` varchar(50) DEFAULT '' COMMENT '字典的值',
  `description` varchar(200) DEFAULT '' COMMENT '字典描述',
  `sort` int(8) DEFAULT '0' COMMENT '排序',
  `enabled` tinyint(4) DEFAULT '1' COMMENT '是否启用：1 已启用，0 未启用',
  `creator_id` varchar(32) NOT NULL COMMENT '创建者ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater_id` varchar(32) NOT NULL COMMENT '更新者ID',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `alive_flag` tinyint(4) DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`id`),
  KEY `idx_group` (`group`) USING BTREE,
  KEY `idx_code` (`code`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE,
  KEY `idx_sort` (`sort`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据字典表';









