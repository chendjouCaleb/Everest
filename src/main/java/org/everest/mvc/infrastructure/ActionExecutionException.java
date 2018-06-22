package org.everest.mvc.infrastructure;

public class ActionExecutionException extends RuntimeException {
    public ActionExecutionException() {
    }

    public ActionExecutionException(String message) {
        super(message);
    }

    public ActionExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionExecutionException(Throwable cause) {
        super(cause);
    }

    public ActionExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
