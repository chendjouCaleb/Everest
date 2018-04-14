package org.everest.main;

import org.everest.main.component.errorHandler.IErrorHandler;
import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;

import java.lang.reflect.Method;

public class Utils {
    public static Object callRemote(Object instance, String sMethod, Object... arguments) throws Exception {
        Class<?>[] argumentTypes = createArgumentTypes(arguments);
        Method method = instance.getClass().getMethod(sMethod, argumentTypes);
        method.setAccessible(true);
        Object[] argumentsWithSession = createArguments(arguments);
        return method.invoke(instance, argumentsWithSession);
    }

    public static Object callRemote(Object instance, Method method, Object... arguments) throws Exception {
        method.setAccessible(true);
        Object[] argumentsWithSession = createArguments(arguments);
        return method.invoke(instance, argumentsWithSession);
    }

    public Object instanciateClass(Class className){
        Object obj = null;
        try {
            obj = className.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
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

    public static void throwException(Throwable e){
        try {
            System.out.println("Throw exception e: " + e.getClass());
            throw new Throwable(e);
        } catch (Throwable throwable) {
            System.out.println("Error during the throwing e: " + throwable.getClass());
            throwable.printStackTrace();
        }
    }

    public static void throwException(String message){
        try {
            throw new Throwable(message);
        } catch (Throwable throwable) {
            System.out.println("Error during the throwing e: " + throwable.getClass());
            throwable.printStackTrace();
        }
    }
    public static void handleError(Request request, Response response, Throwable throwable){
        WebApplication app = WebApplication.getApp();
        System.out.println("Error was occurred during thr request handling: " + throwable.getClass());
        for(IErrorHandler errorHandler: app.getErrorHandlers()){
            if (throwable.getClass().equals(errorHandler.getErrorType())){
                errorHandler.handleError(request, response, throwable);
                return;
            }
        }
        System.out.println("Using the default error Manager");
        app.getDefaultErrorHandler().handleError(request, response, throwable);
    }
}
