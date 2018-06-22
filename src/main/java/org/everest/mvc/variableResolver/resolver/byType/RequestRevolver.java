package org.everest.mvc.variableResolver.resolver.byType;

import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.httpContext.Request;
import org.everest.mvc.variableResolver.IVariableResolverByType;

import java.lang.reflect.Parameter;

public class RequestRevolver implements IVariableResolverByType<Request> {
    @Override
    public Class<Request> getType() {
        return Request.class;
    }

    @Override
    public Request getValue(HttpContext context, Parameter parameter) {
        return context.getRequest();
    }
}
