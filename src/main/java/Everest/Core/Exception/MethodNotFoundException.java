package Everest.Core.Exception;

/**
 * The non verifiable exception for wrap the original {@link NoSuchMethodException}
 */
public class MethodNotFoundException extends RuntimeException {
    private NoSuchMethodException originalException;

    public MethodNotFoundException(NoSuchMethodException originalException) {
        this.originalException = originalException;
    }

    public MethodNotFoundException(String message, NoSuchMethodException originalException) {
        super(message);
        this.originalException = originalException;
    }

    public MethodNotFoundException(String message, Throwable cause, NoSuchMethodException originalException) {
        super(message, cause);
        this.originalException = originalException;
    }

    public MethodNotFoundException(Throwable cause, NoSuchMethodException originalException) {
        super(cause);
        this.originalException = originalException;
    }

    public MethodNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, NoSuchMethodException originalException) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.originalException = originalException;
    }

    public NoSuchMethodException getOriginalException() {
        return originalException;
    }
}
