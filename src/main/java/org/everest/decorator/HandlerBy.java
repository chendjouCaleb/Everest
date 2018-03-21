package org.everest.decorator;

import org.everest.context.classHandler.ClassHandler;
import org.everest.context.classHandler.InstanceHandler;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

@Target(ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HandlerBy {
    Class <? extends ClassHandler> handler();
}
