package io.icejoywoo.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;
import org.springframework.context.annotation.PropertySource;

// https://www.cnblogs.com/changxy-codest/p/18431056
@SpringBootApplication
@Slf4j
@PropertySource(value = {"classpath:application.properties"})
@ComponentScan(nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class, basePackages="io.icejoywoo.*",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = {
                "com.xxx.providerxxx.*",
                "com.xxx.servicedxxx.*"
        })}
)
public class MultipleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleServiceApplication.class, args);
    }

}
