

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





