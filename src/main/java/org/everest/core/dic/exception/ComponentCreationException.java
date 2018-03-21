package org.everest.core.dic.exception;

public class ComponentCreationException extends RuntimeException{
    public ComponentCreationException(String message) {
        super(message);
    }

    public ComponentCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComponentCreationException(Throwable cause) {
        super(cause);
    }

    public ComponentCreationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
