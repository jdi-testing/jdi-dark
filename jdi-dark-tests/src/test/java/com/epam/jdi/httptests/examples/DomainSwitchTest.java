package com.epam.jdi.httptests.examples;

import static com.epam.http.requests.ServiceInit.init;
import static org.hamcrest.Matchers.containsString;

import com.epam.http.requests.ServiceSettings;
import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.support.TwoServicesInit;
import com.epam.jdi.services.QuotesService;
import com.epam.jdi.services.ServiceNoDomainAnnotation;
import org.testng.annotations.Test;

/**
 * Created by oksana_cherniavskaia on 02.09.2020.
 */
public class DomainSwitchTest extends TwoServicesInit {
  @Test
  public static void testSearchWithInstanceFieldChangingDomain() {
    final RestResponse responseYahoo = yahoo.get.call();
    responseYahoo.isOk().body(containsString("yahoo.com"));

    final RestResponse responseGoogle = google.get.call();
    responseGoogle.isOk().body(containsString("google.com"));
  }

  @Test
  public static void testSearchWithStaticFieldChangingDomain() {
    // static field method field will change globally. Rewrite
    final RestResponse responseYahoo = yahoo.getStatic.call();
    responseYahoo.isOk().body(containsString("google.com"));

    //The same as     ServiceNoDomain.getStatic.call();
    final RestResponse responseGoogle = google.getStatic.call();
    responseGoogle.isOk().body(containsString("google.com"));
  }

  @Test
  public static void testSearchChangingDomainAndOtherServiceCall() {
    final RestResponse responseYahoo = yahoo.get.call();
    responseYahoo.isOk().body(containsString("yahoo.com"));

    final QuotesService quotesService = init(QuotesService.class);
    final RestResponse quotesServiceResp = quotesService.quoteOfTheDayCategories.call();
    quotesServiceResp.isOk();

    final ServiceNoDomainAnnotation googleSearch =
        init(ServiceNoDomainAnnotation.class, "https://google.com");

    final RestResponse yahooResponse = yahoo.get.call();
    yahooResponse.assertThat().body(containsString("yahoo.com"));

    final RestResponse responseGoogle = googleSearch.get.call();
    responseGoogle.isOk();
    responseGoogle.assertThat().body(containsString("google.com"));
  }
}
