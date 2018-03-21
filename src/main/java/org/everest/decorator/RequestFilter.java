package org.everest.decorator;

import org.everest.main.component.classHandler.RequestFilterHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@HandlerBy(handler = RequestFilterHandler.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RequestFilter {
    String value() default "";
}
