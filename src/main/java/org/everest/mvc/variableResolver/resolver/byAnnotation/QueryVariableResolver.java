package org.everest.mvc.variableResolver.resolver.byAnnotation;

import Everest.Http.HttpContext;
import Everest.Http.HttpRequest;
import org.apache.commons.beanutils.ConvertUtils;
import org.everest.mvc.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.variableResolver.decorator.QueryVariable;

import java.lang.reflect.Parameter;

public class QueryVariableResolver implements IVariableResolverByAnnotation<QueryVariable> {
    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, QueryVariable annotation) {
        HttpRequest request = httpContext.getRequest();
        if(request.queryString() == null){
            return null;
        }
        String stringVal = null;
        String[] keyValues = request.queryString().toString().split("&");
        for(String keyValue: keyValues){
            if(keyValue.split("=")[0].equals(annotation.value())){
                stringVal = keyValue.split("=")[1];
            }
        }

        return ConvertUtils.convert(stringVal, parameter.getType());
    }
}
