package org.everest.mvc.router.variableResolver.resolver;

import org.apache.commons.beanutils.ConvertUtils;
import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.router.Route;
import org.everest.mvc.router.variableResolver.IVariableResolver;
import org.everest.mvc.router.variableResolver.decorator.QueryVariable;

import java.lang.reflect.Parameter;

public class QueryVariableResolver implements IVariableResolver<QueryVariable>{
    @Override
    public Object getVariable(Request request, Response response, Route route, Parameter parameter, QueryVariable annotation) {
        String stringVal = request.getParam(annotation.value());
        return ConvertUtils.convert(stringVal, parameter.getType());
    }
}
