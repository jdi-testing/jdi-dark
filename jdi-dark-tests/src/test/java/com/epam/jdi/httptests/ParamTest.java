package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.ServiceInit.init;
import static org.testng.Assert.assertEquals;

public class ParamTest {

    @BeforeMethod
    public void before() {
        init(JettyService.class);
    }

    @Test
    public void noValueParamWhenUsingQueryParamWithGetRequest() {
        RestResponse response = JettyService.getNoValueParam.call(requestData(d -> {
            d.queryParams.add("some", "");
        }));
        assertEquals(response.body, "Params: some=");
    }

    @Test
    public void multipleNoValueQueryParamWhenUsingQueryParamInUrlForGetRequest() {
        // JettyService service = init(JettyService.class);
        // For some reason Scalatra returns the order different when running in Intellij and Maven
        /*RestResponse resp = GET(requestData(d -> {
            d.url = ("http://localhost:8080/noValueParam?some&some1");
            d.contentType = (ContentType.JSON);
            d.headers = new MapArray<>(new Object[][] {
                    {"charset", "utf-8"},
            });
        }));*/

        RestResponse resp1 = JettyService.getNoValueParamWithParamInUrl.call("some&some1");

        //        .isOk().assertThat().body(anyOf(is("Params: some=some1="), is("Params: some1=some=")));
        //assertEquals(response.body, "Params: some=");

        //requestData(
        //                rd -> { rd.url = "some&some1";
        //                })

    }
}
