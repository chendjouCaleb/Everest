package org.everest.mvc.variableResolver.resolver.byAnnotation;

import org.apache.commons.beanutils.ConvertUtils;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.variableResolver.decorator.ModelAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Parameter;

public class ModelAttributeVariableResolver implements IVariableResolverByAnnotation<ModelAttribute> {
    private Logger logger = LoggerFactory.getLogger(ModelAttributeVariableResolver.class);

    public Object getVariable(HttpContext httpContext, Parameter parameter, ModelAttribute modelAttribute) {

        String name = modelAttribute.value();
        Object o = httpContext.getModel().getObject(name, parameter.getType());
        return ConvertUtils.convert(o, parameter.getType());
    }
}
