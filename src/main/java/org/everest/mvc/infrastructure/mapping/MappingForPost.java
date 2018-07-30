package org.everest.mvc.infrastructure.mapping;

import javafx.geometry.Pos;
import org.everest.mvc.component.MappingDescriptor;
import org.everest.mvc.decorator.HttpMapping;
import org.everest.mvc.decorator.PostMapping;

public class MappingForPost extends MappingFor<PostMapping>{
    @Override
    public MappingDescriptor getDescriptor(PostMapping mapping) {
        HttpMapping httpMapping = PostMapping.class.getAnnotation(HttpMapping.class);
        MappingDescriptor descriptor = new MappingDescriptor(httpMapping);
        descriptor.setName(mapping.name());
        descriptor.setValue(mapping.value());
        return descriptor;
    }
}
