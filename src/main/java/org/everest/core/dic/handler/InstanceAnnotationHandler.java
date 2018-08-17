package org.everest.core.dic.handler;

import org.everest.decorator.Instance;

public class InstanceAnnotationHandler implements ITypeAnnotationHandler<Instance> {
    @Override
    public void handle(Instance annotation, org.everest.core.dic.Instance instance) {
        if(annotation.value().equals("")){
            instance.setKey(annotation.value());
        }
    }
}
