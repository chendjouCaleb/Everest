package Everest.Core;

import Everest.Core.Exception.MethodNotFoundException;
import Everest.Core.Exception.ReflexionException;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Contains the utilities methods for class reflexion
 */
public class Reflexions {

    /**
     * Create an instance of a {@param className}.
     * @param className The type of the instance to create.
     * @return An instance of a {@param className}.
     */
    public static Object instanciateClass(@Nonnull Class className) {
        Object obj;
        try {
            obj = className.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    /**
     * Assign a {@param value} to the {@param field} of the {@param instance}.
     * @param field The field to assign.
     * @param instance The instance of the field.
     * @param value The value to assign.
     */
    public static void assignField(Field field, Object instance, Object value){
        try {
            field.set(instance, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Invoke an method with the provided parameters.
     * @param instance The instance of the method.
     * @param sMethod The method name to invoke.
     * @param arguments The parameter value of the invocation.
     * @return The result of the invocation.
     */
    public static Object callRemote(Object instance, String sMethod, Object... arguments) {
        Class<?>[] argumentTypes = createArgumentTypes(arguments);
        Method method = null;
        try {
            method = instance.getClass().getMethod(sMethod, argumentTypes);
        } catch (NoSuchMethodException e) {
            throw new ReflexionException("The method " + sMethod + " not found in class " + instance.getClass(), e);
        }
        method.setAccessible(true);
        Object[] argumentsWithSession = createArguments(arguments);
        Object object = null;
        try {
            object = method.invoke(instance, argumentsWithSession);
        } catch (Exception e) {
            throw new ReflexionException(e);
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

    /**
     * Get the class based on her name.
     * @param name The string name of the class.
     * @return The corresponding java {@link Class}.
     */
    public static Class getClass(String name) {
        Class clazz = null;
        try {
            clazz = Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new ReflexionException("Class with name " + name + " does not exist", e);
        }
        return clazz;
    }

    /**
     * Get the  methods with a name {@param methodName} and {@param parameterTypes} as parameter types.
     * @param type The class that contains the method.
     * @param methodName The name of the method.
     * @param parameterTypes The types of the method parameters.
     * @return The method corresponding the {@param methodName}.
     */
    public static Method getMethod(Class<?> type, String methodName, Class<?>... parameterTypes){
        try {
            return type.getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            throw new MethodNotFoundException(e);
        }
    }
}
