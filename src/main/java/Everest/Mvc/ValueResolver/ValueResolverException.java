package Everest.Mvc.ValueResolver;

public class ValueResolverException extends RuntimeException{
    public ValueResolverException() {
    }

    public ValueResolverException(String message) {
        super(message);
    }

    public ValueResolverException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValueResolverException(Throwable cause) {
        super(cause);
    }

    public ValueResolverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
