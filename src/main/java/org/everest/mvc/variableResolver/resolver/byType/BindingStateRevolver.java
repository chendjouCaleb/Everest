package org.everest.mvc.variableResolver.resolver.byType;

import Everest.Http.HttpContext;
import org.everest.mvc.binding.BindingState;
import org.everest.mvc.variableResolver.IVariableResolverByType;

import java.lang.reflect.Parameter;

public class BindingStateRevolver implements IVariableResolverByType<BindingState> {

    public Class<BindingState> getType() {
        return BindingState.class;
    }

    public BindingState getValue(HttpContext httpContext, Parameter parameter) {

        return httpContext.getBindingState();
    }


}
