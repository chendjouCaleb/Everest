package org.everest.mvc.http;

import Everest.Http.StatusCode;
import org.everest.mvc.result.ActionResult;

@Deprecated
public class ResponseEntity<T> extends ActionResult{
    private T body;
    private StatusCode statusCode;

    public ResponseEntity(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public ResponseEntity(T body, StatusCode statusCode) {
        this.body = body;
        this.statusCode = statusCode;
    }
    public static ResponseEntity status(StatusCode status){
        return new ResponseEntity(status);
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public StatusCode getHttpStatus() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
