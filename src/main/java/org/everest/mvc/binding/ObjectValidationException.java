package org.everest.mvc.binding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectValidationException extends IllegalStateException {
    private Map<String, String> fieldErrors = new HashMap<>();
    private List<String> objectErrors = new ArrayList<>();

    public ObjectValidationException() {
    }

    public ObjectValidationException(String s, Map<String, String> fieldErrors) {
        super(s);
        this.fieldErrors = fieldErrors;
    }
    public ObjectValidationException(String s, List<String> objectErrors) {
        super(s);
        this.objectErrors = objectErrors;
    }

    public ObjectValidationException(String s, Map<String, String> fieldErrors, List<String> objectErrors) {
        super(s);
        this.fieldErrors = fieldErrors;
        this.objectErrors = objectErrors;
    }

    public ObjectValidationException(String message) {
        super(message);
    }

    public ObjectValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectValidationException(Throwable cause) {
        super(cause);
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public List<String> getObjectErrors() {
        return objectErrors;
    }
}
