

DROP TABLE IF EXISTS `t_domain_info`;
CREATE TABLE `t_domain_info` (
  `domain_name` varchar(150) NOT NULL,
  `domain_statuses` varchar(300) DEFAULT '',
  `name_servers` varchar(500) DEFAULT '',
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
  PRIMARY KEY (`domain_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;





