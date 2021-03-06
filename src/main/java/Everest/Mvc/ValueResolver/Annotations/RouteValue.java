package Everest.Mvc.ValueResolver.Annotations;

import Everest.Mvc.ValueResolver.ValueResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation to inject route value in action method.
 */
@ValueResolver
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface RouteValue {

    /**
     * The name of the route value parameters.
     */
    String value() default "";
}
