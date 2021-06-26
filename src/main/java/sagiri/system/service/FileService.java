package sagiri.system.service;

import artoria.storage.StorageObject;
import org.springframework.web.multipart.MultipartFile;
import sagiri.system.service.dto.FileDTO;
import sagiri.system.service.dto.FileInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface FileService {

    List<FileDTO> list(String currentPath, String selected);

    /**
     * 保存文件
     */
    FileInfo saveFile(String folder, Date time, String originalName, String contentType, InputStream in, Map<String, Object> m);

    /**
     * 保存文件
     */
    FileInfo saveFile(String folderName, MultipartFile file, Map<String, Object> metadata);

    /**
     * 保存文件
     */
    FileInfo saveFile(String folderName, String url, Map<String, Object> metadata);

    /**
     * 保存文件
     */
    List<FileInfo> saveFiles(String folderName, List<MultipartFile> files);

    /**
     * 读取文件
     */
    StorageObject readFile(String address);

    /**
     * 读取文件
     */
    void readFile(String address, HttpServletResponse response);

}
