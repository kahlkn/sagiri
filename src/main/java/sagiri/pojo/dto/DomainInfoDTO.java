package sagiri.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * The dto of the table "t_domain_info".
 * @author Kahle
 * @date 2019-11-08T09:48:18.613+0800
 */
@Data
public class DomainInfoDTO implements Serializable {

    /* (Start) This will be covered, please do not modify. */
    /**
     *
     */
    private String domainName;
    /**
     *
     */
    private String domainStatuses;
    /**
     *
     */
    private String nameServers;
    /**
     *
     */
    private String registryDomainId;
    /**
     *
     */
    private Date registryRegistrationTime;
    /**
     *
     */
    private Date registryExpirationTime;
    /**
     *
     */
    private Date registryUpdateTime;
    /**
     *
     */
    private String reseller;
    /**
     *
     */
    private String registrar;
    /**
     *
     */
    private String registrarUrl;
    /**
     *
     */
    private String registrarWhoisServer;
    /**
     *
     */
    private String registrarAbuseContactEmail;
    /**
     *
     */
    private String registrarAbuseContactPhone;
    /**
     *
     */
    private String registrarIanaId;
    /**
     *
     */
    private Date registrarRegistrationExpirationDate;
    /**
     *
     */
    private String registrant;
    /**
     *
     */
    private String registrantId;
    /**
     *
     */
    private String registrantOrganization;
    /**
     *
     */
    private String registrantCountry;
    /**
     *
     */
    private String registrantRegion;
    /**
     *
     */
    private String registrantCity;
    /**
     *
     */
    private String registrantContactEmail;
    /**
     *
     */
    private String registrantContactPhone;
    /**
     *
     */
    private String dnsSec;
    /**
     *
     */
    private Date whoisLastUpdateTime;
    /**
     *
     */
    private String rawData;
    /**
     *
     */
    private String remark;
    /**
     *
     */
    private Integer status;
    /**
     *
     */
    private String createUser;
    /**
     *
     */
    private Date createDate;
    /**
     *
     */
    private String updateUser;
    /**
     *
     */
    private Date updateDate;
    /**
     *
     */
    private Integer aliveFlag;
    /* (End) This will be covered, please do not modify. */
    /* Generated by artoria-extend in 2019-11-08T09:48:22.387+0800. */

}
