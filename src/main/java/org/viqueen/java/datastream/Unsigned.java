package org.viqueen.java.datastream;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Unsigned {
    Type type() default Type.FOUR;

    enum Type {
        ONE,
        TWO,
        FOUR
    }
}
