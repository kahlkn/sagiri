

DROP TABLE IF EXISTS `t_domain_info`;
CREATE TABLE `t_domain_info` (
  `domain_name` varchar(150) NOT NULL,
  `domain_length` int(8) NOT NULL,
  `domain_suffix` varchar(30) NOT NULL,
  `domain_statuses` varchar(500) DEFAULT '',
  `name_servers` varchar(600) DEFAULT '',
  `registry_domain_id` varchar(50) DEFAULT '',
  `registry_registration_time` datetime DEFAULT NULL,
  `registry_expiration_time` datetime DEFAULT NULL,
  `registry_update_time` datetime DEFAULT NULL,
  `reseller` varchar(60) DEFAULT '',
  `registrar` varchar(60) DEFAULT '',
  `registrar_url` varchar(100) DEFAULT '',
  `registrar_whois_server` varchar(100) DEFAULT '',
  `registrar_abuse_contact_email` varchar(80) DEFAULT '',
  `registrar_abuse_contact_phone` varchar(20) DEFAULT '',
  `registrar_iana_id` varchar(50) DEFAULT '',
  `registrar_registration_expiration_date` datetime DEFAULT NULL,
  `registrant` varchar(60) DEFAULT '',
  `registrant_id` varchar(50) DEFAULT '',
  `registrant_organization` varchar(60) DEFAULT '',
  `registrant_country` varchar(60) DEFAULT '',
  `registrant_region` varchar(60) DEFAULT '',
  `registrant_city` varchar(60) DEFAULT '',
  `registrant_contact_email` varchar(80) DEFAULT '',
  `registrant_contact_phone` varchar(20) DEFAULT '',
  `dns_sec` varchar(50) DEFAULT '',
  `whois_last_update_time` datetime DEFAULT NULL,
  `raw_data` text DEFAULT '',
  `remark` varchar(500) DEFAULT '',
  `status` tinyint(4) DEFAULT '0',
  `create_user` varchar(32) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_user` varchar(32) NOT NULL,
  `update_date` datetime NOT NULL,
  `alive_flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`domain_name`),
  KEY `idx_domain_length` (`domain_length`) USING BTREE,
  KEY `idx_domain_suffix` (`domain_suffix`) USING BTREE,
  KEY `idx_registry_registration_time` (`registry_registration_time`) USING BTREE,
  KEY `idx_registry_expiration_time` (`registry_expiration_time`) USING BTREE,
  KEY `idx_registry_update_time` (`registry_update_time`) USING BTREE,
  KEY `idx_registrar` (`registrar`) USING BTREE,
  KEY `idx_registrar_iana_id` (`registrar_iana_id`) USING BTREE,
  KEY `idx_registrant` (`registrant`) USING BTREE,
  KEY `idx_registrant_organization` (`registrant_organization`) USING BTREE,
  KEY `idx_whois_last_update_time` (`whois_last_update_time`) USING BTREE,
  KEY `idx_status` (`status`) USING BTREE,
  KEY `idx_update_date` (`update_date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




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
  `create_user` varchar(32) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_user` varchar(32) NOT NULL,
  `update_date` datetime NOT NULL,
  `alive_flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `idx_username` (`username`) USING HASH,
  KEY `idx_real_name` (`real_name`) USING BTREE,
  KEY `idx_phone_number` (`phone_number`) USING BTREE,
  KEY `idx_status` (`status`) USING BTREE,
  KEY `idx_role_code` (`role_code`) USING BTREE,
  KEY `idx_register_date` (`register_date`) USING BTREE,
  KEY `idx_enabled` (`enabled`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';





CREATE TABLE `t_event_record` (
  `id` varchar(32) NOT NULL COMMENT '事件ID',
  `visitor_id` varchar(32) DEFAULT '' COMMENT '访问者ID',
  `user_id` varchar(32) DEFAULT '' COMMENT '用户ID',
  `time` datetime DEFAULT NULL COMMENT '事件时间',
  `client_Id` varchar(32) DEFAULT '' COMMENT '客户端ID',
  `name` varchar(50) DEFAULT '' COMMENT '事件名称',
  `code` varchar(50) DEFAULT '' COMMENT '事件Code',
  `type` varchar(20) DEFAULT '' COMMENT '事件类型',
  `level` int(8) DEFAULT '0' COMMENT '事件级别',
  `description` varchar(500) DEFAULT '' COMMENT '事件描述',
  `variables` text COMMENT '事件变量',
  `provider` varchar(80) DEFAULT '' COMMENT '事件的提供者',
  `create_user` varchar(32) DEFAULT 'system' COMMENT '创建者',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT 'system' COMMENT '修改者',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `alive_flag` tinyint(4) DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`id`),
  KEY `idx_visitor_id` (`visitor_id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_time` (`time`) USING BTREE,
  KEY `idx_client_Id` (`client_Id`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE,
  KEY `idx_code` (`code`) USING BTREE,
  KEY `idx_type` (`type`) USING BTREE,
  KEY `idx_level` (`level`) USING BTREE,
  KEY `idx_provider` (`provider`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='事件记录表';








CREATE TABLE `t_trace_event_record` (
  `id` varchar(32) NOT NULL COMMENT '事件ID',
  `visitor_id` varchar(32) DEFAULT '' COMMENT '访问者ID',
  `user_id` varchar(32) DEFAULT '' COMMENT '用户ID',
  `time` datetime DEFAULT NULL COMMENT '事件时间',
  `name` varchar(50) DEFAULT '' COMMENT '事件名称',
  `code` varchar(50) DEFAULT '' COMMENT '事件Code',
  `level` int(8) DEFAULT '0' COMMENT '事件级别',
  `client_name` varchar(50) DEFAULT '' COMMENT '客户端名称',
  `client_user_agent` varchar(500) DEFAULT '' COMMENT '客户端用户代理',
  `client_device_id` varchar(32) DEFAULT '' COMMENT '客户端设备ID',
  `client_system_id` varchar(32) DEFAULT '' COMMENT '客户端操作系统ID',
  `client_browser_id` varchar(32) DEFAULT '' COMMENT '客户端浏览器ID',
  `client_app_id` varchar(50) DEFAULT '' COMMENT '客户端的应用ID',
  `client_net_address` varchar(200) DEFAULT '' COMMENT '客户端的网络地址',
  `client_real_net_address` varchar(100) DEFAULT '' COMMENT '客户端的真实网络地址',
  `server_name` varchar(50) DEFAULT '' COMMENT '服务器名称',
  `server_app_name` varchar(50) DEFAULT '' COMMENT '服务器的应用名称',
  `request_referer` varchar(200) DEFAULT '' COMMENT '请求的来源',
  `request_method` varchar(20) DEFAULT '' COMMENT '请求的方法',
  `request_path` varchar(120) DEFAULT '' COMMENT '请求的路径',
  `request_address` varchar(200) DEFAULT '' COMMENT '请求的地址',
  `request_input` text COMMENT '请求时的输入信息',
  `response_status` varchar(40) DEFAULT '' COMMENT '响应的状态',
  `response_output` text COMMENT '响应时的输出信息',
  `response_error_message` text COMMENT '响应的错误信息',
  `process_time` int(11) DEFAULT '-1' COMMENT '处理时间',
  `create_user` varchar(32) DEFAULT 'system' COMMENT '创建者',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT 'system' COMMENT '修改者',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `alive_flag` tinyint(4) DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`id`),
  KEY `idx_visitor_id` (`visitor_id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_time` (`time`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE,
  KEY `idx_code` (`code`) USING BTREE,
  KEY `idx_level` (`level`) USING BTREE,
  KEY `idx_client_user_agent` (`client_user_agent`) USING BTREE,
  KEY `idx_client_device_id` (`client_device_id`) USING BTREE,
  KEY `idx_client_system_id` (`client_system_id`) USING BTREE,
  KEY `idx_client_browser_id` (`client_browser_id`) USING BTREE,
  KEY `idx_client_app_id` (`client_app_id`) USING BTREE,
  KEY `idx_client_real_net_address` (`client_real_net_address`) USING BTREE,
  KEY `idx_server_name` (`server_name`) USING BTREE,
  KEY `idx_server_app_name` (`server_app_name`) USING BTREE,
  KEY `idx_request_path` (`request_path`) USING BTREE,
  KEY `idx_request_address` (`request_address`) USING BTREE,
  KEY `idx_response_status` (`response_status`) USING BTREE,
  KEY `idx_process_time` (`process_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='跟踪事件记录表';






