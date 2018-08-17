package org.everest.mvc.actionResultHandler;

import org.everest.mvc.http.ResponseEntity;
import org.everest.mvc.httpContext.HttpContext;

public class ResponseEntityResultHandler implements IResultHandler<ResponseEntity> {

    private ResponseBodyHandler bodyHandler = new ResponseBodyHandler();

    public void handleResponse(HttpContext context, ResponseEntity result) {
        String body = bodyHandler.handleBody(context, result.getBody());
        bodyHandler.sendResponse(context, body, result.getHttpStatus().value());
    }

    @Override
    public Class<?> getType() {
        return ResponseEntity.class;
    }
}
