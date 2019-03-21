package org.everest.mvc.classFilter;

import Everest.Mvc.ValueResolver.ITypedValueResolver;
import org.everest.core.dic.handler.ITypeFilter;

import java.util.Arrays;

public class TypedValueResolverClassFilter implements ITypeFilter {
    @Override
    public boolean isAdmissible(Class type) {

        return Arrays.asList(type.getInterfaces()).contains(ITypedValueResolver.class);
    }
}
