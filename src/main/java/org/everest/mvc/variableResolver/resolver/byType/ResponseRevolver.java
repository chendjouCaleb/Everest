package org.everest.mvc.variableResolver.resolver.byType;

import Everest.Http.HttpContext;
import Everest.Http.HttpResponse;
import org.everest.mvc.variableResolver.IVariableResolverByType;

import java.lang.reflect.Parameter;

public class ResponseRevolver implements IVariableResolverByType<HttpResponse> {
    @Override
    public Class<HttpResponse> getType() {
        return HttpResponse.class;
    }

    @Override
    public HttpResponse getValue(HttpContext httpContext, Parameter parameter) {
        return httpContext.getResponse();
    }
}
