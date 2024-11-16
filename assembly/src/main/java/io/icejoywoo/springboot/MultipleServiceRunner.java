package io.icejoywoo.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Optional;

public abstract class MultipleServiceRunner {

    private ConfigurableApplicationContext applicationContext;

    private final String applicationName;

    private final Class<?>[] applicationClasses;

    private String[] args;

    private final static Object lock = new Object();

    private Boolean wait = Boolean.FALSE;

    public MultipleServiceRunner(String applicationName, Class<?>... applicationClasses) {
        this.applicationName = applicationName;
        this.applicationClasses = applicationClasses;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public void run() {
        if(applicationContext != null) {
            throw new IllegalStateException("AppContext must be null to run this backend");
        }
        runBackendInThread();
        waitUntilBackendIsStarted();
    }

    private void waitUntilBackendIsStarted() {
        try {
            synchronized (lock) {
                if(wait) {
                    lock.wait();
                }
            }
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    private void runBackendInThread() {
        final Thread runnerThread = new ApplicationRunner(applicationName);
        wait = Boolean.TRUE;
        runnerThread.setContextClassLoader(applicationClasses[0].getClassLoader());
        runnerThread.start();
    }

    public void stop() {
        if (Optional.ofNullable(applicationContext).isPresent()) {
            SpringApplication.exit(applicationContext);
            applicationContext = null;
        }
    }

    protected class ApplicationRunner extends Thread {

        public ApplicationRunner(String name) {
            super(name);
        }

        @Override
        public void run() {
            applicationContext = SpringApplication.run(applicationClasses, args);
            synchronized (lock) {
                wait = Boolean.FALSE;
                lock.notify();
            }
        }
    }

}