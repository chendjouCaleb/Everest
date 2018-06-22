package org.everest.mvc.variableResolver.resolver.byAnnotation;

import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.variableResolver.decorator.HttpSession;

import java.lang.reflect.Parameter;

public class HttpSessionResolver implements IVariableResolverByAnnotation<HttpSession> {
    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, HttpSession annotation) {
        return httpContext.getSession();
    }
}
