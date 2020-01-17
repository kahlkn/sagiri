

DROP TABLE IF EXISTS `t_domain_info`;
CREATE TABLE `t_domain_info` (
  `id` bigint(13) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `domain_name` varchar(150) NOT NULL COMMENT '域名',
  `domain_statuses` varchar(500) DEFAULT '' COMMENT '域名状态',
  `name_servers` varchar(600) DEFAULT '' COMMENT '域名服务器',
  `registry_domain_id` varchar(50) DEFAULT '' COMMENT '域名登记处ID',
  `registry_registration_time` datetime DEFAULT NULL COMMENT '域名登记处注册时间',
  `registry_expiration_time` datetime DEFAULT NULL COMMENT '域名登记处过期时间',
  `registry_update_time` datetime DEFAULT NULL COMMENT '域名登记处更新时间',
  `reseller` varchar(60) DEFAULT '' COMMENT '中间商、代理商',
  `registrar` varchar(60) DEFAULT '' COMMENT '域名注册商的名称',
  `registrar_url` varchar(100) DEFAULT '' COMMENT '域名注册商的 URL 地址',
  `registrar_whois_server` varchar(100) DEFAULT '' COMMENT '域名注册商的 Whois 服务器',
  `registrar_abuse_contact_email` varchar(80) DEFAULT '' COMMENT '域名注册商的滥用联系邮箱',
  `registrar_abuse_contact_phone` varchar(20) DEFAULT '' COMMENT '域名注册商的滥用联系手机',
  `registrar_iana_id` varchar(50) DEFAULT '' COMMENT '域名注册商的 IANA 的 ID',
  `registrar_registration_expiration_date` datetime DEFAULT NULL COMMENT '域名注册商的注册到期日期',
  `registrant` varchar(60) DEFAULT '' COMMENT '域名注册者',
  `registrant_id` varchar(50) DEFAULT '' COMMENT '域名注册者的 ID',
  `registrant_organization` varchar(60) DEFAULT '' COMMENT '域名注册者的组织',
  `registrant_country` varchar(60) DEFAULT '' COMMENT '域名注册者的国家',
  `registrant_region` varchar(60) DEFAULT '' COMMENT '域名注册者的区域',
  `registrant_city` varchar(60) DEFAULT '' COMMENT '域名注册者的城市',
  `registrant_contact_email` varchar(80) DEFAULT '' COMMENT '域名注册者的联系邮箱',
  `registrant_contact_phone` varchar(20) DEFAULT '' COMMENT '域名注册者的联系号码',
  `dns_sec` varchar(50) DEFAULT '' COMMENT 'DNS 安全扩展',
  `whois_last_update_time` datetime DEFAULT NULL COMMENT 'Whois 信息最后更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `raw_data` text DEFAULT '' COMMENT '原始数据',
  `status` tinyint(4) DEFAULT '0' COMMENT '域名的业务状态：0 默认',
  `create_user` varchar(32) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(32) NOT NULL COMMENT '修改者',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `alive_flag` tinyint(4) DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_domain_name` (`domain_name`) USING BTREE,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='域名信息表';




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
  `variables_json` text COMMENT '事件变量的JSON',
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
  `client_Id` varchar(32) DEFAULT '' COMMENT '客户端ID',
  `level` int(8) DEFAULT '0' COMMENT '事件级别',
  `client_app_id` varchar(50) DEFAULT '' COMMENT '客户端的应用ID',
  `client_name` varchar(50) DEFAULT '' COMMENT '客户端名称',
  `client_user_agent` varchar(500) DEFAULT '' COMMENT '客户端用户代理',
  `client_net_address` varchar(200) DEFAULT '' COMMENT '客户端的网络地址',
  `server_id` varchar(50) DEFAULT '' COMMENT '服务器ID',
  `server_app_id` varchar(50) DEFAULT '' COMMENT '服务器的应用名称',
  `interface_id` varchar(120) DEFAULT '' COMMENT '接口标识',
  `request_referer` varchar(200) DEFAULT '' COMMENT '请求的来源',
  `request_method` varchar(20) DEFAULT '' COMMENT '请求的方法',
  `request_address` varchar(200) DEFAULT '' COMMENT '请求的地址',
  `request_input` text COMMENT '请求时的输入信息',
  `response_output` text COMMENT '响应时的输出信息',
  `error_message` text COMMENT '错误信息',
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
  KEY `idx_client_Id` (`client_Id`) USING BTREE,
  KEY `idx_level` (`level`) USING BTREE,
  KEY `idx_client_app_id` (`client_app_id`) USING BTREE,
  KEY `idx_client_user_agent` (`client_user_agent`) USING BTREE,
  KEY `idx_client_net_address` (`client_net_address`) USING BTREE,
  KEY `idx_server_id` (`server_id`) USING BTREE,
  KEY `idx_server_app_id` (`server_app_id`) USING BTREE,
  KEY `idx_request_path` (`interface_id`) USING BTREE,
  KEY `idx_process_time` (`process_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='跟踪事件记录表';






