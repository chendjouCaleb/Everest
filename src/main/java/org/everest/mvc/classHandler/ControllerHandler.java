package org.everest.mvc.classHandler;

import org.everest.mvc.httpContext.decorator.HttpController;
import org.everest.context.ApplicationContext;
import org.everest.context.classHandler.ClassHandler;

public class ControllerHandler implements ClassHandler<HttpController> {
    @Override
    public void handleClass(Class<?> clazz, HttpController annotation, ApplicationContext context) {
        if(annotation.name().equals("")){
            context.addInstance(clazz);
        }else {
            context.addInstance(clazz, annotation.name());
        }
    }
}
