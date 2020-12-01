package sagiri.core.common.enums;

public enum ArticleType {
    /**
     * 默认
     */
    DEFAULT(0, "默认"),
    /**
     * 我的
     */
    MINE(1, "我的"),
    /**
     * 笔记
     */
    NOTE(2, "笔记"),
    /**
     * 网上的
     */
    INTERNET(6, "网上的"),
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
