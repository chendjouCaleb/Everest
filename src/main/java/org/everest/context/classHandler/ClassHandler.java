package org.everest.context.classHandler;

import org.everest.context.ApplicationContext;

public interface ClassHandler<T> {
    void handleClass(Class<?> clazz, T annotation, ApplicationContext context);
}
