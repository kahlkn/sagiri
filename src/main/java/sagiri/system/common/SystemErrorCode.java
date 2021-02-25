package sagiri.system.common;

import artoria.common.ErrorCode;

/**
 * 系统相关错误码（100[业务模块]10000[具体的错误]）.
 * @author Kahle
 */
public enum SystemErrorCode implements ErrorCode {

    /**
     * 常用错误码
     */
    E10110001("10110001", "参数是必须的！"),
//    E10110002("10110002", "参数是必须的！"),

    ;

    SystemErrorCode(String code, String description) {
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
