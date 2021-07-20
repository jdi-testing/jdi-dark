package com.epam.jdi.httptests.examples.requestparams;

import static com.epam.http.requests.RequestDataFactory.queryParams;
import static com.epam.http.requests.ServiceInit.init;

import com.epam.http.response.RestResponse;
import com.epam.jdi.services.CurrencyRatesService;
import io.restassured.builder.RequestSpecBuilder;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/** Created by oksana_cherniavskaia on 27.08.2020. */
// !!!!! Important!!!! https://api.ratesapi.io/ is off
public class CurrencyRatesServiceWithQueryAndPathParamsTest {

    public static final String SYMBOLS_PARAM = "symbols";
    public static final String SYMBOLS_PARAM_VALUE = "USD,GBP";
    public static final String BASE_PARAM = "base";
    public static final String BASE_PARAM_VALUE = "USD";

    @BeforeTest
    public void before() {
        init(CurrencyRatesService.class);
    }

    @Test(enabled = false)
    public void callGetLatestQuotes() {

        final RestResponse call = CurrencyRatesService.latest.call();
        call.isOk();
    }

    @Test(enabled = false)
    public void callGetLatestQuotesSymbolsParam() {

        init(CurrencyRatesService.class);
        final RestResponse call =
                CurrencyRatesService.latest.call(
                        queryParams()
                                .addAll(
                                        new Object[][] {
                                            {
                                                SYMBOLS_PARAM, SYMBOLS_PARAM_VALUE,
                                            }
                                        }));
        call.isOk();
        System.out.println(call.getBody());
    }

    @Test(enabled = false)
    public void callGetLatestQuotesSymbolsAndBaseParam() {

        init(CurrencyRatesService.class);
        final RestResponse call =
                CurrencyRatesService.latest.call(
                        queryParams()
                                .addAll(
                                        new Object[][] {
                                            {
                                                SYMBOLS_PARAM,
                                                SYMBOLS_PARAM_VALUE,
                                                BASE_PARAM,
                                                BASE_PARAM_VALUE
                                            }
                                        }));
        call.isOk();
        System.out.println(call.getBody());
    }

    @Test(enabled = false)
    public void callGetHistoricalQuotesSymbolsAndBaseParam() {

        final Map<String, Object> parametersMap = new HashMap<>();

        parametersMap.put(SYMBOLS_PARAM, SYMBOLS_PARAM_VALUE);
        parametersMap.put(BASE_PARAM, BASE_PARAM_VALUE);

        final RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addPathParams("date", "2010-01-12").addQueryParams(parametersMap);

        final RestResponse call = CurrencyRatesService.historical.call(requestSpecBuilder.build());
        call.isOk();
        System.out.println(call.getBody());
    }
}
