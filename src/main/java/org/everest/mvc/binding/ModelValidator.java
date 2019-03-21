package org.everest.mvc.binding;

import org.everest.decorator.Instance;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorFactoryImpl;
import org.hibernate.validator.internal.engine.constraintvalidation.HibernateConstraintValidatorInitializationContextImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Instance
public class ModelValidator implements IModelValidator {
    private Logger logger = LoggerFactory.getLogger(ModelValidator.class);
    private Validator validator;

    public ModelValidator(ValidatorFactory validatorFactory) {
        validator = validatorFactory.getValidator();
        logger.info("Validator provider: " + validator.getClass());
    }

    public BindingState validateModel(Object model) {
        BindingState state = new BindingState(model);

        Set<ConstraintViolation<Object>> er = validator.validate(model);
        for (ConstraintViolation violation : er) {
            String key = violation.getPropertyPath().toString();
            if (key.equals("")) {
                state.addError(violation.getMessage());
            } else {
                state.addError(key, violation.getMessage());
            }
        }

        return state;
    }

    @Override
    public Map<String, String> validate(Object model) {
        Map<String, String> errors = new HashMap<>();
        Set<ConstraintViolation<Object>> er = validator.validate(model);
        for (ConstraintViolation violation : er) {
            logger.info("Violation: {}", violation.getMessage());
            String key = violation.getPropertyPath().toString().equals("")
                    ? "model" : violation.getPropertyPath().toString();
            errors.put(key, violation.getMessage());
        }

        return errors;
    }


    public void validateByPattern(String value, String pattern, String errorMessage) {
        if(errorMessage == null){
            errorMessage = "La chaine " + value + " est incorrecte";
        }
        if (!value.matches(pattern)){
            throw new ObjectValidationException(errorMessage);
        }
    }

    @Override
    public BindingState validate(Object object, Method method, Object[] params) {
        BindingState state = new BindingState();

        Set<ConstraintViolation<Object>> er = validator.forExecutables().validateParameters(object, method, params);
        for (ConstraintViolation violation : er) {
            String key = violation.getPropertyPath().toString();
            if (key.equals("")) {
                state.addError(violation.getMessage());
            } else {
                state.addError(key, violation.getMessage());
            }
        }

        return state;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}
