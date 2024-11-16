package io.icejoywoo.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@SpringBootApplication
public class LiteLauncherApplication {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        MultipleServiceStarter.start(args);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                MultipleServiceStarter.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }

}
