package com.epam.jdi.httptests.examples;

import static com.epam.http.requests.ServiceInit.init;

import com.epam.http.requests.ServiceSettings;
import com.epam.http.response.RestResponse;
import com.epam.jdi.services.GoogleSearch;
import com.epam.jdi.services.QuotesService;
import org.testng.annotations.Test;

/** Created by oksana_cherniavskaia on 27.08.2020. */
public class GoogleSearchGetChangingDomainTests {

    @Test
    public static void testSearchWithInstanceFieldChangingDomain() {

        final GoogleSearch yahoo =
                init(
                        GoogleSearch.class,
                        ServiceSettings.builder().domain("https://yahoo.com").build());
        final GoogleSearch google =
                init(
                        GoogleSearch.class,
                        ServiceSettings.builder().domain("https://google.com").build());

        final RestResponse responseYahoo = yahoo.searchInstanceMethod.call();

        responseYahoo.isOk();
        responseYahoo.validate(it -> it.getBody().contains("yahoo.com"));

        final RestResponse responseGoogle = google.searchInstanceMethod.call();
        responseGoogle.isOk();
        responseGoogle.validate(it -> it.getBody().contains("google.com"));
    }

    @Test
    public static void testSearchWithStaticFieldChangingDomain() {

        // Yahoo domain set globally in service object stored in ServiceInit
        final GoogleSearch yahoo =
                init(
                        GoogleSearch.class,
                        ServiceSettings.builder().domain("https://yahoo.com").build());
        final GoogleSearch google =
                init(
                        GoogleSearch.class,
                        ServiceSettings.builder().domain("https://google.com").build());

        // static field method field will change globally. Rewrite
        final RestResponse responseYahoo = GoogleSearch.search.call();
        responseYahoo.isOk();
        responseYahoo.validate(it -> it.getBody().contains("google.com"));

        final RestResponse responseGoogle = GoogleSearch.search.call();
        responseGoogle.isOk();
        responseGoogle.validate(it -> it.getBody().contains("google.com"));
    }

    @Test
    public static void testSearchChangingDomainAndOtherServiceCall() {

        final GoogleSearch yahoo =
                init(
                        GoogleSearch.class,
                        ServiceSettings.builder().domain("https://yahoo.com").build());
        final RestResponse responseYahoo = yahoo.searchInstanceMethod.call();
        responseYahoo.isOk();
        responseYahoo.validate(it -> it.getBody().contains("yahoo.com"));

        final QuotesService quotesService = init(QuotesService.class);
        final RestResponse quotesServiceResp = quotesService.quoteOfTheDayCategories.call();
        quotesServiceResp.isOk();

        final GoogleSearch googleSearch =
                init(
                        GoogleSearch.class,
                        ServiceSettings.builder().domain("https://google.com").build());

        final RestResponse yahooResponse = yahoo.searchInstanceMethod.call();
        yahooResponse.validate(it -> it.getBody().contains("yahoo.com"));

        final RestResponse responseGoogle = googleSearch.searchInstanceMethod.call();
        responseGoogle.isOk();
        responseGoogle.validate(it -> it.getBody().contains("google.com"));
    }
}
