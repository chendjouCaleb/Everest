package org.everest.mvc.router.variableResolver.resolver.byAnnotation;

import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.router.Route;
import org.everest.mvc.router.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.router.variableResolver.decorator.HttpRequest;

import java.lang.reflect.Parameter;

public class HttpRequestResolver implements IVariableResolverByAnnotation<HttpRequest> {
    @Override
    public Object getVariable(Request request, Response response, Route route, Parameter parameter, HttpRequest annotation) {
        return request;
    }
}
