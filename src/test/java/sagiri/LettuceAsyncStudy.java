//package sagiri;
//
//import io.lettuce.core.RedisClient;
//import io.lettuce.core.RedisFuture;
//import io.lettuce.core.api.StatefulRedisConnection;
//import io.lettuce.core.api.async.RedisAsyncCommands;
//import org.openjdk.jmh.annotations.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.TimeUnit;
//
//@BenchmarkMode(Mode.All)
//@Warmup(iterations = 1)
//@Threads(100)
//@State(Scope.Benchmark)
//@Measurement(iterations = 10, time = 600, timeUnit = TimeUnit.MILLISECONDS)
//@OutputTimeUnit(TimeUnit.MILLISECONDS)
//public class LettuceAsyncStudy {
//
//    private static final int LOOP = 1;
//    private StatefulRedisConnection<String, String> connection;
//    @Setup
//    public void setup() {
//        RedisClient client = RedisClient.create("redis://localhost");
//        connection = client.connect();
//    }
//    @Benchmark
//    public void get() throws ExecutionException, InterruptedException {
//        RedisAsyncCommands<String, String> commands = connection.async();
//        List<RedisFuture<String>> redisFutureList = new ArrayList<>();
//        for (int i = 0; i < LOOP; ++i) {
//            RedisFuture<String> future = commands.get("hello");
//            redisFutureList.add(future);
//            future.get();
//        }
//        for (RedisFuture<String> stringRedisFuture : redisFutureList) {
//            stringRedisFuture.get();
//        }
//    }
//
//}
