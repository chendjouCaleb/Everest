package org.everest.exception;

public class RendererException extends EverestException{
    public RendererException() {
    }

    public RendererException(String message) {
        super(message);
    }

    public RendererException(String message, Throwable cause) {
        super(message, cause);
    }

    public RendererException(Throwable cause) {
        super(cause);
    }

    public RendererException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
