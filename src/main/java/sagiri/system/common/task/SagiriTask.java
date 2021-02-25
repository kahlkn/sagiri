package sagiri.system.common.task;

import artoria.exception.ExceptionUtils;
import artoria.file.FilenameUtils;
import artoria.util.CloseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sagiri.system.common.util.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Component
public class SagiriTask {
    private static String workspace = "E:\\Test\\workspace";

//    @Scheduled(cron = "0 0/1 * * * ?")
    public void autoUpdateAvgPrice() {
        File workspace = new File(SagiriTask.workspace);
        File[] files = workspace.listFiles();
        log.info(">> workspace find {}", files != null ? files.length : null);
        if (files == null) { return; }
        for (File file : files) {
            if (file.isDirectory()) { continue; }
            Date createTime = new Date(FileUtils.createTime(file));

            String originalFilename = file.getName();
            String contentType = FileUtils.getContentType(file);
            System.out.println(originalFilename + " >> " + contentType);
            Map<String, Object> metadata = new LinkedHashMap<>();
            metadata.put("originalFilename", originalFilename);
            metadata.put("content-type", contentType);
            metadata.put("last-modified-time", String.valueOf(createTime.getTime()));

            InputStream inputStream = null;
            try {
                log.info(">> handle {}", file);
                inputStream = new FileInputStream(file);
                FileUtils.save(inputStream, metadata, createTime, FilenameUtils.getExtension(originalFilename));
                boolean delete = file.delete();
                log.info("{} >> {}", file, delete);
            }
            catch (IOException e) {
                throw ExceptionUtils.wrap(e);
            }
            finally {
                CloseUtils.closeQuietly(inputStream);
            }
        }
    }

}
