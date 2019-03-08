package org.everest.mvc.variableResolver.resolver.byAnnotation;

import Everest.Http.HttpContext;
import org.everest.mvc.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.variableResolver.decorator.SessionAttribute;

import java.lang.reflect.Parameter;

public class SessionAttributeResolver implements IVariableResolverByAnnotation<SessionAttribute> {
    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, SessionAttribute annotation) {
        String name = annotation.value();

        return httpContext.getSession().getAttribute(name);
    }
}
