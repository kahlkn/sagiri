package sagiri.system.service.impl;

import artoria.exception.ExceptionUtils;
import artoria.file.FilenameUtils;
import artoria.identifier.LongIdentifierGenerator;
import artoria.io.IOUtils;
import artoria.net.HttpMethod;
import artoria.net.HttpRequest;
import artoria.net.HttpResponse;
import artoria.net.HttpUtils;
import artoria.storage.StorageObject;
import artoria.storage.StorageUtils;
import artoria.time.DateUtils;
import artoria.util.ArrayUtils;
import artoria.util.CloseUtils;
import artoria.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sagiri.system.service.FileService;
import sagiri.system.service.dto.FileDTO;
import sagiri.system.service.dto.FileInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

import static artoria.common.Constants.*;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Value("${sagiri.file.home-path}")
    private String homePath;
    @Autowired
    private LongIdentifierGenerator fileIdGenerator;

    @Override
    public List<FileDTO> list(String currentPath, String selected) {
        File file = new File(homePath, currentPath);
        if (StringUtils.isNotBlank(selected)) {
            try {
                file = new File(file, selected).getCanonicalFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(file);
        }
        File[] files = file.listFiles();
        List<FileDTO> list = new ArrayList<>();

        FileDTO fileDTO = new FileDTO();
        fileDTO.setName(".");
        fileDTO.setType(ZERO);
        list.add(fileDTO);
        fileDTO = new FileDTO();
        fileDTO.setName("..");
        fileDTO.setType(ZERO);
        list.add(fileDTO);
        if (ArrayUtils.isNotEmpty(files)) {
            for (File file1 : files) {
                fileDTO = new FileDTO();
                fileDTO.setName(file1.getName());
                fileDTO.setType(file1.isDirectory() ? ZERO : ONE);
                if (fileDTO.getType() == ONE) {
                    String string = file1.toString();
                    string = string.replaceAll("\\\\", "/");
                    String str = homePath.replaceAll("\\\\", "/");
                    string = string.replace(str, "");
                    fileDTO.setAddress(string);
                    fileDTO.setFullAddress("http://localhost:1210" + string);

                    String extension = FilenameUtils.getExtension(string);
                    if ("jpg".equalsIgnoreCase(extension) ||
                            "jpeg".equalsIgnoreCase(extension) ||
                            "png".equalsIgnoreCase(extension) ||
                            "gif".equalsIgnoreCase(extension)) {
                        fileDTO.setThumbnail(fileDTO.getAddress());
                    }
                }
                list.add(fileDTO);
            }
        }
        return list;
    }

    @Override
    public FileInfo saveFile(String folder, Date time, String originalName, String contentType, InputStream in, Map<String, Object> m) {
        // 处理 metadata
        if (m == null) { m = new LinkedHashMap<>(); }
        // Original file name.
        String key = "original-file-name";
        if (StringUtils.isNotBlank(originalName)&&!m.containsKey(key)) {
            m.put(key, originalName);
        }
        key = HttpHeaders.CONTENT_TYPE.toLowerCase();
        if (StringUtils.isNotBlank(contentType)&&!m.containsKey(key)) {
            m.put(key, contentType);
        }
        // 处理 objectKey
        String extension = FilenameUtils.getExtension(originalName);
        if (StringUtils.isBlank(extension)) { extension = EMPTY_STRING; }
        if (StringUtils.isBlank(folder)) { folder = "5"; }
        if (time == null) { time = new Date(); }
        while (folder.startsWith(SLASH)||folder.startsWith(BACKSLASH)) {
            folder = folder.substring(ONE);
        }
        while (folder.endsWith(SLASH)||folder.endsWith(BACKSLASH)) {
            folder = folder.substring(ZERO, folder.length() - ONE);
        }
        if (StringUtils.isBlank(folder)) { folder = "5"; }
        String timeStr = DateUtils.format(time, "/yyyy/MM/dd/");
        String fileStr = String.valueOf(fileIdGenerator.nextLongIdentifier());
        if (StringUtils.isNotBlank(extension) && !extension.startsWith(DOT)) {
            extension = DOT + extension;
        }
        String part = Integer.toHexString((fileStr + extension).hashCode()).substring(0, 2)+"/";
        String objectKey = String.format("/file/%s%s%s%s%s", folder, timeStr, part, fileStr, extension);
        // 保存
        StorageUtils.putObject(homePath, objectKey, in, m);
        return new FileInfo(originalName, objectKey);
    }

    @Override
    public FileInfo saveFile(String folderName, MultipartFile file, Map<String, Object> metadata) {
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();
        Date targetTime = new Date();

        long size = file.getSize();
        System.out.println(originalFilename + " >> " + size + " >> " + contentType);

        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            return saveFile(folderName, targetTime, originalFilename, contentType, inputStream, metadata);
        }
        catch (IOException e) {
            throw ExceptionUtils.wrap(e);
        }
        finally {
            CloseUtils.closeQuietly(inputStream);
        }
    }

    @Override
    public FileInfo saveFile(String folderName, String url, Map<String, Object> metadata) {
        try {
            HttpRequest httpRequest = new HttpRequest();
            httpRequest.setMethod(HttpMethod.GET);
            httpRequest.setUrl(url);
            HttpResponse httpResponse = HttpUtils.getHttpClient().execute(httpRequest);
            byte[] body = httpResponse.getBody();
            String contentType = httpResponse.getHeader(HttpHeaders.CONTENT_TYPE);
            String name = FilenameUtils.getName(url);
            Date targetTime = new Date();
            if (metadata == null) { metadata = new LinkedHashMap<>(); }
            if (!metadata.containsKey("origin")) { metadata.put("origin", url); }
            ByteArrayInputStream inputStream = new ByteArrayInputStream(body);
            return saveFile(folderName, targetTime, name, contentType, inputStream, metadata);
        }
        catch (IOException e) {
            throw ExceptionUtils.wrap(e);
        }
    }

    @Override
    public List<FileInfo> saveFiles(String folderName, List<MultipartFile> files) {
        List<FileInfo> fileInfoList = new ArrayList<>();
        for (MultipartFile file : files) {
            FileInfo fileInfo = saveFile(folderName, file, null);
            fileInfoList.add(fileInfo);
        }
        return fileInfoList;
    }

    @Override
    public StorageObject readFile(String address) {

        return StorageUtils.getObject(homePath, address);
    }

    @Override
    public void readFile(String address, HttpServletResponse response) {
        OutputStream outputStream = null;
        InputStream objectContent = null;
        try {
            StorageObject storageObject = StorageUtils.getObject(homePath, address);
            objectContent = storageObject.getObjectContent();
            Map<String, Object> metadata = storageObject.getMetadata();
            String contentType = (String) metadata.get(HttpHeaders.CONTENT_TYPE);
            if (StringUtils.isBlank(contentType)) {
                contentType = "application/octet-stream";
            }
            response.setContentType(contentType);
            outputStream = response.getOutputStream();
            IOUtils.copyLarge(objectContent, outputStream);
        }
        catch (IOException e) {
            throw ExceptionUtils.wrap(e);
        }
        finally {
            CloseUtils.closeQuietly(objectContent);
            CloseUtils.closeQuietly(outputStream);
        }
    }

}
