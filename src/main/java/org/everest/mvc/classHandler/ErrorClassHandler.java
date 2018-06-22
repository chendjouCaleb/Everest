package org.everest.mvc.classHandler;

import org.everest.context.ApplicationContext;
import org.everest.context.classHandler.ClassHandler;
import org.everest.decorator.ErrorHandler;

public class ErrorClassHandler implements ClassHandler<ErrorHandler> {
    @Override
    public void handleClass(Class<?> clazz, ErrorHandler annotation, ApplicationContext context) {
        if(annotation.value().equals("")){
            context.addInstance(clazz);
        }else {
            context.addInstance(clazz, annotation.value());
        }
    }
}
