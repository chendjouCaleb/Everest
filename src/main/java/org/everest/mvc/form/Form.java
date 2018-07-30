package org.everest.mvc.form;

import org.apache.bval.jsr.ApacheValidationProvider;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.everest.exception.FormValidationException;
import org.everest.mvc.form.annotation.NotMapped;
import org.everest.mvc.httpContext.Request;
import org.everest.mvc.form.converter.BooleanConverter;
import org.joda.time.DateTime;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Field;
import java.util.*;

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
        addConverter();
        this.model = _model;
        this.request = _request;
        this.errors = new HashMap<>();
        setProperties(_model.getClass());
    }
public Form(){}
    public Form(Object _model, Map<String, Object> _properties){
        addConverter();
        this.model = _model;
        this.properties = _properties;
    }
    private void addConverter(){
        BooleanConverter.DateConverter converter = new BooleanConverter.DateConverter();
        ConvertUtils.register(converter, DateTime.class);
        BooleanConverter booleanConverter = new BooleanConverter();
        BooleanConverter.DateTimeConverter timeConverter = new BooleanConverter.DateTimeConverter();

        ConvertUtils.register(booleanConverter, Boolean.class);
        ConvertUtils.register(new BooleanConverter.IntegerConverter(), Integer.class);
        //ConvertUtils.register(timeConverter, DateTime.class);
    }

    public void addConverter(Converter converter, Class target){
        ConvertUtils.register(converter, target);
    }
    public void addConverters(Map<Converter, Class> converters){
        converters.forEach(ConvertUtils::register);
    }

    private List<String> getParamsName(Class clazz){
        List<String> paramsName = new ArrayList<>();

        for (Field field: clazz.getDeclaredFields()){
            field.setAccessible(true);
            NotMapped notMapped = field.getAnnotation(NotMapped.class);
            if(notMapped == null){
                paramsName.add(field.getName());
            }
        }
        return paramsName;
    }

    private void setProperties(Class clazz){
        this.properties = new HashMap<>();
        List<String> parameterNames= getParamsName(clazz);

        parameterNames.forEach(param -> {
            if(request.getServletRequest().getParameter(param) != null && request.getServletRequest().getParameterValues(param).length > 1){
                properties.put(param, request.getServletRequest().getParameterValues(param));
            }else{
                properties.put(param, request.getServletRequest().getParameter(param));
            }
        });
    }

    public Object getModel() {
        try {
            System.out.println(properties.toString());
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

    public void validate(){
        if(!isValid()){
            throw new FormValidationException("This form is not valid");
        }
    }
    private void doValidation(){
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
        doValidation();
        request.addAttribute("form", this);
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
