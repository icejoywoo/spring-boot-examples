package io.icejoywoo.springboot;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class AssemblyApplication {
    public static void main(String[] args) {
        /**
         * Common
         */
        MDC.put("starting_context", "[starting:common] ");
        log.info("student-service starting...");
        ConfigurableApplicationContext studentContext =
                new SpringApplicationBuilder(StudentServiceApplication.class).web(WebApplicationType.SERVLET).run(args);
        log.info("student-service [{}] isActive: {}", studentContext.getId(), studentContext.isActive());

        log.info("student-service starting...");
        ConfigurableApplicationContext helloContext =
                new SpringApplicationBuilder(HelloApplication.class).web(WebApplicationType.SERVLET).run(args);
        log.info("student-service [{}] isActive: {}", helloContext.getId(), helloContext.isActive());

        MDC.clear();
    }
}
