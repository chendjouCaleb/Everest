package org.everest.mvc;

public class FilterExecutionException extends RuntimeException {
    public FilterExecutionException() {
    }

    public FilterExecutionException(String message) {
        super(message);
    }

    public FilterExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public FilterExecutionException(Throwable cause) {
        super(cause);
    }

    public FilterExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
