package com.epam.http.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a Content-Type. There can be used any values in
 * <a href="https://static.javadoc.io/io.rest-assured/rest-assured/3.1.0/io/restassured/http/ContentType.html">Rest Assured enumeration</a>.
 * An example of usage:
 * <pre>
 *     {@code @ContentType(JSON)}
 * </pre>
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface ContentType {
    io.restassured.http.ContentType value();
}
