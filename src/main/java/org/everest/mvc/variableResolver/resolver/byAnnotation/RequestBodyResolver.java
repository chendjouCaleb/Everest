package org.everest.mvc.variableResolver.resolver.byAnnotation;

import org.everest.component.form.FormHandler;
import org.everest.component.form.FormService;
import org.everest.exception.FormValidationException;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.infrastructure.StaticContext;
import org.everest.mvc.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.variableResolver.decorator.RequestBody;
import org.everest.utils.ReflexionUtils;

import java.lang.reflect.Parameter;

public class RequestBodyResolver implements IVariableResolverByAnnotation<RequestBody> {
    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, RequestBody annotation) {
        FormService formService = StaticContext.context.getInstance(FormService.class);
        FormHandler formHandler = formService.buildForm(httpContext.getRequest(), ReflexionUtils.instantiateClass(parameter.getType()));

        formHandler.handle();
        Object obj = formHandler.getModel();

        if(!annotation.value().equals("")){
            httpContext.getModel().addData(annotation.value(), obj);
        }else {
            httpContext.getModel().addData(annotation.value(), obj.getClass().getSimpleName().toLowerCase());
        }
        httpContext.setRequestBody(obj);

        try{
            formHandler.validate();
            httpContext.getBindingState().setErrors(formHandler.getErrors());
        }catch (FormValidationException ignore){}


        return obj;
    }

}
