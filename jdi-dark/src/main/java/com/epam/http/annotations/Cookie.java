package com.epam.http.annotations;

import java.lang.annotation.*;

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
