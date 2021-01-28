package sagiri.content.facade.api;

import artoria.common.PageResult;
import artoria.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sagiri.content.service.ArticleService;
import sagiri.content.service.dto.ArticleDTO;

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

    @ResponseBody
    @RequestMapping(value = "/api/admin/article/add", method = RequestMethod.POST)
    public Result<Object> addArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.addArticle(articleDTO);
        return new Result<Object>(articleDTO.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/api/admin/article/edit", method = RequestMethod.POST)
    public Result<Object> editArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.editArticle(articleDTO);
        return new Result<Object>(articleDTO.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/api/admin/article/delete", method = RequestMethod.POST)
    public Result<Object> deleteArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.deleteArticle(articleDTO);
        return new Result<Object>(articleDTO.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/api/admin/article/list", method = RequestMethod.POST)
    public PageResult<List<ArticleDTO>> queryArticleList(@RequestBody ArticleDTO articleDTO) {

        return articleService.queryArticleList(articleDTO);
    }

}
