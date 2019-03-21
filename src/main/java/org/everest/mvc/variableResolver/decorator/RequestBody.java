package org.everest.mvc.variableResolver.decorator;

import Everest.Mvc.ValueResolver.AnnotationResolver.RequestBodyResolverValueResolver;
import Everest.Mvc.ValueResolver.ValueResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @deprecated Use {@link Everest.Mvc.ValueResolver.Annotations.BodyValue} instead.
 */
@ValueResolver(RequestBodyResolverValueResolver.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface RequestBody {
    String value() default "";
    boolean validate() default false;
}
