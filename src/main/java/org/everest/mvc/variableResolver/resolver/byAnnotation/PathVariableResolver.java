package org.everest.mvc.variableResolver.resolver.byAnnotation;

import org.apache.commons.beanutils.ConvertUtils;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.variableResolver.decorator.PathVariable;

import java.lang.reflect.Parameter;

public class PathVariableResolver implements IVariableResolverByAnnotation<PathVariable> {
    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, PathVariable path) {
        int i = Integer.valueOf(path.value().substring(1));
        Object o = httpContext.getRoute().getParameters()[i];
        return ConvertUtils.convert(o, parameter.getType());
    }
}
