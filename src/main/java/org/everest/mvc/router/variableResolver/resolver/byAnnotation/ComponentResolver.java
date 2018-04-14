package org.everest.mvc.router.variableResolver.resolver.byAnnotation;

import org.everest.main.StaticContext;
import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.router.Route;
import org.everest.mvc.router.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.router.variableResolver.decorator.Component;

import java.lang.reflect.Parameter;

public class ComponentResolver implements IVariableResolverByAnnotation<Component> {
    @Override
    public Object getVariable(Request request, Response response, Route route, Parameter parameter,Component annotation) {
        return StaticContext.context.getInstance(annotation.value());
    }
}
