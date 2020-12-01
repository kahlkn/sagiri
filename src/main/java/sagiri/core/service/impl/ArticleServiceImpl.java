package sagiri.core.service.impl;

import artoria.beans.BeanUtils;
import artoria.common.*;
import artoria.exception.ExceptionUtils;
import artoria.file.FilenameUtils;
import artoria.identifier.IdentifierUtils;
import artoria.io.IOUtils;
import artoria.spring.RequestContextUtils;
import artoria.storage.StorageObject;
import artoria.storage.StorageUtils;
import artoria.time.DateUtils;
import artoria.util.*;
import artoria.exception.VerifyUtils;
import artoria.validate.ValidatorUtils;
import org.springframework.web.multipart.MultipartFile;
import sagiri.core.common.FileUtils;
import sagiri.core.persistence.entity.Article;
import sagiri.core.persistence.mapper.ArticleMapper;
import sagiri.core.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sagiri.core.service.dto.ArticleDTO;
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

    @Override
    public void addArticle(ArticleDTO articleDTO) {
        VerifyUtils.notNull(articleDTO, PARAMETER_IS_REQUIRED);
        VerifyUtils.notBlank(articleDTO.getTitle(), PARAMETER_IS_REQUIRED);
        VerifyUtils.notNull(articleDTO.getType(), PARAMETER_IS_REQUIRED);
        VerifyUtils.notBlank(articleDTO.getCategory(), PARAMETER_IS_REQUIRED);
        VerifyUtils.notBlank(articleDTO.getAuthorId(), PARAMETER_IS_REQUIRED);
        VerifyUtils.notBlank(articleDTO.getAuthorName(), PARAMETER_IS_REQUIRED);
        VerifyUtils.notBlank(articleDTO.getContent(), PARAMETER_IS_REQUIRED);
        if (StringUtils.isBlank(articleDTO.getId())) {
            articleDTO.setId(IdentifierUtils.nextStringIdentifier());
        }
        if (articleDTO.getStatus() == null) {
            articleDTO.setStatus(ZERO);
        }
        //
        Date nowDate = new Date();
        //
        Article article = BeanUtils.beanToBean(articleDTO, Article.class);
        if (article.getStatus() == ONE) {
            article.setReleaseTime(nowDate);
        }
        article.setCreatorId(SYSTEM);
        article.setCreateTime(nowDate);
        article.setUpdaterId(SYSTEM);
        article.setUpdateTime(nowDate);
        article.setAliveFlag(ONE);
        //
        int effect = articleMapper.insertSelective(article);
        VerifyUtils.isTrue(effect == ONE, INTERNAL_SERVER_BUSY);
    }

    @Override
    public void editArticle(ArticleDTO articleDTO) {
        VerifyUtils.notNull(articleDTO, PARAMETER_IS_REQUIRED);
        VerifyUtils.notBlank(articleDTO.getId(), PARAMETER_IS_REQUIRED);
        //
        Date nowDate = new Date();
        //
        Article article = BeanUtils.beanToBean(articleDTO, Article.class);
        if (article.getStatus() == ONE) {
            article.setReleaseTime(nowDate);
        }
        article.setUpdaterId(SYSTEM);
        article.setUpdateTime(nowDate);
        //
        article.setNumberId(null);
        article.setType(null);
        int effect = articleMapper.updateByIdSelective(article);
        VerifyUtils.isTrue(effect == ONE, INTERNAL_SERVER_BUSY);
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
                String fileName = String.valueOf(IdentifierUtils.nextStringIdentifier());
                String objectKey = directoryName + fileName + "." + FilenameUtils.getExtension(originalFilename);

                Assert.notBlank(objectKey, "Variable \"objectKey\" must not blank. ");
                StorageUtils.putObject(bucket, objectKey, file.getBytes(), metadata);


                UploadedFileDTO uploadedFileDTO = new UploadedFileDTO();
                uploadedFileDTO.setName(originalFilename);
                uploadedFileDTO.setAddress("/article/file/" + objectKey);
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
