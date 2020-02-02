package sagiri.controller;

import artoria.codec.Base64Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 跳转相关的 Controller.
 * @author Kahle
 * @date 2020-01-10T11:46:30.001+0800
 */
@Slf4j
@Controller
public class RedirectController {

//    @RequestMapping(value = "/go", method = RequestMethod.GET)
//    public String redirectNothing() {
//
//        return "redirect:/";
//    }
//
//    @RequestMapping(value = "/go/{address}", method = RequestMethod.GET)
//    public String redirect(@PathVariable String address) {
//        byte[] bytes = Base64Utils.decodeFromString(address);
//        return "redirect:" + new String(bytes, UTF_8);
//    }

}
