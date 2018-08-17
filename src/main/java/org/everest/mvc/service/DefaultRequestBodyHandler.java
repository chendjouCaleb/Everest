package org.everest.mvc.service;

import org.everest.mvc.binding.ModelBinder;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.infrastructure.StaticContext;

import java.util.Map;

public class DefaultRequestBodyHandler implements IRequestBodyHandler{
    @Override
    public Object getBody(HttpContext httpContext, Class<?> type) {
        ModelBinder modelBinder = StaticContext.context.getInstance(ModelBinder.class);
        Map<String, ?> map = httpContext.getRequest().getServletRequest().getParameterMap();
        return modelBinder.bind(type,map );
    }
}
