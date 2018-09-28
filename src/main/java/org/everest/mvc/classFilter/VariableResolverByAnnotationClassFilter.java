package org.everest.mvc.classFilter;

import org.everest.core.dic.handler.ITypeFilter;
import org.everest.mvc.binding.IConverter;
import org.everest.mvc.variableResolver.IVariableResolverByAnnotation;

import java.util.Arrays;

public class VariableResolverByAnnotationClassFilter implements ITypeFilter {
    @Override
    public boolean isAdmissible(Class type) {

        return Arrays.asList(type.getInterfaces()).contains(IVariableResolverByAnnotation.class);
    }
}
