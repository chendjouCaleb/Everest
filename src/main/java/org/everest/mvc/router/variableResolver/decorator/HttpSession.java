package org.everest.mvc.router.variableResolver.decorator;

import org.everest.mvc.router.variableResolver.resolver.HttpRequestResolver;
import org.everest.mvc.router.variableResolver.resolver.HttpSessionResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ResolvedBy(HttpSessionResolver.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface HttpSession {
}
