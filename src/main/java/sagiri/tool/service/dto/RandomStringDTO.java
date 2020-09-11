package sagiri.tool.service.dto;

import lombok.Data;

/**
 * 随机字符串 DTO 对象.
 * @author Kahle
 */
@Data
public class RandomStringDTO {
    private Boolean upperCase;
    private Boolean lowerCase;
    private Boolean number;
    private String  specialty;
    private String  candidate;
    private Integer length;
    private String  result;

}
