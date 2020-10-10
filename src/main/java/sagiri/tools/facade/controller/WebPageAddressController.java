package sagiri.tools.facade.controller;

import sagiri.tools.service.WebPageAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * WebPageAddressController.
 * @author Kahle
 * @date 2020-10-10T18:21:40.507+0800
 */
@Slf4j
@Controller
public class WebPageAddressController {

    @Autowired
    private WebPageAddressService webPageAddressService;

    /*@ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public Result<Object> hello(@RequestBody WebPageAddressVO webPageAddressVO) {

        return new Result<Object>("Hello, World! ");
    }*/

}
