package io.icejoywoo.springboot;

public class StudentServiceRunner extends MultipleServiceRunner {
    public StudentServiceRunner() {
        super("student-service", StudentServiceApplication.class);
        System.setProperty("spring.profiles.active", "default");
        System.setProperty("spring.application.name", "student-service");
        System.setProperty("server.port", "8082");
    }
}
