package sagiri.collect.facade.controller;

import sagiri.collect.service.EventRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * EventRecordController.
 * @author Kahle
 * @date 2020-09-15T10:55:27.628+0800
 */
@Slf4j
@Controller
public class EventRecordController {

    @Autowired
    private EventRecordService eventRecordService;

    /*@ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public Result<Object> hello(@RequestBody EventRecordVO eventRecordVO) {

        return new Result<Object>("Hello, World! ");
    }*/

}
