package Everest.Mvc.ExceptionHandler;

import org.everest.mvc.binding.ObjectValidationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationErrorModel extends ErrorResponseModel{
    private Map<String, String> fieldErrors = new HashMap<>();
    private List<String> objectErrors = new ArrayList<>();


    public ValidationErrorModel(ObjectValidationException e) {
        super(e);
        setFieldErrors(e.getFieldErrors());
        setObjectErrors(e.getObjectErrors());
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public List<String> getObjectErrors() {
        return objectErrors;
    }

    public void setObjectErrors(List<String> objectErrors) {
        this.objectErrors = objectErrors;
    }

    @Override
    public String toString() {
        return "ValidationErrorModel{" +
                "fieldErrors=" + fieldErrors +
                ", objectErrors=" + objectErrors +
                '}';
    }
}
