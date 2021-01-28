

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







CREATE TABLE `t_option` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '选项ID',
  `owner` varchar(40) DEFAULT '' COMMENT '拥有者',
  `name` varchar(50) NOT NULL COMMENT '选项名称',
  `value` text NOT NULL COMMENT '选项的值',
  `description` varchar(200) DEFAULT '' COMMENT '选项描述',
  `sort` tinyint(4) DEFAULT '0' COMMENT '排序',
  `create_user` varchar(32) NOT NULL DEFAULT 'system' COMMENT '创建者',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(32) NOT NULL DEFAULT 'system' COMMENT '修改者',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `alive_flag` tinyint(4) DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`id`),
  KEY `idx_owner` (`owner`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE,
  KEY `idx_sort` (`sort`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='选项表';




CREATE TABLE `t_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典的ID',
  `group` varchar(50) DEFAULT '' COMMENT '字典的组',
  `code` varchar(50) DEFAULT '' COMMENT '字典Code',
  `name` varchar(50) NOT NULL COMMENT '字典名称',
  `value` varchar(80) DEFAULT '' COMMENT '字典的值',
  `description` varchar(200) DEFAULT '' COMMENT '字典描述',
  `sort` int(8) DEFAULT '0' COMMENT '排序',
  `enabled` tinyint(4) DEFAULT '1' COMMENT '是否可用，1 可用，0 不可用',
  `create_user` varchar(32) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(32) NOT NULL COMMENT '修改者',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `alive_flag` tinyint(4) DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`id`),
  KEY `idx_group` (`group`) USING BTREE,
  KEY `idx_code` (`code`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE,
  KEY `idx_sort` (`sort`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据字典表';






