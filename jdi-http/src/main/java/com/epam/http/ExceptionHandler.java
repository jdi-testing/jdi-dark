package com.epam.http;

/**
 * Created by Roman Iovlev on 14.02.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

public class ExceptionHandler {

    public static RuntimeException exception(String message, Object... args) {
        return new RuntimeException(String.format(message, args));
    }
}
