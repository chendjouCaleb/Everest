package org.everest.mvc.renderer;

public class RendererException extends RuntimeException {

    public RendererException() {
        super();
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

    protected RendererException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
