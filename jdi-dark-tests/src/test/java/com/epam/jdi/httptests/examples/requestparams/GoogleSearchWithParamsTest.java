package com.epam.jdi.httptests.examples.requestparams;

import static com.epam.http.requests.RequestDataFactory.queryParams;
import static com.epam.http.requests.ServiceInit.init;

import com.epam.http.requests.ServiceSettings;
import com.epam.http.response.RestResponse;
import com.epam.jdi.services.GoogleSearch;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/** Created by oksana_cherniavskaia on 27.08.2020. */
public class GoogleSearchWithParamsTest {

    public static final String QUERY_PARAM = "q";
    public static final String QUERY_VALUE = "apple";

    @Test
    public static void testSearchWithParams() {
        init(GoogleSearch.class, ServiceSettings.builder().domain("https://google.com").build());

        final RestResponse call = GoogleSearch.search.call(queryParams().add(QUERY_PARAM, QUERY_VALUE));
        call.isOk();
        call.validate((it) -> it.getBody().contains("Apple"));
    }

    @BeforeTest
    public void before() {
        init(GoogleSearch.class);
    }
}
