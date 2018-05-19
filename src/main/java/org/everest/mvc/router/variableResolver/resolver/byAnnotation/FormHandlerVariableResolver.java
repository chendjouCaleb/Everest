package org.everest.mvc.router.variableResolver.resolver.byAnnotation;

import org.everest.component.form.FormHandler;
import org.everest.component.form.FormService;
import org.everest.main.StaticContext;
import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.router.Route;
import org.everest.mvc.router.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.router.variableResolver.decorator.Form;
import org.everest.utils.ReflexionUtils;

import java.lang.reflect.Parameter;

public class FormHandlerVariableResolver implements IVariableResolverByAnnotation<Form> {
    @Override
    public Object getVariable(Request request, Response response, Route route, Parameter parameter, Form form) {

        FormService formService = StaticContext.context.getInstance(FormService.class);
        FormHandler formHandler = formService.buildForm(request, ReflexionUtils.instantiateClass(form.value()));
        formHandler.handle();
        request.getModel().add(form.modelAttr(), formHandler.getModel());
        return formHandler;
    }
}
