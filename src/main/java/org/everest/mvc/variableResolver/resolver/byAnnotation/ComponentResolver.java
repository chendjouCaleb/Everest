package org.everest.mvc.variableResolver.resolver.byAnnotation;

import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.infrastructure.StaticContext;
import org.everest.mvc.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.variableResolver.decorator.Component;

import java.lang.reflect.Parameter;

public class ComponentResolver implements IVariableResolverByAnnotation<Component> {
    @Override
    public Object getVariable(HttpContext context, Parameter parameter, Component annotation) {
        return StaticContext.context.getInstance(annotation.value());
    }
}
