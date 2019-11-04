package sagiri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sagiri.service.HelloService;

/**
 * Hello controller.
 * @author Kahle
 */
@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    /**
     * Do say hello.
     */
    @ResponseBody
    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public Object sayHello(@RequestParam(value = "name", required = false) String name) {

        return helloService.sayHello(name);
    }

}
