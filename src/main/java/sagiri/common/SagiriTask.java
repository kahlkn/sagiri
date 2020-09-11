package sagiri.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SagiriTask {

    @Scheduled(cron = "1 0 0 * * ?")
    public void autoUpdateAvgPrice() {
    }

}
