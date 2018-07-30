package org.everest.mvc.infrastructure.mapping;

import org.everest.mvc.component.MappingDescriptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;

public abstract class MappingFor<T extends Annotation> {
    private Class<T> mapper;
    public abstract MappingDescriptor getDescriptor(T mapping);
    public MappingFor(){
        mapper = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Class<T> getMapper() {
        return mapper;
    }
}
