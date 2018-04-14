package org.everest.mvc.router.variableResolver.resolver.byType;

import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.main.component.http.Session;
import org.everest.mvc.router.Route;
import org.everest.mvc.router.variableResolver.IVariableResolverByType;

import java.lang.reflect.Parameter;

public class SessionRevolver implements IVariableResolverByType<Session> {
    @Override
    public Class<Session> getType() {
        return Session.class;
    }

    @Override
    public Session getValue(Request request, Response response, Route route, Parameter parameter) {
        return request.getSession();
    }
}
