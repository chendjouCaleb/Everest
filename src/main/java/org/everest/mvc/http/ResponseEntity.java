package org.everest.mvc.http;

import org.apache.commons.lang3.StringUtils;
import org.everest.mvc.result.ActionResult;

public class ResponseEntity<T> extends ActionResult{
    private T body;
    private HttpStatus httpStatus;

    public ResponseEntity(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public ResponseEntity(T body, HttpStatus httpStatus) {
        this.body = body;
        this.httpStatus = httpStatus;
    }
    public static ResponseEntity status(HttpStatus status){
        return new ResponseEntity(status);
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
