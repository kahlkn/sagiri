package sagiri.core.common;

import java.util.HashMap;
import java.util.Map;

import static artoria.common.Constants.FOUR;

public class SysUtils {

    public static Map<String, Object> app() {
        Map<String, Object> system = new HashMap<>(FOUR);
        system.put("cdnUrl", "/admin");
        system.put("version", "v1.0");
        return system;
    }

}
