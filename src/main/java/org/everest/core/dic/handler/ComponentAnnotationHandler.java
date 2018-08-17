package org.everest.core.dic.handler;

import org.everest.core.dic.Instance;
import org.everest.decorator.Component;

public class ComponentAnnotationHandler implements ITypeAnnotationHandler<Component> {
    @Override
    public void handle(Component annotation, Instance instance) {
        if(annotation.value().equals("")){
            instance.setKey(annotation.value());
        }
    }
}
