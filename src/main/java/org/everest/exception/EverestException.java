package org.everest.exception;

public class EverestException extends RuntimeException{
    public EverestException() {
    }

    public EverestException(String message) {
        super(message);
    }

    public EverestException(String message, Throwable cause) {
        super(message, cause);
    }

    public EverestException(Throwable cause) {
        super(cause);
    }

    public EverestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
