package sagiri.system.common.util;

import artoria.exception.ExceptionUtils;
import artoria.logging.Logger;
import artoria.logging.LoggerFactory;
import artoria.storage.StorageUtils;
import artoria.time.DateUtils;
import artoria.util.*;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.Map;

import static artoria.common.Constants.*;

public class FileUtils {
    private static Logger log = LoggerFactory.getLogger(FileUtils.class);
    private static String bucket = "E:\\Test";

    public static long createTime(File file) {
        if (ClassUtils.isPresent("java.nio.file.attribute.BasicFileAttributes"
                , ClassLoaderUtils.getDefaultClassLoader())) {
            BasicFileAttributes bAttributes;
            try {
                bAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                long lastModifiedTime = bAttributes.lastModifiedTime().toMillis();
                long creationTime = bAttributes.creationTime().toMillis();
                return lastModifiedTime < creationTime ? lastModifiedTime : creationTime;
            }
            catch (IOException e) {
                throw ExceptionUtils.wrap(e);
            }
        }
        else {
            return file.lastModified();
        }
    }

    public static String objectKey(Date createTime, String extension) {
        if (StringUtils.isNotBlank(extension) && !extension.startsWith(DOT)) {
            extension = DOT + extension;
        }
        for (int count = 0; count < TEN; count++) {
            String directoryName = DateUtils.format(createTime, "yyyy/MMdd/");
            String fileName = DateUtils.format(createTime, "HHmmssSSS").substring(ZERO, EIGHT);
            String sequence;
            for (int i = 0; i < EIGHTY; i++) {
                sequence = String.valueOf(RandomUtils.nextInt(89) + 10);
                String objectKey = directoryName + fileName + sequence + extension;
                boolean doesObjectExist = StorageUtils.doesObjectExist(bucket, objectKey);
                if (!doesObjectExist) {
                    log.info("objectKey >> {}", objectKey);
                    return objectKey;
                }
            }
            ThreadUtils.sleepQuietly(50);
        }
        return null;
    }

    public static void save(byte[] bytes, Map<String, Object> metadata, Date createTime, String extension) {
        String objectKey = objectKey(createTime, extension);
        Assert.notBlank(objectKey, "Variable \"objectKey\" must not blank. ");
        StorageUtils.putObject(bucket, objectKey, bytes, metadata);
    }

    public static void save(InputStream inputStream, Map<String, Object> metadata, Date createTime, String extension) {
        String objectKey = objectKey(createTime, extension);
        Assert.notBlank(objectKey, "Variable \"objectKey\" must not blank. ");
        StorageUtils.putObject(bucket, objectKey, inputStream, metadata);
    }

    private static MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap();

    public static String getContentType(File file) {
        //利用nio提供的类判断文件ContentType
        String contentType;
        try {
            contentType = Files.probeContentType(file.toPath());
        }
        catch (IOException e) {
            throw ExceptionUtils.wrap(e);
        }

        /*String fileName = file.getName();
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentType = fileNameMap.getContentTypeFor(fileName);*/

        //若失败则调用另一个方法进行判断
        if (contentType == null) {
            // 这个是 根据 文件的后缀名进行判断的
            contentType = mimetypesFileTypeMap.getContentType(file);
        }
        return contentType;
    }

    /*public void test3() throws Exception {
        File file = new File("E:\\test.png");
        long lastModified = file.lastModified();
        log.info(DateUtils.format(lastModified));


        BasicFileAttributes bAttributes = null;
        try {
            bAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String fileName = file.getName();
        // 修改时间
        long lastModifiedTime = bAttributes.lastModifiedTime().toMillis();
        long creationTime = bAttributes.creationTime().toMillis();
        long lastAccessTime = bAttributes.lastAccessTime().toMillis();
        log.info(DateUtils.format(lastModifiedTime));
        log.info(DateUtils.format(creationTime));
        log.info(DateUtils.format(lastAccessTime));
    }*/

}
