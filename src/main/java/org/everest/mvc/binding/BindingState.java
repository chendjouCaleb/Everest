package org.everest.mvc.binding;

import org.everest.mvc.model.ModelValidationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BindingState {
    private Map<String, String> fieldErrors = new HashMap<>();

    private List<String> objectErrors = new ArrayList<>();

    private Object object;

    public boolean isValid() {
        return getErrors().isEmpty();
    }

    public Map<String, String> getErrors() {
        Map<String, String> errors = new HashMap<>();
        errors.putAll(fieldErrors);
        objectErrors.forEach(message -> errors.put("", message));
        return errors;
    }

    public BindingState(Object o){
        this.object = o;
        validate();
    }

    public void addError(String key, String message){
        fieldErrors.put(key, message);
    }
    public void addError(String message){objectErrors.add(message);}

    public void tryValidate(){

    }

    public void validate(){

    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public void setObjectErrors(List<String> objectErrors) {
        this.objectErrors = objectErrors;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
