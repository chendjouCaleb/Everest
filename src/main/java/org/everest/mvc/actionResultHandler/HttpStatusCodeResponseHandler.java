package org.everest.mvc.actionResultHandler;

import org.everest.exception.OperationException;
import org.everest.mvc.http.HttpStatus;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.infrastructure.StaticContext;
import org.everest.mvc.result.JSON;
import org.everest.mvc.service.JSONService;

import java.io.IOException;

public class HttpStatusCodeResponseHandler implements IResultHandler<HttpStatus> {

    public void handleResponse(HttpContext context, HttpStatus result) {
        context.getResponse().getServletResponse().setStatus(result.value());
        try {
            context.getResponse().getServletResponse().getWriter();
        } catch (IOException e) {
            throw new OperationException(e);
        }
    }

    @Override
    public Class<?> getType() {
        return HttpStatus.class;
    }
}
