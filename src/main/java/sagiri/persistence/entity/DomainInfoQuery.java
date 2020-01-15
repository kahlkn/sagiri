package sagiri.persistence.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 域名信息的查询条件对象.
 * @author Kahle
 * @date 2019-11-08T09:48:18.613+0800
 */
@Data
public class DomainInfoQuery implements Serializable {

    /**
     * 主键
     */
    private Long id;
    /**
     * 域名
     */
    private String domainName;
    /**
     * 域名状态
     */
    private String domainStatuses;
    /**
     * 域名服务器
     */
    private String nameServers;
    /**
     * 域名登记处ID
     */
    private String registryDomainId;
    /**
     * 域名登记处注册时间
     */
    private Date registryRegistrationTime;
    /**
     * 域名登记处过期时间
     */
    private Date registryExpirationTime;
    /**
     * 域名登记处更新时间
     */
    private Date registryUpdateTime;
    /**
     * 中间商、代理商
     */
    private String reseller;
    /**
     * 域名注册商的名称
     */
    private String registrar;
    /**
     * 域名注册商的 URL 地址
     */
    private String registrarUrl;
    /**
     * 域名注册商的 Whois 服务器
     */
    private String registrarWhoisServer;
    /**
     * 域名注册商的滥用联系邮箱
     */
    private String registrarAbuseContactEmail;
    /**
     * 域名注册商的滥用联系手机
     */
    private String registrarAbuseContactPhone;
    /**
     * 域名注册商的 IANA 的 ID
     */
    private String registrarIanaId;
    /**
     * 域名注册商的注册到期日期
     */
    private Date registrarRegistrationExpirationDate;
    /**
     * 域名注册者
     */
    private String registrant;
    /**
     * 域名注册者的 ID
     */
    private String registrantId;
    /**
     * 域名注册者的组织
     */
    private String registrantOrganization;
    /**
     * 域名注册者的国家
     */
    private String registrantCountry;
    /**
     * 域名注册者的区域
     */
    private String registrantRegion;
    /**
     * 域名注册者的城市
     */
    private String registrantCity;
    /**
     * 域名注册者的联系邮箱
     */
    private String registrantContactEmail;
    /**
     * 域名注册者的联系号码
     */
    private String registrantContactPhone;
    /**
     * DNS 安全扩展
     */
    private String dnsSec;
    /**
     * Whois 信息最后更新时间
     */
    private Date whoisLastUpdateTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 原始数据
     */
    private String rawData;
    /**
     * 域名的业务状态：0 默认
     */
    private Integer status;

}
