package org.everest.main.component.http;

public interface ErrorHandler<T> {
    Class getErrorType();
    void handleError(Request request, Response response, T exception);
}
