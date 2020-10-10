package sagiri.tools.facade.controller;

import artoria.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sagiri.tools.service.DomainInfoService;

/**
 * DomainInfoController.
 * @author Kahle
 * @date 2019-11-08T09:48:18.613+0800
 */
@Slf4j
@Controller
public class DomainInfoController {

    @Autowired
    private DomainInfoService domainInfoService;

    @ResponseBody
    @RequestMapping(value = "/domain/batch", method = RequestMethod.GET)
    public Result<Object> batch(String domainSuffix, Integer length, Boolean existNumber, Long sleepTime) {
        if (sleepTime == null) { sleepTime = 500L; }
        domainInfoService.batchDomainChecking(length, existNumber, sleepTime, domainSuffix.split(","));
        return new Result<>();
    }

}
