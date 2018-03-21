package org.everest.utils;

import org.everest.exception.ReflexionException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ReflexionUtils {
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
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
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
}
