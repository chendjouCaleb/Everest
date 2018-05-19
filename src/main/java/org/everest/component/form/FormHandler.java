package org.everest.component.form;

import org.apache.bval.jsr.ApacheValidationProvider;
import org.apache.commons.beanutils.BeanUtils;
import org.everest.component.form.exception.FormHandlerException;
import org.everest.exception.FormValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FormHandler<T> {
    private Map<String, Object> properties;
    private T model;
    private T initialModel;
    private Map<String, String> errors = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(FormHandler.class);

    public FormHandler(Map<String, Object> properties, T model) {
        this.properties = properties;
        this.model = model;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    private void handleModel(){
        try {
            BeanUtils.populate(model, properties);
        } catch (Exception e) {
            throw new FormHandlerException(e);
        }
    }
    private void cloneModel(){
        try {
            initialModel = (T) BeanUtils.cloneBean(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void handle(){
        cloneModel();
        handleModel();
        logger.info("Form Values: " + properties);
        logger.info("Form Model: " + model);
    }

    public void resetModel(){
        try {
            model = (T) BeanUtils.cloneBean(initialModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean isValid(){
        doValidation();
        return errors.isEmpty();
    }

    public void validate(){
        if(!isValid()){
            throw new FormValidationException("This form is not valid");
        }
    }

    public void doValidation(){
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

    public T getModel() {
        return model;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void addError(String key, String message) {
        this.errors.put(key, message);
    }
}
