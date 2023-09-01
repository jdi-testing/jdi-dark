package com.epam.jdi.httptests.examples.requestparams;

import static com.epam.http.requests.RequestDataFactory.queryParams;
import static com.epam.http.requests.ServiceInit.init;

import com.epam.http.response.RestResponse;
import com.epam.jdi.services.QuotesService;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

/** Created by oksana_cherniavskaia on 27.08.2020. */
// Service is avaible for free only with token and 5 requests per day are free
@Ignore
public class QuotesServiceStaticAndInstanceRestMethodsTests {
    @BeforeTest
    public void before() {
        service = init(QuotesService.class);
    }
    private static QuotesService service;

    @Test
    public static void testRandomQuote() {

        final RestResponse call = service.quoteOfTheDay.call();
        call.isOk();
    }

    @Test
    public static void testRandomQuoteCategoryLanguage() {

        Map<String, Object> params = new HashMap<>();
        params.put(QuotesService.CATEGORY_PARAM, "inspire");
        params.put(QuotesService.LANGUAGE_PARAM, "en");
        // static RestMethod field quoteOfTheDay
        final RestResponse call = QuotesService.quoteOfTheDay.call(queryParams().addAll(params));
        call.isOk();
    }

    @Test
    public static void testQuoteOfTheDayCategories() {
        final RestResponse call = service.quoteOfTheDayCategories.call();
        call.isOk();
    }

    @Test
    public static void testQuoteOfTheDayCategoriesWithParams() {
        Map<String, Object> params = new HashMap<>();

        params.put(QuotesService.CATEGORY_PARAM, "inspire");
        params.put(QuotesService.DETAILED_PARAM, "false");
        final RestResponse call =
                service.quoteOfTheDayCategories.call(queryParams().addAll(params));
        call.isOk();
    }

    @Test
    public static void testGetLanguages() {
        final RestResponse call = service.quoteOfTheDayLanguages.call();
        call.isOk();
    }


}
