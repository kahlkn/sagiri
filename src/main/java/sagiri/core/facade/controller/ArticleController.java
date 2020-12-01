package sagiri.core.facade.controller;

import artoria.common.Result;
import artoria.exception.ExceptionUtils;
import artoria.file.FilenameUtils;
import artoria.io.IOUtils;
import artoria.spring.RequestContextUtils;
import artoria.storage.StorageObject;
import artoria.storage.StorageUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sagiri.core.common.FileUtils;
import sagiri.core.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sagiri.core.service.dto.ArticleDTO;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ArticleController.
 * @author Kahle
 * @date 2020-11-30T16:17:49.778+0800
 */
@Slf4j
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/article/index", method = RequestMethod.GET)
    public String index(@RequestParam String str) {

        return "/admin/articles";
    }

    @ResponseBody
    @RequestMapping(value = "/article/add", method = RequestMethod.POST)
    public Result<Object> addArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.addArticle(articleDTO);
        return new Result<Object>(articleDTO.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/article/edit", method = RequestMethod.POST)
    public Result<Object> editArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.editArticle(articleDTO);
        return new Result<Object>(articleDTO.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/article/file/upload", method = RequestMethod.POST)
    public Result<Object> uploadFiles(@RequestParam("files") List<MultipartFile> files) {

        return new Result<Object>(articleService.uploadFiles(files));
    }

    @RequestMapping(value = "/article/file/{year}/{month}/{day}/{name}", method = RequestMethod.GET)
    public void readFile(@PathVariable String year,
                         @PathVariable String month,
                         @PathVariable String day,
                         @PathVariable String name) {
        String fileAddress = String.format("/%s/%s/%s/%s", year, month, day, name);
        HttpServletResponse response = RequestContextUtils.getResponse();
        articleService.readFile(fileAddress, response);
    }

}
