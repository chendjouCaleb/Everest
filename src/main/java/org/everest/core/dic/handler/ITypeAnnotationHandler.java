package org.everest.core.dic.handler;

import org.everest.core.dic.Instance;

import java.lang.annotation.Annotation;

public interface ITypeAnnotationHandler<T extends Annotation> {
    void handle(T annotation, Instance instance);
}
