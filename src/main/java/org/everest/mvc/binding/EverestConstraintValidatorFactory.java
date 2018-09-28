package org.everest.mvc.binding;

import org.everest.core.dic.Container;
import org.everest.decorator.Instance;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorFactory;

@Instance
public class EverestConstraintValidatorFactory implements ConstraintValidatorFactory {
    private Container container;

    public EverestConstraintValidatorFactory(Container container) {
        this.container = container;
    }

    @Override
    public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> aClass) {
        if (aClass.getPackage().getName().startsWith("javax.validation") ||
                aClass.getPackage().getName().startsWith("org.hibernate.validator"))
        {
            try {
                // create standard validators by calling
                // default constructor
                return aClass.newInstance();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return (T) container.getRetrieverService().getByConcreteType(aClass).getInstance();
    }

    @Override
    public void releaseInstance(ConstraintValidator<?, ?> constraintValidator) {

    }
}
