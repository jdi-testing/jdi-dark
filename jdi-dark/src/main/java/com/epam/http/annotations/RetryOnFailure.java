package com.epam.http.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface RetryOnFailure {

    int numberOfAttempts() default 3;

    int[] errorCodes() default {502, 503};

    long delay() default 10;

    TimeUnit unit() default TimeUnit.MICROSECONDS;
}
