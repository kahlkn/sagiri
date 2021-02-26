package sagiri.content.facade.api;

import artoria.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sagiri.content.service.ArticleCategoryService;
import sagiri.content.service.dto.ArticleCategoryDTO;

/**
 * ArticleCategoryController.
 * @author Kahle
 * @date 2020-11-30T16:23:04.911+0800
 */
@Slf4j
@Controller
public class ArticleCategoryController {

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @ResponseBody
    @RequestMapping(value = "/api/admin/article/category/add", method = RequestMethod.POST)
    public Result<Object> add(@RequestBody ArticleCategoryDTO articleCategoryDTO) {
        articleCategoryService.add(articleCategoryDTO);
        return new Result<>();
    }

    @ResponseBody
    @RequestMapping(value = "/api/admin/article/category/edit", method = RequestMethod.POST)
    public Result<Object> edit(@RequestBody ArticleCategoryDTO articleCategoryDTO) {
        articleCategoryService.edit(articleCategoryDTO);
        return new Result<>();
    }

    @ResponseBody
    @RequestMapping(value = "/api/admin/article/category/delete", method = RequestMethod.POST)
    public Result<Object> delete(@RequestBody ArticleCategoryDTO articleCategoryDTO) {
        articleCategoryService.delete(articleCategoryDTO.getId());
        return new Result<>();
    }

    @ResponseBody
    @RequestMapping(value = "/api/admin/article/category/detail", method = RequestMethod.POST)
    public Result<Object> detail(@RequestBody ArticleCategoryDTO articleCategoryDTO) {

        return new Result<Object>(articleCategoryService.findById(articleCategoryDTO.getId()));
    }

    @ResponseBody
    @RequestMapping(value = "/api/admin/article/category/select-list", method = RequestMethod.POST)
    public Result<Object> selectList(@RequestBody ArticleCategoryDTO articleCategoryDTO) {

        return new Result<Object>(articleCategoryService.selectList(articleCategoryDTO));
    }

}
