package org.everest.main.component.http.response;

import org.everest.exception.RendererException;
import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;


public class TemplateResponseHandler implements StringResponseHandler{
    @Override
    public String getPrefix() {
        return "render";
    }

    @Override
    public void handleResponse(String result, Request request, Response response) {
        try {
            response.twigRender(request, result);
        } catch (Exception e) {
            throw new RendererException(e);
        }
    }
}
