package org.everest.mvc.actionResultHandler;

import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.infrastructure.StaticContext;
import org.everest.mvc.result.JSON;
import org.everest.mvc.service.JSONService;

import java.io.IOException;

public class JSONResponseHandler implements IResultHandler<JSON> {
    private JSONService jsonService;

    public JSONResponseHandler(JSONService jsonService) {
        this.jsonService = jsonService;
    }

    @Override
    public void handleResponse(HttpContext httpContext, JSON result) {
        try {
            httpContext.getResponse().getServletResponse().setStatus(result.getStatusCode());
            jsonService.sendJSON(httpContext.getResponse(), result.getModel());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Class<?> getType() {
        return JSON.class;
    }
}
