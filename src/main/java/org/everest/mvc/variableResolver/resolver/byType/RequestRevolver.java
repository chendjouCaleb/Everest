package org.everest.mvc.variableResolver.resolver.byType;

import Everest.Http.HttpContext;
import Everest.Http.HttpRequest;
import org.everest.mvc.variableResolver.IVariableResolverByType;

import java.lang.reflect.Parameter;

public class RequestRevolver implements IVariableResolverByType<HttpRequest> {
    @Override
    public Class<HttpRequest> getType() {
        return HttpRequest.class;
    }

    @Override
    public HttpRequest getValue(HttpContext context, Parameter parameter) {
        return context.getRequest();
    }
}
