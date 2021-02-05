package sagiri.collector.common;

import artoria.common.ErrorCode;

/**
 * 错误信息.
 * @author Kahle
 */
public enum CollectorErrorCode implements ErrorCode {

    /**
     * 选项相关错误码
     */
    COLLECTOR_021("COLLECTOR_021", "COLLECTOR_021. "),

    ;

    CollectorErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    private String code;
    private String description;

    @Override
    public String getCode() {

        return code;
    }

    @Override
    public String getDescription() {

        return description;
    }

}
