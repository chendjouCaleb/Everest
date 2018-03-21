package org.everest.exception;

public class InstanceNotFoundException extends RuntimeException{
    public InstanceNotFoundException() {
    }

    public InstanceNotFoundException(String message) {
        super(message);
    }

    public InstanceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InstanceNotFoundException(Throwable cause) {
        super(cause);
    }

    public InstanceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
