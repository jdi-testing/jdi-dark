package com.epam.http.logger.aspects;

import com.epam.http.ExceptionHandler;
import com.epam.http.logger.AllureLogger;
import com.epam.http.requests.RequestData;
import com.epam.http.requests.RestMethod;
import com.epam.http.response.RestResponse;
import com.epam.jdi.tools.func.JAction1;
import com.epam.jdi.tools.func.JAction2;
import com.epam.jdi.tools.func.JFunc2;

import java.util.ArrayList;

import static com.epam.http.JdiHttpSettigns.logger;
import static com.epam.http.logger.AllureLogger.startStep;
import static java.lang.String.format;

public class LoggerHelper {

    private LoggerHelper() {
    }

    public static JAction1<LogObject> LOG_REQUEST = LoggerHelper::logRequest;

    private static void logRequest(LogObject logObject) {
        ArrayList<String> maps = new ArrayList<>();
        for (RequestData rd : logObject.getRd()) {
            maps.addAll(rd.fields().filter((k, v) -> !k.equals("empty") && v != null && !v.toString().equals("[]") && !v.toString().isEmpty()).map((k, v) -> "\n" + k + ": " + v));
        }

        RestMethod restMethod = logObject.getRestMethod();
        logger.info(format("Do %s request: %s%s %s", restMethod.getType(), restMethod.getUrl() != null ? restMethod.getUrl() : "", restMethod.getPath() != null ? restMethod.getPath() : "", maps));

        String uuid = startStep(format("%s %s%s", restMethod.getType(), restMethod.getUrl() != null ? restMethod.getUrl() : "", restMethod.getPath() != null ? restMethod.getPath() : ""),
                format("%s %s%s  %s", restMethod.getType(), restMethod.getUrl() != null ? restMethod.getUrl() : "", restMethod.getPath() != null ? restMethod.getPath() : "", maps));
        logObject.setUuid(uuid);
    }

    public static JAction2<RestResponse, String> LOG_RESPONSE = LoggerHelper::logResponse;

    public static void logResponse(RestResponse restResponse, String uuid) {
        logger.info(restResponse.toString());
        AllureLogger.passStep(restResponse.toString(), uuid);
    }

    public static JFunc2<LogObject, Throwable, RuntimeException> LOG_FAILURE = LoggerHelper::logFailure;

    private static RuntimeException logFailure(LogObject logObject, Throwable ex) {
        logger.error(ex.getMessage(), logObject.getRd(), logObject.getRestMethod());
        AllureLogger.failStep(logObject.getUuid());
        return ExceptionHandler.exception(ex.getMessage(), logObject.getRd(), logObject.getRestMethod());
    }
}
