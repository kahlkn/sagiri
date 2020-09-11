
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






