package org.everest.decorator;

import org.everest.context.classHandler.ComponentHandler;
import org.everest.core.dic.enumeration.Scope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@HandlerBy(handler = ComponentHandler.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {
    String value() default "";
    Scope scope() default Scope.SINGLETION;
}
