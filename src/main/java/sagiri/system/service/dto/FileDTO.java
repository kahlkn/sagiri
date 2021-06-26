package sagiri.system.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileDTO implements Serializable {
    /**
     * 文件名称
     */
    private String name;
    /**
     * 文件类型：0 文件夹，1 文件
     */
    private Integer type;
    /**
     * 缩略图
     */
    private String thumbnail;
    /**
     * 文件地址
     */
    private String address;
    /**
     * 完整的文件地址
     */
    private String fullAddress;

}
