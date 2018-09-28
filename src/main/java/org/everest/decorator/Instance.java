package org.everest.decorator;

import org.everest.core.dic.enumeration.Scope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.METHOD})
public @interface Instance {
    String value() default "";
    Scope scope() default Scope.SINGLETON;
}
