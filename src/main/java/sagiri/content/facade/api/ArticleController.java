package sagiri.content.facade.api;

import artoria.common.PageResult;
import artoria.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public Result<Object> add(@RequestBody ArticleDTO articleDTO) {
        articleService.add(articleDTO);
        return new Result<Object>(articleDTO.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/api/admin/article/edit", method = RequestMethod.POST)
    public Result<Object> edit(@RequestBody ArticleDTO articleDTO) {
        articleService.edit(articleDTO);
        return new Result<>();
    }

    @ResponseBody
    @RequestMapping(value = "/api/admin/article/delete", method = RequestMethod.POST)
    public Result<Object> delete(@RequestBody ArticleDTO articleDTO) {
        articleService.delete(articleDTO.getId());
        return new Result<>();
    }

    @ResponseBody
    @RequestMapping(value = "/api/admin/article/detail", method = RequestMethod.POST)
    public Result<Object> detail(@RequestBody ArticleDTO articleDTO) {

        return new Result<Object>(articleService.detail(articleDTO.getId()));
    }

    @ResponseBody
    @RequestMapping(value = "/api/admin/article/paging-list", method = RequestMethod.POST)
    public PageResult<List<ArticleDTO>> pagingList(@RequestBody ArticleDTO articleDTO) {

        return articleService.pagingList(articleDTO);
    }

}
