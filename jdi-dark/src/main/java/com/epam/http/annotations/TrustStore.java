package com.epam.http.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a TrustStore located on the file-system.
 * An example of usage:
 * <pre>
 *     {@code @TrustStore(pathToJks = "test", password = "test") }
 * </pre>
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface TrustStore {
        String pathToJks();
        String password();
}
