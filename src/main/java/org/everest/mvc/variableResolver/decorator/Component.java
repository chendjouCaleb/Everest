package org.everest.mvc.variableResolver.decorator;

import org.everest.mvc.variableResolver.resolver.byAnnotation.ComponentResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ResolvedBy(ComponentResolver.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Component {
    String value();
}
