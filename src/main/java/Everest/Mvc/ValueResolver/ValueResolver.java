package Everest.Mvc.ValueResolver;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

@Target(ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValueResolver {
    Class<?> value() default Object.class;
}
