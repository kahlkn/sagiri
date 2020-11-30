package sagiri.core.persistence.entity;

import lombok.Data;

import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;

/**
 * The entity of the table "t_article".
 * @author Kahle
 * @date 2020-11-30T16:17:49.778+0800
 */
@Data
public class Article implements Serializable {

    /* (Start) This will be covered, please do not modify. */
    /**
     * 文章ID
     */
    private Long id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 文章类型
     */
    private Integer type;
    /**
     * 文章分类
     */
    private String category;
    /**
     * 作者
     */
    private String author;
    /**
     * 状态：0 草稿，1 发布
     */
    private Integer status;
    /**
     * 发布时间
     */
    private Date releaseTime;
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
    /* Generated by artoria-extend in 2020-11-30T16:17:50.390+0800. */

}
