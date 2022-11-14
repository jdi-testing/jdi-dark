package com.epam.jdi.httptests.examples.requestparams;

import static com.epam.http.requests.RequestDataFactory.queryParams;
import static com.epam.http.requests.ServiceInit.init;

import com.epam.http.response.RestResponse;
import com.epam.jdi.services.QuotesService;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/** Created by oksana_cherniavskaia on 27.08.2020. */
public class QuotesServiceStaticAndInstanceRestMethodsTests {

    @Test
    public void testRandomQuote() {

        final RestResponse call = getQuotesService().quoteOfTheDay.call();
        call.isOk();
    }

    @Test
    public void testRandomQuoteCategoryLanguage() {

        Map<String, Object> params = new HashMap<>();
        params.put(QuotesService.CATEGORY_PARAM, "inspire");
        params.put(QuotesService.LANGUAGE_PARAM, "en");
        // RestMethod field quoteOfTheDay
        final RestResponse call = getQuotesService().quoteOfTheDay.call(queryParams().addAll(params));
        call.isOk();
    }

    @Test
    public void testQuoteOfTheDayCategories() {
        final RestResponse call = getQuotesService().quoteOfTheDayCategories.call();
        call.isOk();
    }

    @Test
    public void testQuoteOfTheDayCategoriesWithParams() {
        Map<String, Object> params = new HashMap<>();

        params.put(QuotesService.CATEGORY_PARAM, "inspire");
        params.put(QuotesService.DETAILED_PARAM, "false");
        final RestResponse call =
                getQuotesService().quoteOfTheDayCategories.call(queryParams().addAll(params));
        call.isOk();
    }

    @Test
    public void testGetLanguages() {
        final RestResponse call = getQuotesService().quoteOfTheDayLanguages.call();
        call.isOk();
    }

    @BeforeTest
    public QuotesService getQuotesService() {
        return init(QuotesService.class);
    }
}
