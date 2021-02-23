package sagiri.system.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileInfo implements Serializable {
    /**
     * 文件名称
     */
    private String name;
    /**
     * 文件地址
     */
    private String address;

    public FileInfo() {
    }

    public FileInfo(String name, String address) {
        this.name = name;
        this.address = address;
    }

}
