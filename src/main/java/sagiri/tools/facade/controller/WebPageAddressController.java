package sagiri.tools.facade.controller;

import artoria.common.Result;
import artoria.exception.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sagiri.tools.service.WebPageAddressService;

import java.io.IOException;

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

    @ResponseBody
    @RequestMapping(value = "/importBookmarks", method = RequestMethod.POST)
    public Result<Object> importBookmarks(@RequestParam("file") MultipartFile file) {
        try {
            byte[] fileBytes = file.getBytes();
            webPageAddressService.importBookmarks(new String(fileBytes));
            return new Result<Object>("Hello, World! ");
        }
        catch (IOException e) {
            throw ExceptionUtils.wrap(e);
        }
    }

}
