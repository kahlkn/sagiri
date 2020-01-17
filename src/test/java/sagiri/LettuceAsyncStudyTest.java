package sagiri;

import artoria.common.Constants;
import artoria.net.NetUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.net.InetAddress;

@Slf4j
public class LettuceAsyncStudyTest {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(LettuceAsyncStudy.class.getSimpleName())
                .output("F:\\lettuceAsync-Throughput.log").forks(1).build();
        new Runner(options).run();
    }

    @Test
        public void test1() {
        log.info("{}", Constants.HOST_NAME);
        InetAddress localHost = NetUtils.getLocalHost();
        log.info("{}", Constants.HOST_NAME);
    }

}
