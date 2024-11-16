package io.icejoywoo.springboot;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MultipleServiceStarter {

    private final static List<Container> containers = new ArrayList<>(4);

    private final static String RUNNER_PACKAGE = "com.xxx";

    protected static Set<Class<?>> scan() throws IOException, ClassNotFoundException {
        Set<Class<?>> classes = new HashSet<>();

        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                ClassUtils.convertClassNameToResourcePath(RUNNER_PACKAGE) + "/**/*.class";
        Resource[] resources = resourcePatternResolver.getResources(pattern);
        //MetadataReader 的工厂类
        MetadataReaderFactory readerfactory = new CachingMetadataReaderFactory(resourcePatternResolver);
        for (Resource resource : resources) {
            //用于读取类信息
            MetadataReader reader = readerfactory.getMetadataReader(resource);
            //扫描到的class
            String classname = reader.getClassMetadata().getClassName();
            Class<?> clazz = Class.forName(classname);
            if (MultipleServiceRunner.class.isAssignableFrom(clazz) && !Objects.equals(MultipleServiceRunner.class, clazz)) {
                classes.add(clazz);
            }
        }

        return classes;
    }

    public static void start(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Set<Class<?>> runnerClasses = scan();

        for (Class<?> runnerClass : runnerClasses) {
            MultipleServiceRunner runnerInstance = (MultipleServiceRunner) runnerClass.newInstance();
            containers.add(new Container(runnerClass, runnerInstance));

            runnerInstance.setArgs(args);
            runnerInstance.run();
        }
    }

    public static void stop() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (Container container : containers) {
            container.runnerInstance.stop();
        }
    }

    protected static class Container {
        private Class<?> runnerClass;

        private MultipleServiceRunner runnerInstance;

        public Container(Class<?> runnerClass, MultipleServiceRunner runnerInstance) {
            this.runnerClass = runnerClass;
            this.runnerInstance = runnerInstance;
        }
    }

}
