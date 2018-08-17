package org.everest.mvc.binding;

import org.everest.decorator.Instance;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorFactoryImpl;
import org.hibernate.validator.internal.engine.constraintvalidation.HibernateConstraintValidatorInitializationContextImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Instance
public class ModelValidator implements IModelValidator {
    private Logger logger = LoggerFactory.getLogger(ModelValidator.class);
    private Validator validator;

    public void initialize(ValidatorFactory validatorFactory) {
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

    @Instance
    ValidatorFactory validatorFactory(ConstraintValidatorFactory constraintValidatorFactory){

        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure().constraintValidatorFactory(constraintValidatorFactory)
                .buildValidatorFactory();
        return validatorFactory;
    }

    @Instance
    ConstraintValidatorFactory constraintValidatorFactory(){
        ConstraintValidatorFactoryImpl factory = new ConstraintValidatorFactoryImpl();
        return factory;
    }
}
