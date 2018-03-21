package org.everest.mvc.router.variableResolver.decorator;

import org.everest.mvc.router.variableResolver.resolver.HttpRequestResolver;
import org.everest.mvc.router.variableResolver.resolver.PathVariableResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ResolvedBy(HttpRequestResolver.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface HttpRequest {
}
