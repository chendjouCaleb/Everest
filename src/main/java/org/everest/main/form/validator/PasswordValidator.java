package org.everest.main.form.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    private int length = 6;
    private char nonAlpha = 1;

    @Override
    public void initialize(Password password){
        length = password.length();
        nonAlpha = password.nonAlpha();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.length() >= length && !s.contains(String.valueOf(nonAlpha));
    }


}
