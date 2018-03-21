package org.everest.mvc.router.variableResolver.decorator;

import org.everest.mvc.router.variableResolver.resolver.PathVariableResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ResolvedBy(PathVariableResolver.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface PathVariable {
    String value();
}
