package sagiri.personal.service.dto;

import artoria.common.Paging;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;

/**
 * The dto of the table "t_web_address".
 * @author Kahle
 * @date 2021-02-26T11:04:30.110+0800
 */
@Data
public class WebAddressDTO implements Serializable {

    /* (Start) This will be covered, please do not modify. */
    /**
     * 网址ID
     */
    private Long id;
    /**
     * 网址分类ID
     */
    private Long categoryId;
    /**
     * 网页名称
     */
    private String name;
    /**
     * 网页地址
     */
    private String address;
    /**
     * 网页图标
     */
    private String icon;
    /**
     * 网页描述
     */
    private String description;
    /**
     * 排序
     */
    private Integer sort;
    /* (End) This will be covered, please do not modify. */
    /* Generated by artoria-extend in 2021-02-26T11:04:30.664+0800. */

    /**
     * 分页参数
     */
    @JsonUnwrapped
    private Paging paging;

}
