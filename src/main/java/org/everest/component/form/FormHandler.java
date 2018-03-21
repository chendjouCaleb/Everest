package org.everest.component.form;

import com.google.common.base.Objects;
import org.apache.bval.jsr.ApacheValidationProvider;
import org.apache.commons.beanutils.BeanUtils;
import org.everest.component.form.exception.FormHandlerException;
import org.everest.core.dic.decorator.Bean;
import org.everest.exception.FormValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FormHandler<T> {
    private Map<String, Object> properties;
    private T model;
    private T initialModel;
    private Map<String, String> errors = new HashMap<>();

    public FormHandler(Map<String, Object> properties, T model) {
        this.properties = properties;
        this.model = model;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    private void handleModel(){
        try {
            System.out.println("Model: " + model);
            System.out.println("Properties: " + properties.toString());
            BeanUtils.populate(model, properties);
            System.out.println("Model: " + model);
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
        doValidation();
    }

    public void resetModel(){
        try {
            model = (T) BeanUtils.cloneBean(initialModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean isValid(){
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
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
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
