package org.everest.mvc.variableResolver.resolver.byAnnotation;

import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.variableResolver.decorator.SessionAttribute;

import java.lang.reflect.Parameter;

public class SessionAttributeResolver implements IVariableResolverByAnnotation<SessionAttribute> {
    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, SessionAttribute annotation) {
        return httpContext.getSession().getAttribute(annotation.value());
    }
}
