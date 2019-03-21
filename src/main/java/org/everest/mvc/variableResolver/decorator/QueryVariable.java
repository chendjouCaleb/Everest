package org.everest.mvc.variableResolver.decorator;

import Everest.Mvc.ValueResolver.AnnotationResolver.QueryVariableResolverValueResolver;
import Everest.Mvc.ValueResolver.ValueResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @deprecated Use {@link Everest.Mvc.ValueResolver.Annotations.QueryValue} instead.
 */
@ValueResolver(QueryVariableResolverValueResolver.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface QueryVariable {
    String value();
}
