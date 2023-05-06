package com.example.homework_6;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ClassFinder {
    public static List<Class<?>> find(String basePackage) throws Exception {
        String basePath = basePackage.replace(".", "/");
        URL resource = ClassLoader.getSystemClassLoader().getResource(basePath);
        File dir = new File(resource.toURI());

        List<Class<?>> classes = new ArrayList<>();
        for (File file : dir.listFiles()) {
            String fileName = file.getName();
            if (fileName.endsWith(".class")) {
                String className = basePackage + "." + fileName.substring(0, fileName.lastIndexOf('.'));
                classes.add(Class.forName(className));
            }
        }

        return classes;
    }
}

