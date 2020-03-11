package com.epam.jdi.httptests;

import com.epam.http.requests.RequestData;
import com.epam.http.requests.RestMethod;
import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.support.WithJetty;
import com.epam.jdi.tools.map.MultiMap;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.http.JdiHttpSettigns.logger;
import static com.epam.http.requests.RestMethod.LOG_REQUEST;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.http.response.RestResponse.LOG_RESPONSE;
import static org.hamcrest.Matchers.equalTo;

public class LoggingCustomizeTests extends WithJetty {

    @BeforeClass
    public void initService() {
        init(JettyService.class);
        LOG_REQUEST = this::logRequest;
        LOG_RESPONSE = (response, s) -> logResponse(response);
    }

    private String logRequest(RestMethod restMethod, List<RequestData> requestData) {
        MultiMap<String, String> queryparams = new MultiMap<>();
        for (RequestData rd : requestData) {
            queryparams.addAll(rd.queryParams);
        }
        String message = String.format("Do %s request:  %s with params: %s", restMethod.getType(), restMethod.getUrl(), queryparams);
        logger.info(message);
        return message;
    }

    private void logResponse(RestResponse response) {
        String message = String.format("Received response with  %s and body: %s", response.getStatus(), response.getBody());
        logger.info(message);
    }

    @Test
    public void whenLastParamInGetRequestEndsWithEqualItsTreatedAsANoValueParam() {
        Map<String, String> queryParamsMap = new HashMap<>();
        queryParamsMap.put("firstName", "Ivan");
        queryParamsMap.put("lastName", "Ivanov");

        JettyService.getGreetWithMapOfQueryParams(queryParamsMap)
                .isOk()
                .assertThat()
                .body("greeting", equalTo("Greetings Ivan Ivanov"));
    }
}
