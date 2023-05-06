package com.example.homework_6;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Context {
    private Map<Class<?>, Object> beans = new HashMap<>();

    public Context(String basePackage) throws Exception {
        for (Class<?> clazz : ClassFinder.find(basePackage)) {
            if (clazz.isAnnotationPresent(Component.class)) {
                Object bean = clazz.getDeclaredConstructor().newInstance();
                beans.put(clazz, bean);
            }
        }

        for (Map.Entry<Class<?>, Object> entry : beans.entrySet()) {
            Class<?> clazz = entry.getKey();
            Object bean = entry.getValue();

            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Object dependency = beans.get(field.getType());
                    field.setAccessible(true);
                    field.set(bean, dependency);
                }
            }
        }
    }

    public <T> T getBean(Class<T> clazz) {
        return clazz.cast(beans.get(clazz));
    }
}

