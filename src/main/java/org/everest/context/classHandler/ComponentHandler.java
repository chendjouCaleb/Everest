package org.everest.context.classHandler;

import org.everest.context.ApplicationContext;
import org.everest.decorator.Component;

public class ComponentHandler implements ClassHandler<Component>{
    @Override
    public void handleClass(Class<?> clazz, Component annotation, ApplicationContext context) {
        if(annotation.value().equals("")){
            context.addInstance(clazz);
        }else {
            context.addInstance(clazz, annotation.value());
        }
    }
}
