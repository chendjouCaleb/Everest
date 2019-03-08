package org.everest.mvc.variableResolver.resolver.byType;

import Everest.Http.HttpContext;
import Everest.Http.Session;
import org.everest.mvc.variableResolver.IVariableResolverByType;

import java.lang.reflect.Parameter;

public class SessionRevolver implements IVariableResolverByType<Session> {
    @Override
    public Class<Session> getType() {
        return Session.class;
    }

    @Override
    public Session getValue(HttpContext context, Parameter parameter) {
        return context.getSession();
    }
}
