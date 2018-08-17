package org.everest.mvc.service;

import org.everest.mvc.httpContext.HttpContext;

public interface IRequestBodyHandler {
    Object getBody(HttpContext httpContext, Class<?> type);
}
