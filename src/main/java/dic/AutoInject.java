package dic;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD, ElementType.METHOD})
public @interface AutoInject {
    Class target() default Object.class;
    boolean newInstance() default false;
}
