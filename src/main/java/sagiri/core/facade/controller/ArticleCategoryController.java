package sagiri.core.facade.controller;

import sagiri.core.service.ArticleCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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

    /*@ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public Result<Object> hello(@RequestBody ArticleCategoryVO articleCategoryVO) {

        return new Result<Object>("Hello, World! ");
    }*/

}
