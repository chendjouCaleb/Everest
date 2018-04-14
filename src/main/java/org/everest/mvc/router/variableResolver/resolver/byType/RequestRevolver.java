package org.everest.mvc.router.variableResolver.resolver.byType;

import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.router.Route;
import org.everest.mvc.router.variableResolver.IVariableResolverByType;

import java.lang.reflect.Parameter;

public class RequestRevolver implements IVariableResolverByType<Request> {
    @Override
    public Class<Request> getType() {
        return Request.class;
    }

    @Override
    public Request getValue(Request request, Response response, Route route, Parameter parameter) {
        return request;
    }
}
