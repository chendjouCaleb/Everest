package org.everest.mvc.component;

import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;

public interface IResponseResolver<T> {
    void handleResponse(Request request, Response response, T result);
}
