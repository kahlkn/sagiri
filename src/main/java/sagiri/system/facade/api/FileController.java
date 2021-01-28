package sagiri.system.facade.api;

import artoria.common.Result;
import artoria.exception.ExceptionUtils;
import artoria.file.FilenameUtils;
import artoria.io.IOUtils;
import artoria.spring.RequestContextUtils;
import artoria.storage.StorageObject;
import artoria.storage.StorageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sagiri.content.service.ArticleService;
import sagiri.system.common.FileUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class FileController {
    private static String bucket = "E:\\Test";

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/article/file/{year}/{month}/{day}/{name}", method = RequestMethod.GET)
    public void readFile(@PathVariable String year,
                         @PathVariable String month,
                         @PathVariable String day,
                         @PathVariable String name) {
        String fileAddress = String.format("/%s/%s/%s/%s", year, month, day, name);
        HttpServletResponse response = RequestContextUtils.getResponse();
        articleService.readFile(fileAddress, response);
    }

    @ResponseBody
    @RequestMapping(value = "/api/article/file/upload", method = RequestMethod.POST)
    public Result<Object> uploadFiles(@RequestParam("files") List<MultipartFile> files) {

        return new Result<Object>(articleService.uploadFiles(files));
    }

    @ResponseBody
    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    public Object upload(@RequestParam("files") MultipartFile[] files) {
        try {
            Date createTime = new Date();
            for (MultipartFile file : files) {
                String originalFilename = file.getOriginalFilename();
                String contentType = file.getContentType();
                long size = file.getSize();
                System.out.println(originalFilename + " >> " + size + " >> " + contentType);
                Map<String, Object> metadata = new LinkedHashMap<>();
                metadata.put("originalFilename", originalFilename);
                metadata.put("content-type", contentType);
                FileUtils.save(file.getBytes(), metadata, createTime, FilenameUtils.getExtension(originalFilename));
            }
            return new Result<>("123");
        }
        catch (IOException e) {
            throw ExceptionUtils.wrap(e);
        }
    }

    @RequestMapping(value = "/file/{year}/{middle}/{name}", method = RequestMethod.GET)
    public void file(@PathVariable String year, @PathVariable String middle, @PathVariable String name) {
        String key = year + "/" + middle + "/" + name;
        StorageObject storageObject = StorageUtils.getObject(bucket, key);
        InputStream objectContent = storageObject.getObjectContent();
        Map<String, Object> metadata = storageObject.getMetadata();
        String contentType = (String) metadata.get("content-type");
        HttpServletResponse response = RequestContextUtils.getResponse();
        response.setContentType(contentType);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            IOUtils.copyLarge(objectContent, outputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
