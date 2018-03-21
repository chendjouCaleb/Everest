package org.everest.main.component.classHandler;

import org.everest.context.ApplicationContext;
import org.everest.context.classHandler.ClassHandler;
import org.everest.decorator.Listener;

public class ListenerHandler implements ClassHandler<Listener> {
    @Override
    public void handleClass(Class<?> clazz, Listener annotation, ApplicationContext context) {
        if(annotation.value().equals("")){
            context.addInstance(clazz);
        }else {
            context.addInstance(clazz, annotation.value());
        }
    }
}
