package org.everest.mvc.infrastructure.mapping;

import org.everest.mvc.component.MappingDescriptor;
import org.everest.mvc.decorator.DeleteMapping;
import org.everest.mvc.decorator.HttpMapping;

public class MappingForDelete extends MappingFor<DeleteMapping>{
    @Override
    public MappingDescriptor getDescriptor(DeleteMapping mapping) {
        HttpMapping httpMapping = mapping.annotationType().getAnnotation(HttpMapping.class);
        MappingDescriptor descriptor = new MappingDescriptor(httpMapping);
        descriptor.setName(mapping.name());
        descriptor.setValue(mapping.value());
        return descriptor;
    }
}
