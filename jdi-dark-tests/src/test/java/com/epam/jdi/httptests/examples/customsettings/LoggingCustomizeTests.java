package com.epam.jdi.httptests.examples.customsettings;

import com.epam.http.logger.AllureLogger;
import com.epam.http.requests.RequestData;
import com.epam.http.requests.RestMethod;
import com.epam.http.response.RestResponse;
import com.epam.jdi.services.JettyService;
import com.epam.jdi.httptests.support.WithJetty;
import com.epam.jdi.tools.map.MultiMap;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.http.JdiHttpSettings.logger;
import static com.epam.http.logger.AllureLogger.startStep;
import static com.epam.http.requests.RestMethod.LOG_REQUEST;
import static com.epam.http.requests.RestMethod.resetLogRequest;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.http.response.RestResponse.LOG_RESPONSE;
import static com.epam.http.response.RestResponse.resetLogResponse;
import static org.hamcrest.Matchers.equalTo;

public class LoggingCustomizeTests extends WithJetty {

    @BeforeClass
    public void initService() {
        init(JettyService.class);
        LOG_REQUEST = this::logRequest;
        LOG_RESPONSE = this::logResponse;
    }

    private String logRequest(RestMethod restMethod, List<RequestData> requestData) {
        MultiMap<String, String> queryparams = new MultiMap<>();
        for (RequestData rd : requestData) {
            queryparams.addAll(rd.queryParams);
        }
        String message = String.format("Do %s %s", restMethod.type, restMethod.url);
        logger.info(message);
        //Change request logging for allure
        startStep(message,
                String.format("%s %s %s", restMethod.type, restMethod.url, queryparams));
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
        resetLogRequest();
        resetLogResponse();
    }
}
