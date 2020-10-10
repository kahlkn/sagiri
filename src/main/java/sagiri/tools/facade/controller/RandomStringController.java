package sagiri.tools.facade.controller;

import artoria.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sagiri.tools.service.dto.RandomStringInputDTO;
import sagiri.tools.service.RandomStringService;

/**
 * 随机字符串相关的 Controller.
 * @author Kahle
 * @date 2020-01-10T11:46:30.001+0800
 */
@Slf4j
@Controller
public class RandomStringController {

    @Autowired
    private RandomStringService randomStringService;

    @ResponseBody
    @RequestMapping(value = "/random/string/generate", method = RequestMethod.GET)
    public Result<Object> generate(RandomStringInputDTO randomStringInputDTO) {

        return new Result<Object>(randomStringService.generate(randomStringInputDTO));
    }

}
