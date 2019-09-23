package com.epam.http;

/**
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
public class ExceptionHandler {

    public static RuntimeException exception(String message, Object... args) {
        return new RuntimeException(String.format(message, args));
    }
}
