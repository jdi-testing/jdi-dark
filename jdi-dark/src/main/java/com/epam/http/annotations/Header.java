package com.epam.http.annotations;

import java.lang.annotation.*;

/**
 * Represents an HTTP Header.
 * An example of usage:
 * <pre>
 *     {@code @Header(name = "test", value = "test") }
 * </pre>
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Repeatable(Headers.class)
public @interface Header {
    String name();
    String value();
}
