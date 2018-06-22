package org.everest.mvc.errorHandler;

import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.httpContext.Request;
import org.everest.mvc.httpContext.Response;

public interface IErrorHandler<T extends Throwable> {
    Class<? extends T> getErrorType();
    void handleError(HttpContext httpContext, T exception);
}
