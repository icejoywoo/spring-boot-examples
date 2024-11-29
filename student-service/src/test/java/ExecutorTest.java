import java.util.concurrent.ThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Slf4j
public class ExecutorTest {

    @Test
    public void testQueueSize() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("test-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();

        for (int i = 0; i < 10; i++) {
            final int a = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                        log.info("i: {}", a);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        executor.setAwaitTerminationSeconds(100);
        executor.shutdown();
    }

}
