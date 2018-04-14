package org.everest.mvc.router.variableResolver.resolver.byType;

import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.router.Route;
import org.everest.mvc.router.variableResolver.IVariableResolverByType;

import java.lang.reflect.Parameter;

public class ResponseRevolver implements IVariableResolverByType<Response> {
    @Override
    public Class<Response> getType() {
        return Response.class;
    }

    @Override
    public Response getValue(Request request, Response response, Route route, Parameter parameter) {
        return response;
    }
}
