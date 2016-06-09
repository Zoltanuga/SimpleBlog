package by.it.util;


import java.lang.reflect.Method;

public class Factory {

    public static  <T> T getInstance(Class<T> clazz) throws Exception {
        Method[] methods = clazz.getMethods();
        T object = clazz.newInstance();
        for (Method method : methods) {
            Invoke annotation = method.getAnnotation(Invoke.class);
            if (annotation != null) {
                for (int i = 0; i < annotation.repeat(); i++) {
                    method.invoke(object);
                }
            }
        }return object;
    }

}
