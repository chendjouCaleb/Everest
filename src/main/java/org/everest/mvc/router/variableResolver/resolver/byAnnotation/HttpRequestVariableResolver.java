package org.everest.mvc.router.variableResolver.resolver.byAnnotation;

import org.apache.commons.beanutils.ConvertUtils;
import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.router.Route;
import org.everest.mvc.router.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.router.variableResolver.decorator.QueryVariable;
import org.everest.mvc.router.variableResolver.decorator.RequestVariable;

import java.lang.reflect.Parameter;

public class HttpRequestVariableResolver implements IVariableResolverByAnnotation<RequestVariable> {
    @Override
    public Object getVariable(Request request, Response response, Route route, Parameter parameter, RequestVariable annotation) {
        String stringVal = request.getParam(annotation.value());
        return ConvertUtils.convert(stringVal, parameter.getType());
    }
}
