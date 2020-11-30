package sagiri.core.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UploadedFileDTO implements Serializable {

    /**
     * 文件名称
     */
    private String name;
    /**
     * 文件地址
     */
    private String address;

}
