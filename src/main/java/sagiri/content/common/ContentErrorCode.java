package sagiri.content.common;

import artoria.common.ErrorCode;

/**
 * 内容相关错误码（100[业务模块]10000[具体的错误]）.
 * @author Kahle
 */
public enum ContentErrorCode implements ErrorCode {

    /**
     * 文章相关错误码
     */
    E12110021("12110021", "文章ID不能为空！"),
    E12110022("12110022", "文章标题不能为空！"),
    E12110023("12110023", "文章类型不能为空！"),
    E12110024("12110024", "文章分类不能为空！"),
    E12110025("12110025", "文章作者不能为空！"),
    E12110026("12110026", "文章内容不能为空！"),
    E12110041("12110041", "新增文章失败！"),
    E12110042("12110042", "编辑文章失败！"),
    E12110043("12110043", "删除文章失败！"),
    /* ---------------- */
    E12110121("12110121", "文章分类ID不能为空！"),
    E12110122("12110122", "文章分类名称不能为空！"),
    E12110123("12110123", "文章分类描述不能为空！"),
    E12110141("12110141", "新增文章分类失败！"),
    E12110142("12110142", "编辑文章分类失败！"),
    E12110143("12110143", "删除文章分类失败！"),
    /* ---------------- */
    E12110221("12110221", "文章关系类型不能为空！"),
    E12110222("12110222", "文章关系的目标ID不能为空！"),
    E12110241("12110241", "批量新增文章关系失败！"),
    E12110242("12110242", "批量编辑文章关系失败！"),
    E12110243("12110243", "批量删除文章关系失败！"),

    ;

    ContentErrorCode(String code, String description) {
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
