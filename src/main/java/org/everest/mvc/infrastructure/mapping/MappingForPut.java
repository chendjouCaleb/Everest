package org.everest.mvc.infrastructure.mapping;

import org.everest.mvc.component.MappingDescriptor;
import org.everest.mvc.decorator.HttpMapping;
import org.everest.mvc.decorator.PutMapping;

public class MappingForPut extends MappingFor<PutMapping>{

    public MappingDescriptor getDescriptor(PutMapping mapping) {
        HttpMapping httpMapping = PutMapping.class.getAnnotation(HttpMapping.class);
        MappingDescriptor descriptor = new MappingDescriptor(httpMapping);
        descriptor.setName(mapping.name());
        descriptor.setValue(mapping.value());
        return descriptor;
    }
}
