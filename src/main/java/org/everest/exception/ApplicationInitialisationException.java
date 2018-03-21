package org.everest.exception;

public class ApplicationInitialisationException extends EverestException {
    public ApplicationInitialisationException() {
    }

    public ApplicationInitialisationException(String message) {
        super(message);
    }

    public ApplicationInitialisationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationInitialisationException(Throwable cause) {
        super(cause);
    }

    public ApplicationInitialisationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
