package org.everest.component.form;

import org.apache.commons.beanutils.ConvertUtils;
import org.everest.component.form.converter.DateTimeConverter;
import org.everest.mvc.httpContext.Request;
import org.everest.mvc.form.annotation.NotMapped;
import org.joda.time.DateTime;

import java.lang.reflect.Field;
import java.util.*;


public class FormService {

    public FormService(){
        addConverter();
    }
    public<T> FormHandler<T> buildForm(Request request, T model){
        Set<String> paramNames = getParamsName(model.getClass());
        Map<String, Object> properties = getProperties(request, paramNames);
        FormHandler<T> formHandler = new  FormHandler<>(properties, model);
        request.addAttribute("form", formHandler);
        return formHandler;
    }


    private Set<String> getParamsName(Class clazz){
        Set<String> paramsName = new HashSet<>();

        for (Field field: clazz.getDeclaredFields()){
            field.setAccessible(true);
            NotMapped notMapped = field.getAnnotation(NotMapped.class);
            if(notMapped == null){
                paramsName.add(field.getName());
            }
        }
        return paramsName;
    }

    private Map<String, Object> getProperties(Request request, Set<String> parameterNames){
        Map<String, Object> properties = new HashMap<>();
        parameterNames.forEach(param -> {
            if(request.getServletRequest().getParameter(param) != null){
                if(request.getServletRequest().getParameterValues(param).length > 1){
                    properties.put(param, request.getServletRequest().getParameterValues(param));
                }else{
                    properties.put(param, request.getServletRequest().getParameter(param));
                }
            }

        });
        return properties;
    }

    public Map<String, String> getMultipleValues(Request request, String paramName, String sep){
        String namePrefix = paramName + sep;
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

    private void addConverter(){
        ConvertUtils.register(new DateTimeConverter(), DateTime.class);
    }
    public ModelValidator getModelValidator(){return new ModelValidator();}
}
