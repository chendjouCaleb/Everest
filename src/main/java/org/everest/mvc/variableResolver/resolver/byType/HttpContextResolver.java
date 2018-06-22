package org.everest.mvc.variableResolver.resolver.byType;

import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.variableResolver.IVariableResolverByType;

import java.lang.reflect.Parameter;

public class HttpContextResolver implements IVariableResolverByType<HttpContext> {

    public Class<HttpContext> getType() {
        return HttpContext.class;
    }

    public HttpContext getValue(HttpContext httpContext, Parameter parameter) {
        return httpContext;

    }
}
