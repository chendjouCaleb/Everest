package org.everest.mvc.variableResolver.resolver.byAnnotation;

import org.everest.component.form.FormHandler;
import org.everest.component.form.FormService;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.infrastructure.StaticContext;
import org.everest.mvc.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.variableResolver.decorator.Form;
import org.everest.utils.ReflexionUtils;

import java.lang.reflect.Parameter;

public class FormHandlerVariableResolver implements IVariableResolverByAnnotation<Form> {
    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, Form form) {

        FormService formService = StaticContext.context.getInstance(FormService.class);
        FormHandler formHandler = formService.buildForm(httpContext.getRequest(), ReflexionUtils.instantiateClass(form.value()));
        formHandler.handle();
        httpContext.getModel().add(form.modelAttr(), formHandler.getModel());
        return formHandler;
    }
}
