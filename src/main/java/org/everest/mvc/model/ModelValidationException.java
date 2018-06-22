package org.everest.mvc.model;

import java.util.Map;

public class ModelValidationException extends RuntimeException {
    private Map<String, String> errors;
    public ModelValidationException() {
    }

    public ModelValidationException(Map<String, String> errors) {
        super("Error was found during the validation of a object");
        this.errors = errors;
    }

    public ModelValidationException(String message) {
        super(message);
    }

    public ModelValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModelValidationException(Throwable cause) {
        super(cause);
    }

    public ModelValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Map<String, String> getErrors() {
        return errors;
    }


}
