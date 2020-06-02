package com.epam.http.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a Cookie.
 * An example of usage:
 * <pre>
 *     {@code @Cookie(name = "test", value = "test"}
 * </pre>
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Repeatable(Cookies.class)
public @interface Cookie {
    String name();

    String value() default "[unassigned]";

    String[] additionalValues() default "[unassigned]";
}
