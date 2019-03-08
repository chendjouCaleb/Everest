package org.everest.mvc.variableResolver.resolver.byAnnotation;

import org.apache.commons.beanutils.ConvertUtils;
import Everest.Http.HttpContext;
import org.everest.mvc.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.variableResolver.decorator.RequestVariable;

import java.lang.reflect.Parameter;

public class HttpRequestVariableResolver implements IVariableResolverByAnnotation<RequestVariable> {
    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, RequestVariable annotation) {
        String name = annotation.value();

        String stringVal = httpContext.getRequest().forms().get(name).get(0);
        return ConvertUtils.convert(stringVal, parameter.getType());
    }
}
