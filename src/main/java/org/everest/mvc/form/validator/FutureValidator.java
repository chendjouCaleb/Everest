package org.everest.mvc.form.validator;

import org.joda.time.DateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FutureValidator implements ConstraintValidator<Future, DateTime> {
    int week;
    @Override
    public void initialize(Future future) {
        week = future.week();
    }

    @Override
    public boolean isValid(DateTime date, ConstraintValidatorContext constraintValidatorContext) {
        DateTime now = DateTime.now();
        return  date != null && date.isAfter(now.plusWeeks(week).toInstant());
    }
}
