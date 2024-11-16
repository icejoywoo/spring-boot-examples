package io.icejoywoo.springboot;

public class HelloServiceRunner extends MultipleServiceRunner{
    public HelloServiceRunner() {
        super("hello-service", HelloApplication.class);
        System.setProperty("spring.profiles.active", "default");
        System.setProperty("spring.application.name", "hello-service");
        System.setProperty("server.port", "8081");
    }
}
