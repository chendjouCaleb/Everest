package org.everest.context;

import org.everest.context.classHandler.ClassHandler;
import org.everest.context.exception.ContextInstanceException;
import org.everest.core.dic.Container;
import org.everest.core.dic.DicUtils;
import org.everest.core.dic.Instance;
import org.everest.core.dic.decorator.Bean;
import org.everest.core.dic.enumeration.Scope;
import org.everest.decorator.HandlerBy;
import org.everest.utils.ReflexionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

class DependencyService {
    public void addDependenciesByPackage(String packageName, ApplicationContext context) {
        Class[] classes = ReflexionUtils.getClasses(packageName);
        for (Class<?> classe: classes){
            Annotation[] annotations = classe.getAnnotations();
            for (Annotation annotation: annotations){
                HandlerBy handler = annotation.annotationType().getAnnotation(HandlerBy.class);
                if(handler != null){
                    ClassHandler classHandler = context.getClassHandlers().get(handler.handler());
                    if(classHandler != null){
                        classHandler.handleClass(classe, annotation, context);
                    }else {
                        String message = "The classHandler " + handler.handler() + " not found in context classHandlers. Please add it" +
                                " to your ApplicationContext";
                        throw new ContextInstanceException(message);
                    }

                }
            }
        }
    }

    public void addDependenciesByPackages(String[] basePackages, ApplicationContext context) {
        for (String basePackage : basePackages) {
            addDependenciesByPackage(basePackage, context);
        }
    }

    public void addDependency(Container container, Class clazz) {

    }

    public void addDependenciesByFactory(Container container, Class className) {

    }


}
