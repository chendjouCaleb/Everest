package org.everest.mvc.form.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Constraint(validatedBy = CompareValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Compare {
    String message() default "Les deux valeurs ne correspondent pas";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    //Object value();

    boolean ignoreCase() default false;
}
