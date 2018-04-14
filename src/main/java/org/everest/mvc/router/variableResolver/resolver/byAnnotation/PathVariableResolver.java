package org.everest.mvc.router.variableResolver.resolver.byAnnotation;

import org.apache.commons.beanutils.ConvertUtils;
import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.router.Route;
import org.everest.mvc.router.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.router.variableResolver.decorator.PathVariable;

import java.lang.reflect.Parameter;

public class PathVariableResolver implements IVariableResolverByAnnotation<PathVariable> {
    @Override
    public Object getVariable(Request request, Response response, Route route, Parameter parameter, PathVariable path) {
        int i = Integer.valueOf(path.value().substring(1));
        Object o = route.getParameters()[i];
        return ConvertUtils.convert(o, parameter.getType());
    }
}
