package org.everest.mvc.binding;

import java.lang.reflect.Method;
import java.util.Map;

public interface IModelValidator {
    BindingState validateModel(Object object);
    Map<String, String> validate(Object object);
    void validateByPattern(String value, String pattern, String errorMessage);

    BindingState validate(Object object, Method method, Object[] params);
}
