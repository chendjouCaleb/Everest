package router;

import annotation.FilterBy;
import exception.RouteParamsException;
import filter.Filter;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouterUtils {
    public static List<Filter> getFilters(Method method){
        List<Filter> annotations = new ArrayList<>();
        for (Annotation annotation:method.getDeclaredAnnotations()){
            System.out.println("Annotation: " + annotation.annotationType().getName());
            FilterBy filter = annotation.annotationType().getAnnotation(FilterBy.class);
            if(filter != null){
                System.out.println("CLASSE FILTER" + filter.filter().getName());
                try {
                    Filter ins = (Filter)(filter.filter().getConstructor().newInstance());
                    annotations.add(ins);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
        return annotations;
    }
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

    public static Object castStringToNumber(String string, Class<?> type) throws Exception{
        Object value;
        if(type.equals(Character.class)){
            return string.charAt(0);
        }
        if(type.equals(String.class)){
            return string;
        }
        Map<Class<?>, Class<?>> numbersType = new HashMap<>();
        numbersType.put(int.class, Integer.class);
        numbersType.put(double.class,Double.class);
        numbersType.put(float.class,Float.class);
        numbersType.put(long.class,Long.class);

        if(numbersType.containsKey(type)){
            throw new RouteParamsException("Le type primitif ne sont supportés en paramètre des méthode d'un controller");
        }
        if(numbersType.containsValue(type)){
            Method m;
            try {
                m = type.getMethod("valueOf", String.class);
                value = m.invoke(m, string);
                return value;
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {

                e.printStackTrace();
            }
        }
        throw new Exception("La chaine " + string + " n'a pas pu etre convertir en " + type.getName());
    }

    public static Object[] params(String[] params, Method method) throws RouteParamsException {
        Object[] values = new Object[params.length];

        for(int i = 0; i < values.length; i++){
            Class<?> type = method.getParameterTypes()[i];
            try {
                values[i] = castStringToNumber(params[i], type);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RouteParamsException(e.getMessage()  + "\n\n" +
                        "Le paramètre " + params[i] + " l'url n'a pas pu etre convertir en " + type.getName());
            }
        }
        return values;
    }
}
