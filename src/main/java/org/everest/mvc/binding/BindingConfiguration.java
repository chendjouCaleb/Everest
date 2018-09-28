package org.everest.mvc.binding;

import org.everest.core.dic.decorator.Bean;
import org.everest.decorator.Instance;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorFactoryImpl;

import javax.validation.ConstraintValidatorFactory;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

@Instance
public class BindingConfiguration {

    @Bean
    public ValidatorFactory validatorFactory(ConstraintValidatorFactory constraintValidatorFactory){



        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .constraintValidatorFactory(constraintValidatorFactory)
                .buildValidatorFactory();
        return validatorFactory;
    }

}
