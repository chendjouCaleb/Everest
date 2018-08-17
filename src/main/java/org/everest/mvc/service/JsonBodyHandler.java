package org.everest.mvc.service;

import org.everest.core.dic.decorator.AutoWired;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.infrastructure.StaticContext;

public class JsonBodyHandler implements IRequestBodyHandler {
    JSONService service = StaticContext.context.getInstance(JSONService.class);
    @Override
    public Object getBody(HttpContext httpContext, Class<?> type) {
        String value = httpContext.getRequest().getBodyString();
        return  service.toObject(value, type);
    }
}
