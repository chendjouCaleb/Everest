package org.everest.component.form;

import org.apache.bval.jsr.ApacheValidationProvider;
import org.everest.exception.FormValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ModelValidator {
    private Map<String, String> errors = new HashMap<>();

    public void validate(Object model){
        if(!isValid(model)){
            throw new FormValidationException("This form is not valid");
        }
    }

    public boolean isValid(Object model){
        doValidation(model);
        return errors.isEmpty();
    }

    public void doValidation(Object model){
        ValidatorFactory validatorFactory = Validation
                .byProvider(ApacheValidationProvider.class)
                .configure().buildValidatorFactory();

        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> er= validator.validate(model);
        for (ConstraintViolation violation: er){
            String key = violation.getPropertyPath().toString().equals("")
                    ? "model":violation.getPropertyPath().toString();
            errors.put(key, violation.getMessage());
        }
        validatorFactory.close();
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void addError(String key, String message) {
        errors.put(key, message);
    }
}
