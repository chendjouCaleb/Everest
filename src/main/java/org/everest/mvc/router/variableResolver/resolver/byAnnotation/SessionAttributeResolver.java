package org.everest.mvc.router.variableResolver.resolver.byAnnotation;

import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.router.Route;
import org.everest.mvc.router.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.router.variableResolver.decorator.SessionAttribute;

import java.lang.reflect.Parameter;

public class SessionAttributeResolver implements IVariableResolverByAnnotation<SessionAttribute> {
    @Override
    public Object getVariable(Request request, Response response, Route route, Parameter parameter, SessionAttribute annotation) {
        return request.getSession().getAttr(annotation.value());
    }
}
