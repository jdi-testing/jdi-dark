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
    public void testSearchWithInstanceFieldChangingDomain() {
        GoogleSearch yahoo = init(GoogleSearch.class,"https://yahoo.com");
        GoogleSearch google = init(GoogleSearch.class, "https://google.com");

        RestResponse response = yahoo.searchInstanceMethod.call();
        response.isOk().body(containsString("yahoo.com"));

        response = google.searchInstanceMethod.call();
        response.isOk().body(containsString("google.com"));
    }

    @Test (enabled = false) // test have incorrect steps logic and not understandable validations logic, need to refactor
    public static void testSearchWithStaticFieldChangingDomain() {
        // static field method field will change globally. Rewrite
        //final RestResponse responseYahoo = GoogleSearch.search.call();
        //responseYahoo.isOk().body(containsString("google.com"));

        //final RestResponse responseGoogle = GoogleSearch.search.call();
        //responseGoogle.isOk().body(containsString("google.com"));
    }

    @Test
    public void testSearchChangingDomainAndOtherServiceCall() {
        GoogleSearch yahoo = init(GoogleSearch.class, "https://yahoo.com");
        RestResponse response = yahoo.searchInstanceMethod.call();
        response.isOk().body(containsString("yahoo.com"));

        QuotesService quotesService = init(QuotesService.class);
        response = quotesService.quoteOfTheDayCategories.call();
        response.isOk();

        GoogleSearch googleSearch = init(GoogleSearch.class, "https://google.com");

        response = yahoo.searchInstanceMethod.call();
        response.assertThat().body(containsString("yahoo.com"));

        response = googleSearch.searchInstanceMethod.call();
        response.isOk().body(containsString("google.com"));
    }
}
