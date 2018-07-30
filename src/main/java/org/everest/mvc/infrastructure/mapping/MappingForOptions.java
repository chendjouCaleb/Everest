package org.everest.mvc.infrastructure.mapping;

import org.everest.mvc.component.MappingDescriptor;
import org.everest.mvc.decorator.HttpMapping;
import org.everest.mvc.decorator.OptionsMapping;

public class MappingForOptions extends MappingFor<OptionsMapping>{
    @Override
    public MappingDescriptor getDescriptor(OptionsMapping mapping) {
        HttpMapping httpMapping = OptionsMapping.class.getAnnotation(HttpMapping.class);
        MappingDescriptor descriptor = new MappingDescriptor(httpMapping);
        descriptor.setName(mapping.name());
        descriptor.setValue(mapping.value());
        return descriptor;
    }
}
