package org.everest.context.classHandler;

import org.everest.context.ApplicationContext;
import org.everest.decorator.Component;
import org.everest.decorator.Repository;

public class RepositoryHandler implements ClassHandler<Repository>{
    @Override
    public void handleClass(Class<?> clazz, Repository annotation, ApplicationContext context) {
        if(annotation.value().equals("")){
            context.addInstance(clazz);
        }else {
            context.addInstance(clazz, annotation.value());
        }
    }
}
