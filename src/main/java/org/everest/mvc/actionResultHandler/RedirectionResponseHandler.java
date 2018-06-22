package org.everest.mvc.actionResultHandler;

import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.result.Redirection;

public class RedirectionResponseHandler implements IResultHandler<Redirection> {

    @Override
    public void handleResponse(HttpContext httpContext, Redirection result) {
        httpContext.getResponse().redirect(result.getUrl());
    }

    @Override
    public Class<?> getType() {
        return Redirection.class;
    }
}
