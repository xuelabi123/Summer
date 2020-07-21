package com.summer.bean;

import com.summer.annotation.Autowired;
import com.summer.annotation.Bean;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author panyox
 * @date 2020/7/21 6:15 下午
 */
public class ApplicationContext extends BeanFactory {

    public ApplicationContext() throws Exception {
        beans = new ConcurrentHashMap<String, Object>();
        initBeans();
        initBeanFileds();
    }

    private void initBeans() throws InstantiationException, IllegalAccessException, NoSuchMethodException {
        Reflections reflections = new Reflections("com.summer");
        Set<Class<?>> components = reflections.getTypesAnnotatedWith(Bean.class);
        for (Class<?> cla : components) {
            String className = cla.getSimpleName();
            String beanId = fisrtChartoLowercase(className);
            Object instance = getInstance(cla);
            put(beanId, instance);
        }
    }

    private Object getInstance(Class<?> cla) throws IllegalAccessException, InstantiationException {
        return cla.newInstance();
    }

    public static String fisrtChartoLowercase(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        return sb.append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    private void initBeanFileds() throws IllegalAccessException {
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            Object bean = entry.getValue();
            inject(bean);
        }
    }

    public void inject(Object object) throws IllegalAccessException {
        Class<?> cla = object.getClass();
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields) {
            Autowired at = field.getAnnotation(Autowired.class);
            if (at != null) {
                String beanId = field.getName();
                Object bean = getBean(beanId);
                if (bean != null) {
                    field.setAccessible(true);
                    field.set(object, bean);
                }
            }
        }
    }

    @Override
    public Object getBean(String id) {
        return beans.get(id);
    }

    @Override
    public void put(String id, Object object) {
        beans.put(id, object);
    }

}
