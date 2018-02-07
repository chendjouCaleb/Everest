package org.everest.main.form.validator;

import org.joda.time.DateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CompareValidator implements ConstraintValidator<Compare, DateTime> {
    String value;
    boolean ignoreCase;
    @Override
    public void initialize(Compare compare) {
        //value = compare.value();
    }

    @Override
    public boolean isValid(DateTime date, ConstraintValidatorContext constraintValidatorContext) {
        DateTime now = DateTime.now();
        return  date.isAfter(now.toInstant());
    }
}
