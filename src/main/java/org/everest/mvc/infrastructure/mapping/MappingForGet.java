package org.everest.mvc.infrastructure.mapping;

import org.everest.mvc.component.MappingDescriptor;
import org.everest.mvc.decorator.GetMapping;
import org.everest.mvc.decorator.HttpMapping;
import org.everest.mvc.httpContext.HttpMethod;

public class MappingForGet extends MappingFor<GetMapping>{
    @Override
    public MappingDescriptor getDescriptor(GetMapping mapping) {
        HttpMapping httpMapping = GetMapping.class.getAnnotation(HttpMapping.class);
        MappingDescriptor descriptor = new MappingDescriptor(httpMapping);
        descriptor.setName(mapping.name());
        descriptor.setValue(mapping.value());
        descriptor.setVerb(HttpMethod.GET);
        return descriptor;
    }
}
