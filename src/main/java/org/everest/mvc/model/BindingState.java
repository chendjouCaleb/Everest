package org.everest.mvc.model;

import java.util.HashMap;
import java.util.Map;

public class BindingState {
    private boolean valid;
    private Map<String, String> errors = new HashMap<>();

    public boolean isValid() {
        return errors.isEmpty();
    }

    public Map<String, String> getErrors() {
        return errors;
    }

//    public void addError(String key, Error error){
//        errors.put(key, error);
//    }
//
//    public void addError(Error error){
//        errors.put(error.getKey(), error);
//    }

    public void addError(String key, String message){
        errors.put(key, message);
    }
    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public void tryValidate(){
        if (!isValid()){
            throw new ModelValidationException(errors);
        }
    }
}
