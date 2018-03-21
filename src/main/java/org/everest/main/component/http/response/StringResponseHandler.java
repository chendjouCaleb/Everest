package org.everest.main.component.http.response;

import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;

public interface StringResponseHandler {
    String getPrefix();
    void handleResponse(String result, Request request, Response response);
}
