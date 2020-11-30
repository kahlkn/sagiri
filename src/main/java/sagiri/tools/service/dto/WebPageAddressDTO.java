package sagiri.tools.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;

/**
 * The dto of the table "t_web_page_address".
 * @author Kahle
 * @date 2020-10-10T18:45:13.188+0800
 */
@Data
public class WebPageAddressDTO implements Serializable {

    /* (Start) This will be covered, please do not modify. */
    /**
     * 网页地址ID
     */
    private Long id;
    /**
     * 地址名称
     */
    private String name;
    /**
     * 网页地址
     */
    private String url;
    /**
     * 网站图标
     */
    private String icon;
    /**
     * 地址描述
     */
    private String description;
    /**
     * 创建者ID
     */
    private String creatorId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新者ID
     */
    private String updaterId;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 数据状态
     */
    private Integer aliveFlag;
    /* (End) This will be covered, please do not modify. */
    /* Generated by artoria-extend in 2020-10-10T18:45:13.612+0800. */

}
