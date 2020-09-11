package sagiri.core.facade.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class HomeController {

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {

        return "Hello, Sagiri! ";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {

        return "redirect:/";
    }

}
