package org.everest.mvc.router.variableResolver.resolver;

import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.router.Route;
import org.everest.mvc.router.variableResolver.IVariableResolver;
import org.everest.mvc.router.variableResolver.decorator.HttpResponse;

import java.lang.reflect.Parameter;

public class HttpResponseResolver implements IVariableResolver<HttpResponse>{
    @Override
    public Object getVariable(Request request, Response response, Route r, Parameter p, HttpResponse a) {
        return response;
    }
}
