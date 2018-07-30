package org.everest.mvc.infrastructure.mapping;

import org.everest.mvc.component.MappingDescriptor;
import org.everest.utils.Assert;

import java.lang.annotation.Annotation;
import java.util.*;

public class MappingGetter {
    private List<MappingFor> mappingHandlers = new ArrayList<>();

    public MappingGetter(){
        mappingHandlers.add(new MappingForDelete());
        mappingHandlers.add(new MappingForPost());
        mappingHandlers.add(new MappingForGet());
        mappingHandlers.add(new MappingForPut());
        mappingHandlers.add(new MappingForHttp());
        mappingHandlers.add(new MappingForPath());
        mappingHandlers.add(new MappingForOptions());
    }

    public MappingDescriptor mappingDescriptor(Annotation annotation){
        MappingFor mappingFor = mappingHandlers.stream()
                .filter(mappingFor1 -> mappingFor1.getMapper().equals(annotation.annotationType()))
                .findFirst().orElse(null);

        Assert.notNull(mappingFor, "No mapping descriptor found for annotation: " + annotation.annotationType().getSimpleName());
        return mappingFor.getDescriptor(annotation);
    }
}
