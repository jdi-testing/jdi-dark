package com.epam.jdi.httptests.examples;

import static com.epam.http.requests.ServiceInit.init;

import com.epam.http.requests.ServiceSettings;
import com.epam.http.response.RestResponse;
import com.epam.jdi.services.QuotesService;
import com.epam.jdi.services.ServiceNoDomainAnnotation;
import org.testng.annotations.Test;

/**
 * Created by oksana_cherniavskaia on 02.09.2020.
 */
public class DomainSwitchTest {
  @Test
  public static void testSearchWithInstanceFieldChangingDomain() {

    final ServiceNoDomainAnnotation yahoo =
        init(
            ServiceNoDomainAnnotation.class,
            ServiceSettings.builder().domain("https://yahoo.com").build());
    final ServiceNoDomainAnnotation google =
        init(
            ServiceNoDomainAnnotation.class,
            ServiceSettings.builder().domain("https://google.com").build());

    final RestResponse responseYahoo = yahoo.get.call();

    responseYahoo.isOk();
    responseYahoo.validate(it -> it.getBody().contains("yahoo.com"));

    final RestResponse responseGoogle = google.get.call();
    responseGoogle.isOk();
    responseGoogle.validate(it -> it.getBody().contains("google.com"));
  }

  @Test
  public static void testSearchWithStaticFieldChangingDomain() {

    // Yahoo domain set globally in service object stored in ServiceInit
    final ServiceNoDomainAnnotation yahoo =
        init(
            ServiceNoDomainAnnotation.class,
            ServiceSettings.builder().domain("https://yahoo.com").build());
    final ServiceNoDomainAnnotation google =
        init(
            ServiceNoDomainAnnotation.class,
            ServiceSettings.builder().domain("https://google.com").build());

        // static field method field will change globally. Rewrite
        final RestResponse responseYahoo = yahoo.getStatic.call();;
    responseYahoo.isOk();
    responseYahoo.validate(it -> it.getBody().contains("google.com"));

    final RestResponse responseGoogle = google.getStatic.call(); //The same as     ServiceNoDomain.getStatic.call();

    responseGoogle.isOk();
    responseGoogle.validate(it -> it.getBody().contains("google.com"));
  }

  @Test
  public static void testSearchChangingDomainAndOtherServiceCall() {

    final ServiceNoDomainAnnotation yahoo =
        init(
            ServiceNoDomainAnnotation.class,
            ServiceSettings.builder().domain("https://yahoo.com").build());
    final RestResponse responseYahoo = yahoo.get.call();
    responseYahoo.isOk();
    responseYahoo.validate(it -> it.getBody().contains("yahoo.com"));

    final QuotesService quotesService = init(QuotesService.class);
    final RestResponse quotesServiceResp = quotesService.quoteOfTheDayCategories.call();
    quotesServiceResp.isOk();

    final ServiceNoDomainAnnotation googleSearch =
        init(
            ServiceNoDomainAnnotation.class,
            ServiceSettings.builder().domain("https://google.com").build());

    final RestResponse yahooResponse = yahoo.get.call();
    yahooResponse.validate(it -> it.getBody().contains("yahoo.com"));

    final RestResponse responseGoogle = googleSearch.get.call();
    responseGoogle.isOk();
    responseGoogle.validate(it -> it.getBody().contains("google.com"));
  }
}
