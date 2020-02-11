package com.epam.http.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents the collection of form parameters.
 * An example of usage:
 * <pre>
 *      <code>
 *     {@literal @}FormParameters({
 *     {@literal @}FormParameter(name = "test1", value = "value1"),
 *     {@literal @}FormParameter(name = "test2", value = "value2")
 *     })
 *     </code>
 * </pre>
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface FormParameters {
    FormParameter[] value();
}