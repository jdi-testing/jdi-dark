package com.epam.http;

import static java.lang.String.format;

/**
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
public class ExceptionHandler {

    public static RuntimeException exception(String msg, Object... args) {
        String message = args.length == 0 ? msg : format(msg, args);
        return new RuntimeException(message);
    }
}
