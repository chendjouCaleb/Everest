package Everest.Core.Exception;

public class ReflexionException extends RuntimeException {
    public ReflexionException() {
    }

    public ReflexionException(String message) {
        super(message);
    }

    public ReflexionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReflexionException(Throwable cause) {
        super(cause);
    }

    public ReflexionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
