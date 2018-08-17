package org.everest.context;

import dic.AutoInject;
import org.everest.context.classHandler.ClassHandler;
import org.everest.context.exception.ContextInstanceException;
import org.everest.core.dic.Container;
import org.everest.core.dic.decorator.AutoWired;
import org.everest.core.dic.decorator.Bean;
import org.everest.decorator.HandlerBy;
import org.everest.utils.Assert;
import org.everest.utils.ReflexionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DependencyService {
    private Logger logger = LoggerFactory.getLogger(DependencyService.class);
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
                        String message = "The classHandler " + handler.handler() + " not found in Context classHandlers. Add it" +
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
