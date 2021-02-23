package sagiri.content.common.enums;

public enum ArticleType {
    /**
     * 默认文章
     */
    DEFAULT( 0, "默认文章"),
    /**
     * 我的文章
     */
    MINE(    1, "我的文章"),
    /**
     * 笔记文章
     */
    NOTE(    2, "笔记文章"),
    /**
     * 网络文章
     */
    INTERNET(6, "网络文章"),
    ;

    ArticleType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    private Integer code;
    private String description;

    public Integer getCode() {

        return code;
    }

    public String getDescription() {

        return description;
    }

}
