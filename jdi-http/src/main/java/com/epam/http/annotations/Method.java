package com.epam.http.annotations;

/**
 * Created by Roman Iovlev on 14.02.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

import com.epam.http.requests.RestMethodTypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.epam.http.requests.RestMethodTypes.DELETE;
import static com.epam.http.requests.RestMethodTypes.GET;
import static com.epam.http.requests.RestMethodTypes.POST;
import static com.epam.http.requests.RestMethodTypes.PUT;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Method {
    String value();
    RestMethodTypes[] types() default { GET, POST, PUT, DELETE };
}
