package sagiri.personal.facade.api;

import artoria.common.PageResult;
import artoria.common.Result;
import artoria.exception.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sagiri.personal.service.WebAddressService;
import sagiri.personal.service.dto.WebAddressDTO;

import java.io.IOException;
import java.util.List;

/**
 * WebAddressController.
 * @author Kahle
 * @date 2021-02-26T11:04:30.110+0800
 */
@Slf4j
@Controller
public class WebAddressController {

    @Autowired
    private WebAddressService webAddressService;

    @ResponseBody
    @RequestMapping(value = "/api/admin/web-address/add", method = RequestMethod.POST)
    public Result<Object> add(@RequestBody WebAddressDTO webAddressDTO) {
        webAddressService.add(webAddressDTO);
        return new Result<>();
    }

    @ResponseBody
    @RequestMapping(value = "/api/admin/web-address/edit", method = RequestMethod.POST)
    public Result<Object> edit(@RequestBody WebAddressDTO webAddressDTO) {
        webAddressService.edit(webAddressDTO);
        return new Result<>();
    }

    @ResponseBody
    @RequestMapping(value = "/api/admin/web-address/delete", method = RequestMethod.POST)
    public Result<Object> delete(@RequestBody WebAddressDTO webAddressDTO) {
        webAddressService.delete(webAddressDTO.getId());
        return new Result<>();
    }

    @ResponseBody
    @RequestMapping(value = "/api/admin/web-address/detail", method = RequestMethod.POST)
    public Result<Object> detail(@RequestBody WebAddressDTO webAddressDTO) {

        return new Result<Object>(webAddressService.findById(webAddressDTO.getId()));
    }

    @ResponseBody
    @RequestMapping(value = "/api/admin/web-address/paging-list", method = RequestMethod.POST)
    public PageResult<List<WebAddressDTO>> pagingList(@RequestBody WebAddressDTO webAddressDTO) {

        return webAddressService.pagingList(webAddressDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/api/admin/web-address/importBookmarks", method = RequestMethod.POST)
    public Result<Object> importBookmarks(@RequestParam("file") MultipartFile file) {
        try {
            byte[] fileBytes = file.getBytes();
            webAddressService.importBookmarks(new String(fileBytes));
            return new Result<Object>("Hello, World! ");
        }
        catch (IOException e) {
            throw ExceptionUtils.wrap(e);
        }
    }

}
