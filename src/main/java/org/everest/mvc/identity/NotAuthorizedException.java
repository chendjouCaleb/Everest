package org.everest.mvc.identity;

public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException() {
        super("Vous n'etes pas authorisé(é) à accéder à cette page");
    }

    public NotAuthorizedException(String message) {
        super(message);
    }

    public NotAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAuthorizedException(Throwable cause) {
        super(cause);
    }

    public NotAuthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
