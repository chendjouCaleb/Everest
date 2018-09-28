package org.everest.mvc.variableResolver.resolver.byAnnotation;

import org.apache.commons.beanutils.ConvertUtils;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.variableResolver.decorator.QueryVariable;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Parameter;

public class QueryVariableResolver implements IVariableResolverByAnnotation<QueryVariable> {
    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, QueryVariable annotation) {
        HttpServletRequest request = httpContext.getRequest().getServletRequest();
        String stringVal = null;
        String[] keyValues = request.getQueryString().split("&");
        for(String keyValue: keyValues){
            if(keyValue.split("=")[0].equals(annotation.value())){
                stringVal = keyValue.split("=")[1];
            }
        }

        return ConvertUtils.convert(stringVal, parameter.getType());
    }
}
