CREATE TABLE `t_domain_info` (
  `id` varchar(32) NOT NULL,
  `domainName` varchar(50) NOT NULL,
  `domainStatuses` varchar(50) NOT NULL,
  `nameServers` varchar(50) NOT NULL,
  `registryDomainId` varchar(50) NOT NULL,
  `registryRegistrationTime` varchar(50) NOT NULL,
  `domainName` varchar(50) NOT NULL,
  `domainName` varchar(50) NOT NULL,
  `domainName` varchar(50) NOT NULL,
  `domainName` varchar(50) NOT NULL,
  `domainName` varchar(50) NOT NULL,
  `domainName` varchar(50) NOT NULL,
  `domainName` varchar(50) NOT NULL,
  `domainName` varchar(50) NOT NULL,
  `domainName` varchar(50) NOT NULL,
  `domainName` varchar(50) NOT NULL,
  `domainName` varchar(50) NOT NULL,
  `domainName` varchar(50) NOT NULL,
  `domainName` varchar(50) NOT NULL,
  `domainName` varchar(50) NOT NULL,
  `domainName` varchar(50) NOT NULL,
  `domainName` varchar(50) NOT NULL,
  `business_type` tinyint(4) DEFAULT '0',
  `address` varchar(200) NOT NULL,
  `sort` tinyint(4) DEFAULT '0',
  `remark` varchar(200) DEFAULT '',
  `create_user` varchar(32) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(32) NOT NULL COMMENT '修改者',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `alive_flag` tinyint(4) DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




private String domainName;
    private String domainStatuses;
    private String nameServers;
    private String registryDomainId;
    private Date registryRegistrationTime;
    private Date registryExpirationTime;
    private Date registryUpdateTime;
    private String reseller;
    private String registrar;
    private String registrarUrl;
    private String registrarWhoisServer;
    private String registrarAbuseContactEmail;
    private String registrarAbuseContactPhone;
    private String registrarIanaId;
    private Date registrarRegistrationExpirationDate;
    private String registrant;
    private String registrantId;
    private String registrantOrganization;
    private String registrantCountry;
    private String registrantRegion;
    private String registrantCity;
    private String registrantContactEmail;
    private String registrantContactPhone;
    private String dnsSec;
    private Date whoisLastUpdateTime;
    private String remark;