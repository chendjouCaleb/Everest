package org.everest.mvc.variableResolver;


import org.everest.context.ApplicationContext;
import org.everest.context.classHandler.ClassHandler;
import org.everest.mvc.variableResolver.decorator.VariableResolver;

public class VariableResolverHandler implements ClassHandler<VariableResolver>{
    @Override
    public void handleClass(Class<?> clazz, VariableResolver annotation, ApplicationContext context) {
        if(annotation.value().equals("")){
            context.addInstance(clazz);
        }else {
            context.addInstance(clazz, annotation.value());
        }
    }
}
