package com.epam.jdi.httptests.examples.requestparams;

import static com.epam.http.requests.RequestDataFactory.queryParams;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.services.GoogleSearch.search;

import com.epam.http.response.RestResponse;
import com.epam.jdi.services.GoogleSearch;
import org.testng.annotations.Test;

/** Created by oksana_cherniavskaia on 27.08.2020. */
public class GoogleSearchSimpleGetWithParamsTest {

    public static final String QUERY_PARAM = "q";
    public static final String QUERY_VALUE = "apple";

    @Test
    public static void testSearchWithParams() {
        init(GoogleSearch.class);
        final RestResponse call =
                search.call(queryParams().addAll(new Object[][] {{QUERY_PARAM, QUERY_VALUE}}));
        call.isOk();
        call.validate((it) -> it.getBody().contains("Apple"));
    }
}
