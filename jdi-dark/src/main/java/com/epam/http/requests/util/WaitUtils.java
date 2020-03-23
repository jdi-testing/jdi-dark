package com.epam.http.requests.util;

import java.util.concurrent.TimeUnit;

import static com.epam.http.JdiHttpSettigns.logger;

public class WaitUtils {

    public static void makeDelayFor(TimeUnit unit, Long delay) {
        try {
            unit.sleep(delay);
        } catch (InterruptedException ie) {
            logger.error("Thread was interrupted: " + ie.getCause());
        }
    }
}
