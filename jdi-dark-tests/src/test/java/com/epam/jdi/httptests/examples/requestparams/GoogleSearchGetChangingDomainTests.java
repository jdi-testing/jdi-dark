package com.epam.jdi.httptests.examples.requestparams;

import static com.epam.http.requests.RequestDataFactory.queryParams;
import static com.epam.http.requests.ServiceInit.init;

import com.epam.http.requests.ServiceSettings;
import com.epam.http.response.RestResponse;
import com.epam.jdi.services.DuckDuckGo;
import com.epam.jdi.services.GoogleSearch;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/** Created by oksana_cherniavskaia on 27.08.2020. */
public class GoogleSearchGetChangingDomainTests {
    public static final String QUERY_PARAM = "q";
    public static final String QUERY_VALUE = "apple";

    @BeforeTest
    public void before() {
        init(GoogleSearch.class);
    }

    @Test
    public static void testSearchWithParams() {

        Map<String, String> params = new HashMap<>();
        params.put(QUERY_PARAM, QUERY_VALUE);

        final RestResponse call = GoogleSearch.search.call(queryParams().addAll(params));
        call.isOk();
        call.validate((it) -> it.getBody().contains("Apple"));
    }

    @Test
    public static void testSearchWithInstanceFieldChangingDomain() {

        final GoogleSearch yahoo =
                init(
                        GoogleSearch.class,
                        ServiceSettings.builder().domain("https://yahoo.com").build());
        final RestResponse responseYahoo = yahoo.searchInstanceMethod.call();
        responseYahoo.isOk();
        responseYahoo.validate(it -> it.getBody().contains("yahoo.com"));

        final GoogleSearch initDefault = init(GoogleSearch.class);

        final RestResponse responseGoogle = initDefault.searchInstanceMethod.call();
        responseGoogle.isOk();
        responseGoogle.validate(it -> it.getBody().contains("google.com"));
    }

    @Test
    public static void testSearchWithStaticFieldChangingDomain() {

        //Yahoo domain set globally in service object stored in ServiceInit
        final GoogleSearch yahoo =
                init(
                        GoogleSearch.class,
                        ServiceSettings.builder().domain("https://yahoo.com").build());
        final RestResponse responseYahoo = GoogleSearch.search.call();
        responseYahoo.isOk();
        responseYahoo.validate(it -> it.getBody().contains("yahoo.com"));

        final GoogleSearch initDefault = init(GoogleSearch.class);

        final RestResponse responseGoogle = GoogleSearch.search.call();
        responseGoogle.isOk();
        responseGoogle.validate(it -> it.getBody().contains("google.com"));
    }

    @Test
    public static void testSearchWithStaticFieldChangingDomainAndOtherServiceCall() {

        //Yahoo domain set globally in service object stored in ServiceInit
        final GoogleSearch yahoo =
            init(
                GoogleSearch.class,
                ServiceSettings.builder().domain("https://yahoo.com").build());
        final RestResponse responseYahoo = yahoo.searchInstanceMethod.call();
        responseYahoo.isOk();
        responseYahoo.validate(it -> it.getBody().contains("yahoo.com"));

        final DuckDuckGo duckDuckGo = init(DuckDuckGo.class);
        final RestResponse duckResp = DuckDuckGo.simpleGet.call();
        duckResp.isOk();


        yahoo.searchInstanceMethod.call().validate(it -> it.getBody().contains("yahoo.com"));

        final RestResponse responseGoogle = GoogleSearch.search.call();
        responseGoogle.isOk();
        responseGoogle.validate(it -> it.getBody().contains("yahoo.com"));
    }
}