package sagiri.tools.facade.vo;

import lombok.Data;

import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;

/**
 * The vo of the table "t_web_page_address_category".
 * @author Kahle
 * @date 2020-10-10T18:22:02.946+0800
 */
@Data
public class WebPageAddressCategoryVO implements Serializable {

    /* (Start) This will be covered, please do not modify. */
    /**
     * 网页地址分类ID
     */
    private Long id;
    /**
     * 父分类ID
     */
    private Long parentId;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 分类描述
     */
    private String description;
    /**
     * 创建人ID
     */
    private String creatorId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人ID
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
    /* Generated by artoria-extend in 2020-10-10T18:22:03.415+0800. */

}
