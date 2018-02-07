package router;

import annotation.FilterBy;
import exception.RouteParamsException;
import filter.Filter;
import org.everest.main.Utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class RouterUtils {
    public static List<Filter> getFilters(Class controller, Method method){
        List<Filter> filters = new ArrayList<>();
        List<Annotation> annotations = new ArrayList<>();
        annotations.addAll(Arrays.asList(controller.getAnnotations()));
        annotations.addAll(Arrays.asList(method.getAnnotations()));
        for (Annotation annotation:annotations){
            FilterBy filter = annotation.annotationType().getAnnotation(FilterBy.class);
            if(filter != null){
                try {
                    Filter instance = (Filter)(filter.filter().getConstructor().newInstance());
                    instance.init(annotation);
                    filters.add(instance);
                }catch (Exception e) {
                    Utils.throwException(e);
                }
            }
        }
        return filters;
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

    public static Object[] params(String[] params, Method method){
        Object[] values = new Object[params.length];

        for(int i = 0; i < values.length; i++){
            Class<?> type = method.getParameterTypes()[i];
            try {
                values[i] = castStringToNumber(params[i], type);
            } catch (Exception e) {
                String message ="Le paramètre " + params[i] + " l'url n'a pas pu etre convertir en " + type.getName();
                Utils.throwException(new RouteParamsException(message));
            }
        }
        return values;
    }
}
