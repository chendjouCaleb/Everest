package org.everest.mvc.identity;

public class NotAuthorizedException extends RuntimeException {
    private String uriResource;

    public NotAuthorizedException() {
        super("Vous n'etes pas authorisé(é) à accéder à cette page");
    }

    public NotAuthorizedException(String uriResource) {
        super("Vous n'etes pas authorisé(é) à accéder à cette page");
        this.uriResource = uriResource;
    }

    public NotAuthorizedException(String uriResource, String message) {
        super(message);
        this.uriResource = uriResource;
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

    public String getUriResource() {
        return uriResource;
    }
}
