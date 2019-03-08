package org.everest.mvc.variableResolver.resolver.byType;

import Everest.Http.HttpContext;
import org.everest.mvc.model.Model;
import org.everest.mvc.variableResolver.IVariableResolverByType;

import java.lang.reflect.Parameter;

public class ModelRevolver implements IVariableResolverByType<Model> {
    @Override
    public Class<Model> getType() {
        return Model.class;
    }

    @Override
    public Model getValue(HttpContext httpContext, Parameter parameter) {
        Model model = httpContext.getModel();
        return model;
    }


}
