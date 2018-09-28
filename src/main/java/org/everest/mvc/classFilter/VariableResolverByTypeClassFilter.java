package org.everest.mvc.classFilter;

import org.everest.core.dic.handler.ITypeFilter;
import org.everest.mvc.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.variableResolver.IVariableResolverByType;

import java.util.Arrays;

public class VariableResolverByTypeClassFilter implements ITypeFilter {
    @Override
    public boolean isAdmissible(Class type) {

        return Arrays.asList(type.getInterfaces()).contains(IVariableResolverByType.class);
    }
}
