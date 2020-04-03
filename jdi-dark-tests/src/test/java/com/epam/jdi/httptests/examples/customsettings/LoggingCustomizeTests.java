package com.epam.jdi.httptests.examples.customsettings;

import com.epam.http.logger.AllureLogger;
import com.epam.http.requests.RequestData;
import com.epam.http.requests.RestMethod;
import com.epam.http.response.RestResponse;
import com.epam.jdi.services.JettyService;
import com.epam.jdi.httptests.support.WithJetty;
import com.epam.jdi.tools.func.JAction2;
import com.epam.jdi.tools.func.JFunc2;
import com.epam.jdi.tools.map.MultiMap;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.http.JdiHttpSettigns.logger;
import static com.epam.http.logger.AllureLogger.startStep;
import static com.epam.http.requests.RestMethod.LOG_REQUEST;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.http.response.RestResponse.LOG_RESPONSE;
import static org.hamcrest.Matchers.equalTo;

public class LoggingCustomizeTests extends WithJetty {
    private static JFunc2<RestMethod, List<RequestData>, String> LOG_REQUEST_TEMP;
    private static JAction2<RestResponse, String> LOG_RESPONSE_TEMP;

    @BeforeClass
    public void initService() {
        init(JettyService.class);
        LOG_REQUEST_TEMP = LOG_REQUEST;
        LOG_RESPONSE_TEMP = LOG_RESPONSE;
        LOG_REQUEST = this::logRequest;
        LOG_RESPONSE = this::logResponse;
    }

    private String logRequest(RestMethod restMethod, List<RequestData> requestData) {
        MultiMap<String, String> queryparams = new MultiMap<>();
        for (RequestData rd : requestData) {
            queryparams.addAll(rd.queryParams);
        }
        String message = String.format("Do %s %s", restMethod.getType(), restMethod.getUrl());
        logger.info(message);
        //Change request logging for allure
        startStep(message,
                String.format("%s %s %s", restMethod.getType(), restMethod.getUrl(), queryparams));
        return message;
    }

    private void logResponse(RestResponse response, String uuid) {
        String message = String.format("Received response with %s and body: %s", response.getStatus(), response.getBody());
        logger.info(message);
        //Change response logging for allure
        AllureLogger.passStep(message, uuid);
    }

    @Test
    public void getGreeting() {
        Map<String, String> queryParamsMap = new HashMap<>();
        queryParamsMap.put("firstName", "Ivan");
        queryParamsMap.put("lastName", "Ivanov");
        JettyService.getGreetWithMapOfQueryParams(queryParamsMap)
                .isOk()
                .assertThat()
                .body("greeting", equalTo("Greetings Ivan Ivanov"));
    }

    @AfterClass
    public void clearLogger() {
        LOG_REQUEST = LOG_REQUEST_TEMP;
        LOG_RESPONSE = LOG_RESPONSE_TEMP;
    }
}
