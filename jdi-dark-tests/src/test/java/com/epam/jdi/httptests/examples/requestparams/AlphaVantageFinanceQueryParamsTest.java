package com.epam.jdi.httptests.examples.requestparams;

import static com.epam.http.requests.RequestDataFactory.queryParams;
import static com.epam.http.requests.ServiceInit.init;

import com.epam.http.response.RestResponse;
import com.epam.jdi.services.AlphaVantageFinanceService;
import io.restassured.builder.RequestSpecBuilder;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;

/** Created by oksana_cherniavskaia on 27.08.2020. */
public class AlphaVantageFinanceQueryParamsTest {

    public static final String FUNCTION_PARAM = "function";
    public static final String FUNCTION_PARAM_VALUE = "GLOBAL_QUOTE";
    public static final String SYMBOL_PARAM = "symbol";
    public static final String SYMBOL_PARAM_VALUE = "AAPL";
    public static final String API_KEY_PARAM = "apikey";
    public static final String API_KEY_PARAM_VALUE = "JSE55UV7F8PW7PYT";

    @Test
    public void callGetLatestQuotesSymbolParamWithMap() {

        final Map<String, Object> parametersMap = new HashMap<>();

        parametersMap.put(FUNCTION_PARAM, FUNCTION_PARAM_VALUE);
        parametersMap.put(SYMBOL_PARAM, SYMBOL_PARAM_VALUE);
        parametersMap.put(API_KEY_PARAM, API_KEY_PARAM_VALUE);

        final RestResponse call = getAlphaVantageFinanceService().getQuotes.call(
            queryParams()
                .addAll(
                    new Object[][]{
                        {FUNCTION_PARAM, FUNCTION_PARAM_VALUE},
                        {SYMBOL_PARAM, SYMBOL_PARAM_VALUE},
                        {API_KEY_PARAM, API_KEY_PARAM_VALUE}
                    }));
        call.isOk();
        System.out.println(call.getBody());
    }

    @Test
    public void callGetLatestQuotesSymbolParamWithCustomSpec() {

        final Map<String, Object> parametersMap = new HashMap<>();

        parametersMap.put(FUNCTION_PARAM, FUNCTION_PARAM_VALUE);
        parametersMap.put(SYMBOL_PARAM, SYMBOL_PARAM_VALUE);
        parametersMap.put(API_KEY_PARAM, API_KEY_PARAM_VALUE);


        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addQueryParams(parametersMap);

        final RestResponse call2 = getAlphaVantageFinanceService().getQuotes.call(requestSpecBuilder.build());

        call2.isOk();
    }

    @Test
    public void callGetLatestQuotesSymbolParamWith2DArray() {

        final RestResponse call = getAlphaVantageFinanceService().getQuotes.call(
            queryParams()
                .addAll(
                    new Object[][]{
                        {FUNCTION_PARAM, FUNCTION_PARAM_VALUE},
                        {SYMBOL_PARAM, SYMBOL_PARAM_VALUE},
                        {API_KEY_PARAM, API_KEY_PARAM_VALUE}
                    }));
        call.isOk();
    }

    public AlphaVantageFinanceService getAlphaVantageFinanceService() {
        return init(AlphaVantageFinanceService.class);
    }

}
