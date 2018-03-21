package org.everest.mvc.router.variableResolver.resolver;

import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.router.Route;
import org.everest.mvc.router.variableResolver.IVariableResolver;
import org.everest.mvc.router.variableResolver.decorator.HttpSession;

import java.lang.reflect.Parameter;

public class HttpSessionResolver implements IVariableResolver<HttpSession> {
    @Override
    public Object getVariable(Request request, Response response, Route route, Parameter parameter, HttpSession annotation) {
        return request.getSession();
    }
}
