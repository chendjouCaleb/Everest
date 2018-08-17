package org.everest.utils;

import org.apache.commons.lang3.AnnotationUtils;
import org.everest.exception.ReflexionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.CollationElementIterator;
import java.util.*;

public class ReflexionUtils {
    private static Logger logger = LoggerFactory.getLogger(ReflexionUtils.class);
    public static Object instantiateClass(Class className) {
        Object obj;
        try {
            obj = className.newInstance();
        } catch (Exception e) {
            throw new ReflexionException(e);
        }
        return obj;
    }

    /**
     * Scans all classes accessible from the httpContext class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     */
    public static Class[] getClasses(String packageName){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources;
        try {
            resources = classLoader.getResources(path);
        } catch (IOException e) {
            throw new ReflexionException(e);
        }
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     */
    private static List<Class> findClasses(File directory, String packageName){
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                try {
                    classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
                } catch (ClassNotFoundException e) {
                    throw new ReflexionException(e);
                }
            }
        }
        return classes;
    }

    public static List<Class> getClasses(String... packageNames){
        List<Class> classList = new ArrayList<>();
        for(String name: packageNames){
            classList.addAll(Arrays.asList(getClasses(name)));
        }
        return classList;
    }
    public static List<Class> getClasses(Collection<String> packageNames){
        List<Class> classList = new ArrayList<>();
        packageNames.forEach(name -> classList.addAll(Arrays.asList(getClasses(name))));
        return classList;
    }

    public static Method findMethodByAnnotation(Class type, Class<? extends Annotation> annotation){
        Method[] methods = type.getDeclaredMethods();
        for (Method method:methods){
            if(method.getAnnotation(annotation) != null){
                return method;
            }
        }
        return null;
    }

    public static boolean isAnnotatedBy(Method method, Class<? extends Annotation>[] annotations){
        for (Class annotation:annotations) {
            if (method.getAnnotation(annotation) != null) {
                return true;
            }
        }
        return false;
    }
    public static boolean isAnnotatedBy(Method method, Collection<Class<? extends Annotation>> annotations){
        for (Class annotation:annotations) {
            if (method.getAnnotation(annotation) != null) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAnnotatedBy(Class type, Class<? extends Annotation> annotation){
        return type.getAnnotation(annotation) != null;
    }


    public static boolean isAnnotatedBy(Class<?> type, Collection<Class<? extends Annotation>> annotations){
        for (Class annotation:annotations){
            if (type.getAnnotation(annotation) != null){
                return true;
            }
        }
        return false;

    }


    public static boolean isAnnotatedBy(Field field, Class<? extends Annotation> annotation){
        return field.getAnnotation(annotation) != null;
    }

    public static boolean isAnnotatedByAnnotatedAnnotation(Method method, Class<? extends Annotation> annotation){
        if(method.getAnnotation(annotation) != null){
            return true;
        }
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation1: annotations){
            if (annotation1.annotationType().getAnnotation(annotation) != null){
                return true;
            }
        }
        return false;
    }
    public static Annotation annotatedAnnotation(Method method, Class<? extends Annotation> annotation){
        if(method.getAnnotation(annotation) != null){
            return method.getAnnotation(annotation);
        }
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation1: annotations){
            if (annotation1.annotationType().getAnnotation(annotation) != null){
                return annotation1;
            }
        }
        return null;
    }


    public static List<Method> findMethodsByAnnotation(Class type, Class<? extends Annotation> annotation){
        List<Method> methods = new ArrayList<>();
        Method[] declaredMethods = type.getDeclaredMethods();
        for (Method method:declaredMethods){
            if(isAnnotatedBy(method, new Class[]{annotation})){
                methods.add(method);
            }
        }
        return methods;
    }

    public static List<Method> findMethodsByAnnotation(Class type, Class<? extends Annotation>... annotations){
        List<Method> methods = new ArrayList<>();
        Method[] declaredMethods = type.getDeclaredMethods();
        for (Method method:declaredMethods){
            if(isAnnotatedBy(method, annotations)){
                methods.add(method);
            }
        }
        return methods;
    }

    public static List<Field> findFieldsByAnnotation(Class type, Class<? extends Annotation> annotation){
        List<Field> fields = new ArrayList<>();
        Field[] declaredFields = type.getDeclaredFields();
        for (Field field: declaredFields){
            if(isAnnotatedBy(field, annotation)){
                fields.add(field);
            }
        }
        return fields;
    }

    public static List<Field> getAllFields(Class type){
        List<Field> fields = new ArrayList<>();
        Class current = type;
        while (current.getSuperclass() != null){
            fields.addAll(Arrays.asList(current.getDeclaredFields()));
            current = current.getSuperclass();
        }
        return fields;
    }

    @SafeVarargs
    public static List<Field> findFieldsByAnnotation(Class type, Class<? extends Annotation>... annotations){
        List<Field> fields = new ArrayList<>();
        List<Field> typeFields = getAllFields(type);
        for (Field field: typeFields){
            //System.out.println("Fields: " + field.getName());
            for (Class<? extends Annotation> annotation:annotations){
                if(isAnnotatedBy(field, annotation) && !fields.contains(field)){
                    fields.add(field);
                }
            }
        }
        return fields;
    }

    public static List<Method> findMethodsByAnnotatedAnnotation(Class type, Class<? extends Annotation> annotation){
        List<Method> methods = new ArrayList<>();
        Method[] declaredMethods = type.getDeclaredMethods();
        for (Method method:declaredMethods){
            if(isAnnotatedByAnnotatedAnnotation(method, annotation)){
                methods.add(method);
            }
        }
        return methods;
    }

    public static boolean isImplement(Class type, Class superType){
        if(type == superType){
            return true;
        }
        return Arrays.asList(type.getInterfaces()).contains(superType);
    }

    public static Method getMethod(Class type, String name){
        Method[] methods = type.getDeclaredMethods();
        for (Method method: methods){
            if(method.getName().equals(name)){
                return method;
            }
        }
return null;
    }
}
