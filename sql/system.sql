

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




CREATE TABLE `t_identifier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `name` varchar(50) NOT NULL COMMENT 'ID生成的名称',
  `value` bigint(20) NOT NULL COMMENT 'ID生成的值',
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='ID生成表';







CREATE TABLE `t_event_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '事件记录ID',
  `event_name` varchar(50) NOT NULL COMMENT '事件名称',
  `event_type` varchar(20) NOT NULL DEFAULT '' COMMENT '事件类型',
  `event_time` datetime NOT NULL COMMENT '事件时间',
  `distinct_id` varchar(32) NOT NULL DEFAULT '' COMMENT '独特的ID',
  `anonymous_id` varchar(32) NOT NULL DEFAULT '' COMMENT '匿名的ID',
  `client_id` varchar(40) NOT NULL DEFAULT '' COMMENT '客户端ID',
  `client_app_id` varchar(40) NOT NULL DEFAULT '' COMMENT '客户端的应用ID',
  `client_device_id` varchar(40) NOT NULL DEFAULT '' COMMENT '客户端的设备ID',
  `client_user_agent` varchar(500) NOT NULL DEFAULT '' COMMENT '客户端的用户代理',
  `client_net_address` varchar(60) NOT NULL DEFAULT '' COMMENT '客户端的网络地址',
  `interface_id` varchar(100) NOT NULL DEFAULT '' COMMENT '被访问的接口的ID',
  `server_id` varchar(40) NOT NULL DEFAULT '' COMMENT '服务器的ID',
  `server_app_id` varchar(40) NOT NULL DEFAULT '' COMMENT '服务器的应用ID',
  `properties_json` text COMMENT '属性JSON',
  `creator_id` varchar(32) DEFAULT 'system' COMMENT '创建者ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater_id` varchar(32) DEFAULT 'system' COMMENT '更新者ID',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `alive_flag` tinyint(4) DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`id`),
  KEY `idx_event_name` (`event_name`) USING BTREE,
  KEY `idx_event_type` (`event_type`) USING BTREE,
  KEY `idx_event_time` (`event_time`) USING BTREE,
  KEY `idx_distinct_id` (`distinct_id`) USING BTREE,
  KEY `idx_anonymous_id` (`anonymous_id`) USING BTREE,
  KEY `idx_client_app_id` (`client_app_id`) USING BTREE,
  KEY `idx_client_net_address` (`client_net_address`) USING BTREE,
  KEY `idx_interface_id` (`interface_id`) USING BTREE,
  KEY `idx_server_id` (`server_id`) USING BTREE,
  KEY `idx_server_app_id` (`server_app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='事件记录表';




/*CREATE TABLE `t_device_record` (
  `id` varchar(60) NOT NULL COMMENT 'ID',
  `name` varchar(50) DEFAULT '' COMMENT '名称',
  `type` varchar(30) DEFAULT '' COMMENT '类型',
  `brand_name` varchar(50) DEFAULT '' COMMENT '名称',
  `model` varchar(50) NOT NULL COMMENT '型号',
  `code_name` varchar(50) DEFAULT '' COMMENT '名称',
  `display_name` varchar(60) DEFAULT '' COMMENT '名称',
  `manufacturer` varchar(80) DEFAULT '' COMMENT '厂商',
  `description` varchar(200) DEFAULT '' COMMENT '描述',
  `udid` varchar(60) DEFAULT '' COMMENT 'UDID',
  `idfa` varchar(60) DEFAULT '' COMMENT 'IDFA',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  `creator_id` varchar(32) NOT NULL COMMENT '创建者ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater_id` varchar(32) NOT NULL COMMENT '更新者ID',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `alive_flag` tinyint(4) DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备表';*/



/*CREATE TABLE `t_client_record` (
  `id` int(13) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `device_id` varchar(60) NOT NULL COMMENT '设备',
  `app_id` varchar(40) NOT NULL COMMENT '应用',
  `app_name` varchar(50) DEFAULT '' COMMENT '名称',
  `app_type` varchar(20) DEFAULT '' COMMENT '类型',
  `app_version` varchar(40) DEFAULT '' COMMENT '版本',
  `system_name` varchar(50) NOT NULL COMMENT '系统名称',
  `system_version` varchar(30) DEFAULT '' COMMENT '系统版本',
  `system_bits` varchar(10) DEFAULT '' COMMENT '系统位数',
  `user_id` varchar(40) DEFAULT '' COMMENT '用户ID',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户名称',
  `description` varchar(200) DEFAULT '' COMMENT '描述',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  `creator_id` varchar(32) NOT NULL COMMENT '创建者ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater_id` varchar(32) NOT NULL COMMENT '更新者ID',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `alive_flag` tinyint(4) DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户端表';*/













