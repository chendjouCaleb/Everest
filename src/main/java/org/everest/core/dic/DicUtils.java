package org.everest.core.dic;

import org.everest.core.dic.exception.ComponentCreationException;
import org.everest.core.dic.exception.InstanceException;
import org.everest.exception.ReflexionException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DicUtils {
    public static Object instanciateClass(Class className) {
        Object obj = null;
        try {
            obj = className.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static void assignField(Field field, Object object, Object value){
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new InstanceException(e);
        }
    }

    public static Object callRemote(Object instance, String sMethod, Object... arguments) {
        Class<?>[] argumentTypes = createArgumentTypes(arguments);
        Method method = null;
        try {
            method = instance.getClass().getMethod(sMethod, argumentTypes);
        } catch (NoSuchMethodException e) {
            try {
                throw new ReflexionException("The method " + sMethod + " not found in class " + instance.getClass());
            } catch (ReflexionException e1) {
                e1.printStackTrace();
            }
        }
        method.setAccessible(true);
        Object[] argumentsWithSession = createArguments(arguments);
        Object object = null;
        try {
            object = method.invoke(instance, argumentsWithSession);
        } catch (Exception e) {
            try {
                throw new ReflexionException(e);
            } catch (ReflexionException e1) {
                e1.printStackTrace();
            }
        }
        return object;
    }

    public static Object[] createArguments(Object[] arguments) {
        Object[] args = new Object[arguments.length];
        System.arraycopy(arguments, 0, args, 0, arguments.length);
        return args;
    }

    public static Class<?>[] createArgumentTypes(Object[] arguments) {
        Class<?>[] types = new Class[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            types[i] = arguments[i].getClass();
        }
        return types;
    }

    public static Class getClass(String name) {
        Class clazz = null;
        try {
            clazz = Class.forName(name);
        } catch (ClassNotFoundException e) {
            try {
                throw new ComponentCreationException("Class with name " + name + " does not exist");
            } catch (ComponentCreationException e1) {
                e1.printStackTrace();
            }
        }
        return clazz;
    }
}
