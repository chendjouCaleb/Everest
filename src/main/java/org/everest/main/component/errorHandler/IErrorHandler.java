package org.everest.main.component.errorHandler;

import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;

public interface IErrorHandler<T extends Throwable> {
    Class getErrorType();
    void handleError(Request request, Response response, T exception);
}
