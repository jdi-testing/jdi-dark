package com.epam.http.annotations;

import java.lang.annotation.*;

/**
 * Represents query parameter.
 * An example of usage:
 * <pre>
 *     {@code @QueryParameter(name = "test", value = "test") }
 * </pre>
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Repeatable(QueryParameters.class)
public @interface QueryParameter {
    String name();
    String value();
}
