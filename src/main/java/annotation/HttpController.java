package annotation;


import org.everest.decorator.HandlerBy;
import org.everest.main.component.classHandler.ControllerHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@HandlerBy(handler = ControllerHandler.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HttpController {
    String name() default "";
    String prefix() default "";
}
