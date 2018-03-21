package org.everest.main.component.classHandler;

import org.everest.context.ApplicationContext;
import org.everest.context.classHandler.ClassHandler;
import org.everest.decorator.RequestFilter;

public class RequestFilterHandler implements ClassHandler<RequestFilter> {
    @Override
    public void handleClass(Class<?> clazz, RequestFilter annotation, ApplicationContext context) {
        if(annotation.value().equals("")){
            context.addInstance(clazz);
        }else {
            context.addInstance(clazz, annotation.value());
        }
    }
}
