package org.everest.mvc.variableResolver;

public class VariableResolverException extends RuntimeException{
    public VariableResolverException() {
    }

    public VariableResolverException(String message) {
        super(message);
    }

    public VariableResolverException(String message, Throwable cause) {
        super(message, cause);
    }

    public VariableResolverException(Throwable cause) {
        super(cause);
    }

    public VariableResolverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
