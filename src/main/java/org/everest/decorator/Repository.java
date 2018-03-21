package org.everest.decorator;

import org.everest.context.classHandler.RepositoryHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@HandlerBy(handler = RepositoryHandler.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Repository {
    String value() default "";
}
