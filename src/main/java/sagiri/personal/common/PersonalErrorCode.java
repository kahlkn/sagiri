package sagiri.personal.common;

import artoria.common.ErrorCode;

/**
 * 个人的相关错误码（100[业务模块]10000[具体的错误]）.
 * @author Kahle
 */
public enum PersonalErrorCode implements ErrorCode {

    /**
     * 网址相关错误码
     */
    E12210021("12210021", "网址ID不能为空！"),
    E12210022("12210022", "网址分类不能为空！"),
    E12210023("12210023", "网页的名称不能为空！"),
    E12210024("12210024", "网页的地址不能为空！"),
    E12210025("12210025", "网页的图标不能为空！"),
    E12210026("12210026", "网页的描述不能为空！"),
    E12210041("12210041", "新增网址失败！"),
    E12210042("12210042", "编辑网址失败！"),
    E12210043("12210043", "删除网址失败！"),
    /* ---------------- */
    E12210121("12210121", "网址分类ID不能为空！"),
    E12210122("12210122", "网址分类名称不能为空！"),
    E12210123("12210123", "网址分类描述不能为空！"),
    E12210141("12210141", "新增网址分类失败！"),
    E12210142("12210142", "编辑网址分类失败！"),
    E12210143("12210143", "删除网址分类失败！"),

    ;

    PersonalErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    private String description;
    private String code;

    @Override
    public String getCode() {

        return code;
    }

    @Override
    public String getDescription() {

        return description;
    }

}
