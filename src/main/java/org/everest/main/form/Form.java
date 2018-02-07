package org.everest.main.form;

import org.apache.bval.jsr.ApacheValidationProvider;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.everest.main.component.http.Request;
import org.joda.time.DateTime;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Form {
    private Object model;
    private Map<String, Object> properties;
    private Request request;
    private Map<String, String> errors;
    private String messageError;

    public Form(Request _request){
        this.request = _request;
    }
    public Form(Object _model, Request _request){
        init(_model, _request);
    }
    public void init(Object _model, Request _request){
        addDateConverter();
        this.model = _model;
        this.request = _request;
        this.errors = new HashMap<>();
        setProperties();
    }
public Form(){}
    public Form(Object _model, Map<String, Object> _properties){
        addDateConverter();
        this.model = _model;
        this.properties = _properties;
    }
    private void addDateConverter(){
        DateConverter converter = new DateConverter();
        DateTimeConverter timeConverter = new DateTimeConverter();
        ConvertUtils.register(converter, DateTime.class);
        //ConvertUtils.register(timeConverter, DateTime.class);
    }

    private void setProperties(){
        this.properties = new HashMap<>();
        Enumeration<String> parameterNames= request.getServletRequest().getParameterNames();
        while (parameterNames.hasMoreElements()){
            String param = parameterNames.nextElement();
            if(request.getServletRequest().getParameterValues(param).length > 1){
                properties.put(param, request.getServletRequest().getParameterValues(param));
            }else{
                properties.put(param, request.getServletRequest().getParameter(param));
            }

        }
    }

    public Object getModel() {
        try {
            BeanUtils.populate(model, properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  model;
    }
    public void clearError(){
        this.errors = new HashMap<>();
        this.messageError = null;
    }
    public boolean isValid(){

        this.handle();
        if (!errors.isEmpty()){
            setMessageError("Veuillez remplir correctement le formulaire.");
        }
        System.out.println("Validité du formulaire: " + errors.isEmpty());
        System.out.println("Erros: " + errors);
        return errors.isEmpty();
    }

    private void validate(){
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

    public void handle(){
        getModel();
        validate();
        request.setAttr("form", this);
        request.setAttr("model", model);
    }
    public Form handle(Request request, Class entityClass){
        this.model = null;
        Object model = null;
        try {
            model = entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        init(model, request);
        return this;
    }

    public Map<String, String> getMultipleValues(String namePrefix){
        Map<String, String> values = new HashMap<>();
        Enumeration<String> parameterNames= request.getServletRequest().getParameterNames();

        while (parameterNames.hasMoreElements()){
            String param = parameterNames.nextElement();
            if(param.startsWith(namePrefix)){
                String name = param.replaceFirst(namePrefix, "");
                values.put(name, request.getServletRequest().getParameter(param));
            }
        }

        return values;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void addError(String key, String message){
        errors.put(key, message);
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String message) {
        this.messageError = message;
    }
}