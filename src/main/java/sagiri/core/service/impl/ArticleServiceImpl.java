package sagiri.core.service.impl;

import artoria.beans.BeanUtils;
import artoria.exception.ExceptionUtils;
import artoria.file.FilenameUtils;
import artoria.io.IOUtils;
import artoria.random.RandomUtils;
import artoria.spring.RequestContextUtils;
import artoria.storage.StorageObject;
import artoria.storage.StorageUtils;
import artoria.time.DateUtils;
import artoria.util.Assert;
import artoria.util.CloseUtils;
import artoria.util.CollectionUtils;
import artoria.common.Paging;
import artoria.util.PagingUtils;
import artoria.common.Input;
import artoria.common.PageResult;
import artoria.common.Result;
import artoria.exception.VerifyUtils;
import org.springframework.web.multipart.MultipartFile;
import sagiri.core.common.FileUtils;
import sagiri.core.persistence.entity.Article;
import sagiri.core.persistence.mapper.ArticleMapper;
import sagiri.core.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sagiri.core.service.dto.UploadedFileDTO;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

import static artoria.common.Constants.*;
import static artoria.common.InternalErrorCode.INTERNAL_SERVER_BUSY;
import static artoria.common.InternalErrorCode.PARAMETER_IS_REQUIRED;

/**
 * ArticleServiceImpl.
 * @author Kahle
 * @date 2020-11-30T16:17:49.778+0800
 */
@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {
//    private static String bucket = ".\\article";
    private static String bucket = "E:\\sagiri\\article";

    @Autowired
    private ArticleMapper articleMapper;

    /*private long newId(Integer articleType) {
        // 80  01  1606719596   1001
        // 80  固定头
        // 01  业务类型  大类    01 表示资源相关    02 表示文章相关
        // 1606719596   unix  时间戳
        // 1001   自定义序列
        //    100  毫秒值  ，  1  文章类型
        if (articleType == null) { articleType = ZERO; }
        String valueOf = String.valueOf(articleType);
        VerifyUtils.isTrue(valueOf.length() == ONE, INTERNAL_SERVER_BUSY);
        String str = "8001" + DateUtils.getTimestamp() + articleType;
        return Long.parseLong(str);
    }*/

    private long newFileId() {
        //   1 02011301752 34  00
        // 1  业务类型  大类    1 内容相关
        // 020   年份      1130   月日     1752  时分
        // 34   秒     00  随机或者序列
        // 1 020 1130 18 06 54 83
        String str = "1" + DateUtils.format("yyyyMMddHHmmss").substring(1, 14) + RandomUtils.nextInt(100);
        return Long.parseLong(str);
    }

    @Override
    public List<UploadedFileDTO> uploadFiles(List<MultipartFile> files) {
        try {
            Date createTime = new Date();
            List<UploadedFileDTO> fileList = new ArrayList<>();
            for (MultipartFile file : files) {
                String originalFilename = file.getOriginalFilename();
                String contentType = file.getContentType();
                long size = file.getSize();
                System.out.println(originalFilename + " >> " + size + " >> " + contentType);
                Map<String, Object> metadata = new LinkedHashMap<>();
                metadata.put("originalFilename", originalFilename);
                metadata.put("content-type", contentType);


                String directoryName = DateUtils.format(createTime, "yyyy/MM/dd/");
                String fileName = String.valueOf(newFileId());
                String objectKey = directoryName + fileName + "." + FilenameUtils.getExtension(originalFilename);


                Assert.notBlank(objectKey, "Variable \"objectKey\" must not blank. ");
                StorageUtils.putObject(bucket, objectKey, file.getBytes(), metadata);


                UploadedFileDTO uploadedFileDTO = new UploadedFileDTO();
                uploadedFileDTO.setName(originalFilename);
                uploadedFileDTO.setAddress(objectKey);
                fileList.add(uploadedFileDTO);
            }
            return fileList;
        }
        catch (IOException e) {
            throw ExceptionUtils.wrap(e);
        }
    }

    @Override
    public void readFile(String fileAddress, HttpServletResponse response) {
        OutputStream outputStream = null;
        InputStream objectContent = null;
        try {
            StorageObject storageObject = StorageUtils.getObject(bucket, fileAddress);
            objectContent = storageObject.getObjectContent();
            Map<String, Object> metadata = storageObject.getMetadata();
            String contentType = (String) metadata.get("content-type");
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
