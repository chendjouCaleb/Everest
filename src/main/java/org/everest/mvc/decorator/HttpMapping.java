package org.everest.mvc.decorator;

import Everest.Http.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface HttpMapping {
    String value() default "";
    HttpMethod verbs() default HttpMethod.GET;
    String name() default "";
}
