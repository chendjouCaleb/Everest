package org.everest.core.dic.exception;

public class ContainerInitializationException extends RuntimeException {
    public ContainerInitializationException() {
    }

    public ContainerInitializationException(String message) {
        super(message);
    }

    public ContainerInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContainerInitializationException(Throwable cause) {
        super(cause);
    }

    public ContainerInitializationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
