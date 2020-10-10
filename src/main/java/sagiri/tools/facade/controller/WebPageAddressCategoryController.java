package sagiri.tools.facade.controller;

import sagiri.tools.service.WebPageAddressCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * WebPageAddressCategoryController.
 * @author Kahle
 * @date 2020-10-10T18:22:02.946+0800
 */
@Slf4j
@Controller
public class WebPageAddressCategoryController {

    @Autowired
    private WebPageAddressCategoryService webPageAddressCategoryService;

    /*@ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public Result<Object> hello(@RequestBody WebPageAddressCategoryVO webPageAddressCategoryVO) {

        return new Result<Object>("Hello, World! ");
    }*/

}
