package org.everest.mvc.component.responseResolver;

import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.action.Redirection;

public class RedirectionResponseResolver implements IResponseResolver<Redirection>{

    @Override
    public void handleResponse(Request request, Response response, Redirection result) {
        response.redirect(result.getUrl());
    }

    @Override
    public Class<?> getType() {
        return Redirection.class;
    }
}
