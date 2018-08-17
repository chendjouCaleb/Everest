package org.everest.mvc.binding;

import java.util.Map;

public interface IModelValidator {
    BindingState validateModel(Object object);
    Map<String, String> validate(Object object);
}
