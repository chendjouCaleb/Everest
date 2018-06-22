package org.everest.mvc.variableResolver.resolver.byAnnotation;

import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.variableResolver.decorator.HttpRequest;

import java.lang.reflect.Parameter;

public class HttpRequestResolver implements IVariableResolverByAnnotation<HttpRequest> {
    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, HttpRequest annotation) {
        return httpContext.getRequest();
    }
}
