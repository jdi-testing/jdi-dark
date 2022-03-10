package com.epam.jdi.httptests.examples;

import static com.epam.http.requests.ServiceInit.init;
import static org.hamcrest.Matchers.containsString;

import com.epam.http.response.RestResponse;
import com.epam.jdi.services.QuotesService;
import com.epam.jdi.services.ServiceNoDomainAnnotation;
import org.testng.annotations.Test;

/**
 * Created by oksana_cherniavskaia on 02.09.2020.
 */
public class DomainSwitchTest {

  @Test
  public void testSearchWithInstanceFieldChangingDomain() {
    final RestResponse responseYahoo = getYahoo().get.call();
    responseYahoo.isOk().body(containsString("yahoo.com"));

    final RestResponse responseGoogle = getGoogle().get.call();
    responseGoogle.isOk().body(containsString("google.com"));
  }

  @Test(enabled = false) //this test was aimed to check static fields. need to refactor after PoC
  public static void testSearchWithStaticFieldChangingDomain() {
    // static field method field will change globally. Rewrite
    //final RestResponse responseYahoo = yahoo.getStatic.call();
    //responseYahoo.isOk().body(containsString("google.com"));

    //The same as     ServiceNoDomain.getStatic.call();
    //final RestResponse responseGoogle = google.getStatic.call();
    //responseGoogle.isOk().body(containsString("google.com"));
  }

  @Test
  public void testSearchChangingDomainAndOtherServiceCall() {
    ServiceNoDomainAnnotation yahoo = getYahoo();

    final RestResponse responseYahoo = yahoo.get.call();
    responseYahoo.isOk().body(containsString("yahoo.com"));

    final QuotesService quotesService = init(QuotesService.class);
    final RestResponse quotesServiceResp = quotesService.quoteOfTheDayCategories.call();
    quotesServiceResp.isOk();

    final ServiceNoDomainAnnotation googleSearch = getGoogle();

    final RestResponse yahooResponse = yahoo.get.call();
    yahooResponse.assertThat().body(containsString("yahoo.com"));

    final RestResponse responseGoogle = googleSearch.get.call();
    responseGoogle.isOk();
    responseGoogle.assertThat().body(containsString("google.com"));
  }

  private ServiceNoDomainAnnotation getYahoo() {
    return init(ServiceNoDomainAnnotation.class, "https://yahoo.com");
  }

  private ServiceNoDomainAnnotation getGoogle() {
    return init(ServiceNoDomainAnnotation.class, "https://google.com");
  }
}
