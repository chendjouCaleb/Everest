package org.everest.mvc.variableResolver.decorator;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@HandlerBy(handler = VariableResolverHandler.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface VariableResolver {
    String value() default "";
}
