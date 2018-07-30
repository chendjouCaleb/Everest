package org.everest.mvc.infrastructure.mapping;

import org.everest.mvc.component.MappingDescriptor;
import org.everest.mvc.decorator.HttpMapping;
import org.everest.mvc.decorator.PathMapping;

public class MappingForPath extends MappingFor<PathMapping>{
    @Override
    public MappingDescriptor getDescriptor(PathMapping mapping) {
        HttpMapping httpMapping = PathMapping.class.getAnnotation(HttpMapping.class);
        MappingDescriptor descriptor = new MappingDescriptor(httpMapping);
        descriptor.setName(mapping.name());
        descriptor.setValue(mapping.value());
        return descriptor;
    }
}
