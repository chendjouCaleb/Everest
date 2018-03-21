package org.everest.component.form.exception;

import org.everest.exception.EverestException;

public class FormHandlerException extends EverestException{
    public FormHandlerException() {
    }

    public FormHandlerException(String message) {
        super(message);
    }

    public FormHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormHandlerException(Throwable cause) {
        super(cause);
    }

    public FormHandlerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
