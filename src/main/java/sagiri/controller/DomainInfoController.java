package sagiri.controller;

import artoria.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sagiri.service.DomainInfoService;

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
    @RequestMapping(value = "/domain/initialize", method = RequestMethod.POST)
    public Result<Object> initialize(String domainSuffix, Integer length, Boolean existNumber) {
        domainInfoService.initialize(domainSuffix, length, existNumber);
        return new Result<>();
    }

    @ResponseBody
    @RequestMapping(value = "/domain/update", method = RequestMethod.POST)
    public Result<Object> updateDomain(String domainName) {
        if ("all".equalsIgnoreCase(domainName)) {
            domainInfoService.updateAllDomain();
        }
        else {
            domainInfoService.updateDomain(domainName);
        }
        return new Result<>();
    }

}
