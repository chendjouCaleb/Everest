package org.everest.mvc.variableResolver.resolver.byType;

import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.httpContext.Response;
import org.everest.mvc.variableResolver.IVariableResolverByType;

import java.lang.reflect.Parameter;

public class ResponseRevolver implements IVariableResolverByType<Response> {
    @Override
    public Class<Response> getType() {
        return Response.class;
    }

    @Override
    public Response getValue(HttpContext httpContext, Parameter parameter) {
        return httpContext.getResponse();
    }
}
