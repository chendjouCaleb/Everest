package Everest.Core;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains the methods for manipulate {@link java.lang.annotation.Annotation}.
 */
public class Annotations {

    /**
     * Get and return the first annotation annotated by {@param annotation}.
     * @param method The method to get the annotation.
     * @param annotation The annotation of annotated annotation.
     * @return The first annotation annotated by {@param annotation}.
     */
    public static Annotation annotatedAnnotation(Method method, Class<? extends Annotation> annotation){
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation1: annotations){
            if (annotation1.annotationType().getAnnotation(annotation) != null){
                return annotation1;
            }
        }
        return null;
    }


    /**
     * Get and return the first annotation annotated by {@param annotation}.
     * @param parameter The parameter to get the annotated annotation.
     * @param annotation The annotation of annotated annotation.
     * @return The first annotation annotated by {@param annotation}.
     */
    public static Annotation annotatedAnnotation(Parameter parameter, Class<? extends Annotation> annotation){
        Annotation[] annotations = parameter.getAnnotations();
        for (Annotation annotation1: annotations){
            if (annotation1.annotationType().getAnnotation(annotation) != null){
                return annotation1;
            }
        }
        return null;
    }


    /**
     * Get and return the annotations annotated by {@param annotation}.
     * @param element The element to get the annotated annotation.
     * @param annotation The annotation of annotated annotation.
     * @return The list of annotations annotated by {@param annotation}.
     */
    public static List<Annotation> annotatedAnnotations(AnnotatedElement element, Class<? extends Annotation> annotation){
        List<Annotation> list = new ArrayList<>();
        Annotation[] annotations = element.getAnnotations();
        for (Annotation annotation1: annotations){
            if (annotation1.annotationType().getAnnotation(annotation) != null){
                list.add(annotation1);
            }
        }
        return list;
    }

    /**
     * Check if the {@param field} is annotated by {@param annotation}.
     * @param field The field
     * @param annotation The annotation
     */
    public static boolean isAnnotatedBy(Field field, Class<? extends Annotation> annotation){
        return field.getAnnotation(annotation) != null;
    }
}
