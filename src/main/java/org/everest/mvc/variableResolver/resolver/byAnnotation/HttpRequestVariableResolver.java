package org.everest.mvc.variableResolver.resolver.byAnnotation;

import org.apache.commons.beanutils.ConvertUtils;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.variableResolver.decorator.RequestVariable;

import java.lang.reflect.Parameter;

public class HttpRequestVariableResolver implements IVariableResolverByAnnotation<RequestVariable> {
    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, RequestVariable annotation) {
        String stringVal = httpContext.getRequest().getParam(annotation.value());
        return ConvertUtils.convert(stringVal, parameter.getType());
    }
}
