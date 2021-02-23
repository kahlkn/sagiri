package sagiri.system.service.impl;

import artoria.exception.ExceptionUtils;
import artoria.file.FilenameUtils;
import artoria.identifier.IdentifierUtils;
import artoria.io.IOUtils;
import artoria.net.HttpMethod;
import artoria.net.HttpRequest;
import artoria.net.HttpResponse;
import artoria.net.HttpUtils;
import artoria.storage.StorageObject;
import artoria.storage.StorageUtils;
import artoria.time.DateUtils;
import artoria.util.CloseUtils;
import artoria.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sagiri.system.service.FileService;
import sagiri.system.service.dto.FileInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

import static artoria.common.Constants.DOT;
import static artoria.common.Constants.EMPTY_STRING;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Value("${sagiri.file.home-path}")
    private String homePath;

    private String createObjectKey(String folderName, Date targetTime, String extension) {
        if (StringUtils.isBlank(extension)) { extension = EMPTY_STRING; }
        if (StringUtils.isBlank(folderName)) { folderName = "main"; }
        if (targetTime == null) { targetTime = new Date(); }
//        if (folderName.startsWith("/")||folderName.endsWith("/")) {
//            folderName = folderName.replaceAll("/", "");
//        }
        String timeName = DateUtils.format(targetTime, "/yyyy/MM/dd/");
        String fileName = String.valueOf(IdentifierUtils.nextStringIdentifier());
        if (StringUtils.isNotBlank(extension) &&
                !extension.startsWith(DOT)) {
            extension = DOT + extension;
        }
        return "/file/" + folderName + timeName + fileName + extension;
    }

    @Override
    public FileInfo saveFile(String folder, Date time, String fileName, String contentType, InputStream in, Map<String, Object> m) {

        if (m == null) { m = new LinkedHashMap<>(); }
        // Original file name.
        String key = "original-file-name";
        if (StringUtils.isNotBlank(fileName)&&!m.containsKey(key)) {
            m.put(key, fileName);
        }
        key = "content-type";
        if (StringUtils.isNotBlank(contentType)&&!m.containsKey(key)) {
            m.put(key, contentType);
        }

        String extension = FilenameUtils.getExtension(fileName);
        String objectKey = createObjectKey(folder, time, extension);
        StorageUtils.putObject(homePath, objectKey, in, m);
        return new FileInfo(fileName, objectKey);
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
            Date targetTime = new Date();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(body);
            return saveFile(folderName, targetTime, new File(url).getName(), null, inputStream, metadata);
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
            String contentType = (String) metadata.get("content-type");
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
