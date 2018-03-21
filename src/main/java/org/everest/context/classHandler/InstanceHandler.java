package org.everest.context.classHandler;

import org.everest.context.ApplicationContext;
import org.everest.decorator.Instance;
import org.everest.utils.ReflexionUtils;

public class InstanceHandler implements ClassHandler<Instance> {
    @Override
    public void handleClass(Class<?> clazz, Instance instance, ApplicationContext context) {
        if (instance.value().equals("")) {
            context.addInstance(clazz);
        } else {
            context.addInstance(clazz, instance.value());
        }
    }
}
