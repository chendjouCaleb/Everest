package filter;

import annotation.FilterBy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@FilterBy(filter = DebugFilter.class)
public @interface Debug {
    int priority() default 0;
}
