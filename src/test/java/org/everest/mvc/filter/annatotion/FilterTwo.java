package org.everest.mvc.filter.annatotion;

import org.everest.mvc.filter.method.FilterTwoMethod;
import org.everest.mvc.httpContext.decorator.FilterBy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@FilterBy(filter = FilterTwoMethod.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface FilterTwo {
}
