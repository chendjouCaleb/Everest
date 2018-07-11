package org.everest.mvc.variableResolver.resolver.byAnnotation;

import org.apache.commons.beanutils.ConvertUtils;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.variableResolver.decorator.QueryVariable;

import java.lang.reflect.Parameter;

public class QueryVariableResolver implements IVariableResolverByAnnotation<QueryVariable> {
    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, QueryVariable annotation) {
        Object stringVal;
        if(parameter.getType().isArray()){
            stringVal = httpContext.getRequest().getParams(annotation.value());
        }else {
            stringVal = httpContext.getRequest().getParam(annotation.value());
        }
        return ConvertUtils.convert(stringVal, parameter.getType());
    }
}
