package org.everest.context.exception;

public class ContextInstanceException extends RuntimeException {
    public ContextInstanceException() {
    }

    public ContextInstanceException(String message) {
        super(message);
    }

    public ContextInstanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContextInstanceException(Throwable cause) {
        super(cause);
    }

    public ContextInstanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
