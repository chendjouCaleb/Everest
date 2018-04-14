package org.everest.mvc.router.variableResolver.resolver.byAnnotation;

import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.router.Route;
import org.everest.mvc.router.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.router.variableResolver.decorator.HttpResponse;

import java.lang.reflect.Parameter;

public class HttpResponseResolver implements IVariableResolverByAnnotation<HttpResponse> {
    @Override
    public Object getVariable(Request request, Response response, Route r, Parameter p, HttpResponse a) {
        return response;
    }
}
