package org.everest.mvc.variableResolver.decorator;

import org.everest.mvc.variableResolver.IVariableResolverByAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

@Target(ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResolvedBy {
    Class<? extends IVariableResolverByAnnotation> value();
}
