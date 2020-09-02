package com.epam.jdi.httptests.examples;

import com.epam.http.response.RestResponse;
import com.epam.jdi.services.GoogleSearch;
import com.epam.jdi.services.QuotesService;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;
import static org.hamcrest.Matchers.containsString;

/** Created by oksana_cherniavskaia on 27.08.2020. */
public class GoogleSearchGetChangingDomainTests {

    @Test
    public static void testSearchWithInstanceFieldChangingDomain() {
        final GoogleSearch yahoo = init(GoogleSearch.class,"https://yahoo.com");
        final GoogleSearch google = init(GoogleSearch.class, "https://google.com");

        final RestResponse responseYahoo = yahoo.searchInstanceMethod.call();
        responseYahoo.isOk().body(containsString("yahoo.com"));

        final RestResponse responseGoogle = google.searchInstanceMethod.call();
        responseGoogle.isOk().body(containsString("google.com"));
    }

    @Test
    public static void testSearchWithStaticFieldChangingDomain() {
        // Yahoo domain set globally in service object stored in ServiceInit
        final GoogleSearch yahoo = init(GoogleSearch.class,"https://yahoo.com");
        final GoogleSearch google = init(GoogleSearch.class, "https://google.com");

        // static field method field will change globally. Rewrite
        final RestResponse responseYahoo = GoogleSearch.search.call();
        responseYahoo.isOk().body(containsString("google.com"));

        final RestResponse responseGoogle = GoogleSearch.search.call();
        responseGoogle.isOk().body(containsString("google.com"));
    }

    @Test
    public static void testSearchChangingDomainAndOtherServiceCall() {

        final GoogleSearch yahoo = init(GoogleSearch.class, "https://yahoo.com");
        final RestResponse responseYahoo = yahoo.searchInstanceMethod.call();
        responseYahoo.isOk().body(containsString("yahoo.com"));

        final QuotesService quotesService = init(QuotesService.class);
        final RestResponse quotesServiceResp = quotesService.quoteOfTheDayCategories.call();
        quotesServiceResp.isOk();

        final GoogleSearch googleSearch = init(GoogleSearch.class, "https://google.com");

        final RestResponse yahooResponse = yahoo.searchInstanceMethod.call();
        yahooResponse.assertThat().body(containsString("yahoo.com"));

        final RestResponse responseGoogle = googleSearch.searchInstanceMethod.call();
        responseGoogle.isOk().body(containsString("google.com"));
    }
}
