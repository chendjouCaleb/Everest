package org.everest.mvc.variableResolver.decorator;

import Everest.Mvc.ValueResolver.ValueResolver;
import Everest.Mvc.ValueResolver.AnnotationResolver.PathVariableResolverValueResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @deprecated Use {@link Everest.Mvc.ValueResolver.Annotations.RouteValue} instead.
 */
@Deprecated()
@ValueResolver(PathVariableResolverValueResolver.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface PathVariable {
    String value();
}
