package org.everest.mvc.variableResolver.decorator;

import org.everest.mvc.variableResolver.resolver.byAnnotation.SessionAttributeResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ResolvedBy(SessionAttributeResolver.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface SessionAttribute {
    String value();
}
