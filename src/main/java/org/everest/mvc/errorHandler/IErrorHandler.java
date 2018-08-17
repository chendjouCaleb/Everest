package org.everest.mvc.errorHandler;

import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.httpContext.Request;
import org.everest.mvc.httpContext.Response;
import org.everest.mvc.result.ActionResult;

public interface IErrorHandler<T extends Throwable> {
    Class<T> getErrorType();
    ActionResult handleError(HttpContext httpContext, T exception);
}
