package org.everest.mvc.component;

import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.action.Redirection;
import org.everest.mvc.action.Render;

import javax.servlet.ServletException;
import java.io.IOException;

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
