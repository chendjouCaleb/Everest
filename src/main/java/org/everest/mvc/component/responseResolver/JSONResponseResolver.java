package org.everest.mvc.component.responseResolver;

import org.everest.main.StaticContext;
import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.action.JSON;
import org.everest.mvc.service.JSONService;

import java.io.IOException;

public class JSONResponseResolver implements IResponseResolver<JSON>{

    @Override
    public void handleResponse(Request request, Response response, JSON result) {
        JSONService jsonService = StaticContext.context.getInstance(JSONService.class);
        try {
            jsonService.sendJSON(response, result.getModel());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Class<?> getType() {
        return JSON.class;
    }
}
