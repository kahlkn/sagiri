package sagiri.personal.facade.api;

import artoria.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sagiri.personal.service.WebAddressCategoryService;
import sagiri.personal.service.dto.WebAddressCategoryDTO;

/**
 * WebAddressCategoryController.
 * @author Kahle
 * @date 2021-02-26T11:04:49.714+0800
 */
@Slf4j
@Controller
public class WebAddressCategoryController {

    @Autowired
    private WebAddressCategoryService webAddressCategoryService;

    @ResponseBody
    @RequestMapping(value = "/api/admin/web-address/category/add", method = RequestMethod.POST)
    public Result<Object> add(@RequestBody WebAddressCategoryDTO categoryDTO) {
        webAddressCategoryService.add(categoryDTO);
        return new Result<>();
    }

    @ResponseBody
    @RequestMapping(value = "/api/admin/web-address/category/edit", method = RequestMethod.POST)
    public Result<Object> edit(@RequestBody WebAddressCategoryDTO categoryDTO) {
        webAddressCategoryService.edit(categoryDTO);
        return new Result<>();
    }

    @ResponseBody
    @RequestMapping(value = "/api/admin/web-address/category/delete", method = RequestMethod.POST)
    public Result<Object> delete(@RequestBody WebAddressCategoryDTO categoryDTO) {
        webAddressCategoryService.delete(categoryDTO.getId());
        return new Result<>();
    }

    @ResponseBody
    @RequestMapping(value = "/api/admin/web-address/category/detail", method = RequestMethod.POST)
    public Result<Object> detail(@RequestBody WebAddressCategoryDTO categoryDTO) {

        return new Result<Object>(webAddressCategoryService.findById(categoryDTO.getId()));
    }

    @ResponseBody
    @RequestMapping(value = "/api/admin/web-address/category/select-list", method = RequestMethod.POST)
    public Result<Object> selectList(@RequestBody WebAddressCategoryDTO categoryDTO) {

        return new Result<Object>(webAddressCategoryService.selectList(categoryDTO));
    }

}
