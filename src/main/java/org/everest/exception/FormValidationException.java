package org.everest.exception;

public class FormValidationException extends EverestException {
    public FormValidationException() {
    }

    public FormValidationException(String message) {
        super(message);
    }

    public FormValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormValidationException(Throwable cause) {
        super(cause);
    }

    public FormValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
