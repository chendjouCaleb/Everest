package org.everest.main.form.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Constraint(validatedBy = FutureValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Future {
    String message() default "Cette date n'est pas une du future après le nombre de semaines spécifié";
    Class<?>[] groups() default {};

    int week() default 0;
    Class<? extends Payload>[] payload() default {};
}
