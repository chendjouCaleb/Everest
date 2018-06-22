package org.everest.mvc.variableResolver.resolver.byAnnotation;

import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.variableResolver.decorator.HttpResponse;

import java.lang.reflect.Parameter;

public class HttpResponseResolver implements IVariableResolverByAnnotation<HttpResponse> {
    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter,HttpResponse a) {
        return httpContext.getResponse();
    }
}
