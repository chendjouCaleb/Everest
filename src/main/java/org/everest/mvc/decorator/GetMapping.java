package org.everest.mvc.decorator;

import org.everest.mvc.httpContext.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@HttpMapping(verbs = HttpMethod.GET)
public @interface GetMapping {
    String value() default "";
    String name() default "";
}
