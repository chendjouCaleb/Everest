package org.everest.mvc.classFilter;

import Everest.Mvc.ValueResolver.IAnnotationValueResolver;
import org.everest.core.dic.handler.ITypeFilter;

import java.util.Arrays;

public class AnnotationValueResolverClassFilter implements ITypeFilter {
    @Override
    public boolean isAdmissible(Class type) {

        return Arrays.asList(type.getInterfaces()).contains(IAnnotationValueResolver.class);
    }
}
