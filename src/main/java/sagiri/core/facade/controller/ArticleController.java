package sagiri.core.facade.controller;

import artoria.common.PageResult;
import artoria.common.Result;
import artoria.exception.BusinessException;
import artoria.spring.RequestContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sagiri.core.common.SysUtils;
import sagiri.core.service.ArticleService;
import sagiri.core.service.dto.ArticleDTO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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

    /**
     * 文章的列表页面
     */
    @RequestMapping(value = "/admin/articles", method = RequestMethod.GET)
    public String articleListPage(Model model) {
        model.addAttribute("app", SysUtils.app());
        return "/admin/articles";
    }

    @RequestMapping(value = "/admin/article/add", method = RequestMethod.GET)
    public String articleNew(Model model) {
        model.addAttribute("app", SysUtils.app());
        return "/admin/article/new";
    }

    @RequestMapping(value = "/admin/article/edit", method = RequestMethod.GET)
    public String articleEdit(Model model) {
        model.addAttribute("app", SysUtils.app());
        return "/admin/article/edit";
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

    @ResponseBody
    @RequestMapping(value = "/api/article/add", method = RequestMethod.POST)
    public Result<Object> addArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.addArticle(articleDTO);
        return new Result<Object>(articleDTO.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/api/article/edit", method = RequestMethod.POST)
    public Result<Object> editArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.editArticle(articleDTO);
        return new Result<Object>(articleDTO.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/api/article/delete", method = RequestMethod.POST)
    public Result<Object> deleteArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.deleteArticle(articleDTO);
        return new Result<Object>(articleDTO.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/api/article/list", method = RequestMethod.POST)
    public PageResult<List<ArticleDTO>> queryArticleList(@RequestBody ArticleDTO articleDTO) {

        return articleService.queryArticleList(articleDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/api/article/file/upload", method = RequestMethod.POST)
    public Result<Object> uploadFiles(@RequestParam("files") List<MultipartFile> files) {

        return new Result<Object>(articleService.uploadFiles(files));
    }

}
