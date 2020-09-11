package sagiri.tool.service.dto;

import lombok.Data;

/**
 * 随机字符串输入 DTO 对象.
 * @author Kahle
 */
@Data
public class RandomStringInputDTO {
    private Boolean upperCase;
    private Boolean lowerCase;
    private Boolean number;
    private String  specialty;
    private String  candidate;
    private Integer length;

}
