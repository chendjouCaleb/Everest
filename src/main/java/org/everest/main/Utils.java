package org.everest.main;

import java.lang.reflect.Method;

public class Utils {
    public static Object callRemote(Object instance, String sMethod, Object... arguments) throws Exception {
        Class<?>[] argumentTypes = createArgumentTypes(arguments);
        Method method = instance.getClass().getMethod(sMethod, argumentTypes);
        Object[] argumentsWithSession = createArguments(arguments);
        return method.invoke(instance, argumentsWithSession);
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
}
