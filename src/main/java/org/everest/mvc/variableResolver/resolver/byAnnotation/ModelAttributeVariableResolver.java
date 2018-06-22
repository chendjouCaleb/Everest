package org.everest.mvc.variableResolver.resolver.byAnnotation;

import org.apache.commons.beanutils.ConvertUtils;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.variableResolver.decorator.ModelAttribute;

import java.lang.reflect.Parameter;

public class ModelAttributeVariableResolver implements IVariableResolverByAnnotation<ModelAttribute> {
    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, ModelAttribute modelAttribute) {

        Object o = httpContext.getModel().getObject(modelAttribute.value(), parameter.getType());
        return ConvertUtils.convert(o, parameter.getType());
    }
}
