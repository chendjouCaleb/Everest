package org.everest.mvc.router.variableResolver.decorator;


import org.everest.mvc.router.variableResolver.resolver.byAnnotation.FormHandlerVariableResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ResolvedBy(FormHandlerVariableResolver.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Form {
    Class value();
}
