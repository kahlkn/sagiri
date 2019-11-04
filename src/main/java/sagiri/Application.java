package sagiri;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startup class.
 * @author Kahle
 */
@SpringBootApplication
public class Application {
    private static Logger log = LoggerFactory.getLogger(Application.class);

    /**
     * Program entry.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("Sagiri start success! ");
    }

}
