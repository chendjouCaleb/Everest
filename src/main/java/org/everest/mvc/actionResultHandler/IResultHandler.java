package org.everest.mvc.actionResultHandler;

import org.everest.mvc.httpContext.HttpContext;

public interface IResultHandler<T> {
    void handleResponse(HttpContext context, T result);
    Class<?> getType();
}
