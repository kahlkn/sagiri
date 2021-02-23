package sagiri.system.facade.api;

import artoria.common.Result;
import artoria.spring.RequestContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sagiri.system.service.FileService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/file/{directory}/{year}/{month}/{day}/{name}", method = RequestMethod.GET)
    public void readFile(@PathVariable String directory,
                         @PathVariable String year,
                         @PathVariable String month,
                         @PathVariable String day,
                         @PathVariable String name,
                         HttpServletResponse response) {
        String address = String.format("/file/%s/%s/%s/%s/%s", directory, year, month, day, name);
//        HttpServletResponse response = RequestContextUtils.getResponse();
        fileService.readFile(address, response);
    }

    @ResponseBody
    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    public Result<Object> uploadFiles(@RequestParam String directory,
                                      @RequestParam("files") List<MultipartFile> files) {

        return new Result<Object>(fileService.saveFiles(directory, files));
    }

}
