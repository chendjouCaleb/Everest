package org.everest.mvc.classFilter;

import org.everest.core.dic.handler.ITypeFilter;
import org.everest.mvc.binding.IConverter;

import javax.validation.ConstraintValidator;
import java.util.Arrays;

public class ValidatorClassFilter implements ITypeFilter {
    @Override
    public boolean isAdmissible(Class type) {
        return Arrays.asList(type.getInterfaces()).contains(ConstraintValidator.class);
    }
}
