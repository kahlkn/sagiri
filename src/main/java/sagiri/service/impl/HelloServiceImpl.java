package sagiri.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sagiri.service.HelloService;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello service implementation.
 * @author Kahle
 */
@Service
public class HelloServiceImpl implements HelloService {
    private static Logger log = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public Object sayHello(String name) {
        log.info("Call the \"sayHello\" method and pass in the argument \"{}\". ", name);
        if (name == null || name.trim().length() <= 0) {
            name = "Sagiri";
        }
        Map<String, Object> data = new HashMap<>(4);
        data.put("content", "Hello, " + name + "!");
        data.put("time", System.currentTimeMillis());
        return data;
    }

}
