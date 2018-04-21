package org.everest.mvc.router.variableResolver.resolver.byType;

import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.model.Model;
import org.everest.mvc.router.Route;
import org.everest.mvc.router.variableResolver.IVariableResolverByType;

import java.lang.reflect.Parameter;
import java.util.List;

public class ModelRevolver implements IVariableResolverByType<Model> {
    @Override
    public Class<Model> getType() {
        return Model.class;
    }

    @Override
    public Model getValue(Request request, Response response, Route route, Parameter parameter) {
        Model model = new Model();
        request.setAttr("model", model.getObjects());
        List flashMessage = (List) request.getSession().getAttr("flashMessage");
        flashMessage.addAll(model.getFlashs());

        return model;
    }


}