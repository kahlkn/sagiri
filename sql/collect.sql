
CREATE TABLE `t_event_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '事件记录ID',
  `event_name` varchar(50) NOT NULL COMMENT '事件名称',
  `event_type` varchar(20) NOT NULL DEFAULT '' COMMENT '事件类型',
  `event_time` datetime NOT NULL COMMENT '事件时间',
  `distinct_id` varchar(32) NOT NULL DEFAULT '' COMMENT '明显的ID',
  `anonymous_id` varchar(32) NOT NULL DEFAULT '' COMMENT '匿名的ID',
  `client_id` varchar(40) NOT NULL DEFAULT '' COMMENT '客户端的ID',
  `client_app_id` varchar(40) NOT NULL DEFAULT '' COMMENT '客户端的应用的ID',
  `client_device_id` varchar(40) NOT NULL DEFAULT '' COMMENT '客户端的设备的ID',
  `client_user_agent` varchar(500) NOT NULL DEFAULT '' COMMENT '客户端的用户代理',
  `client_net_address` varchar(60) NOT NULL DEFAULT '' COMMENT '客户端的网络地址',
  `interface_id` varchar(100) NOT NULL DEFAULT '' COMMENT '被访问的接口的ID',
  `server_id` varchar(40) NOT NULL DEFAULT '' COMMENT '服务器的ID',
  `server_app_id` varchar(40) NOT NULL DEFAULT '' COMMENT '服务器的应用的ID',
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






