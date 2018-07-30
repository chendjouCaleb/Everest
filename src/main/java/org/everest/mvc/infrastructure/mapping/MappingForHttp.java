package org.everest.mvc.infrastructure.mapping;

import org.everest.mvc.component.MappingDescriptor;
import org.everest.mvc.decorator.HttpMapping;
import org.everest.mvc.decorator.PostMapping;

public class MappingForHttp extends MappingFor<HttpMapping>{
    @Override
    public MappingDescriptor getDescriptor(HttpMapping mapping) {
        MappingDescriptor descriptor = new MappingDescriptor(mapping);
        descriptor.setName(mapping.name());
        descriptor.setValue(mapping.value());
        return descriptor;
    }
}
