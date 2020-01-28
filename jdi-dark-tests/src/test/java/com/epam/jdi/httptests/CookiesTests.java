package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import com.epam.jdi.tools.map.MapArray;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.ServiceInit.init;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.core.IsEqual.equalTo;

public class CookiesTests {

    @BeforeTest
    public void before() {
        //init(ServiceExample.class);
    }

    @Test
    public void requestSpecificationAllowsSpecifyingCookieWithNoValue() throws Exception {
      //  given().cookie("some_cookie").expect().body(Matchers.equalTo("some_cookie")).when().get("/cookie_with_no_value");

        ServiceExample service = init(ServiceExample.class);
        //RestResponse response = service.getCookies2.addCookie("cookie");
        // TODO - no method to add cookie without value

/*                call(
                requestData(requestData ->
                        requestData.cookies = new MapArray<>(new Object[][] {
                                {"additionalCookie", "test"}
                        })));*/
      //  response.isOk();
    }

    @Test
    public void requestSpecificationAllowsSpecifyingCookies() throws Exception {
        given().cookies("username", "John", "token", "1234").then().expect().body(Matchers.equalTo("username, token")).when().get("/cookie");
    }

    @Test
    public void requestSpecificationAllowsSpecifyingCookieUsingMap() throws Exception {
        Map<String, String> cookies = new HashMap<String, String>();
        cookies.put("username", "John");
        cookies.put("token", "1234");
        given().cookies(cookies).then().expect().body(Matchers.equalTo("username, token")).when().get("/cookie");
    }

    @Test
    public void requestSpecificationAllowsSpecifyingMultipleCookies() throws Exception {
        Map<String, String> cookies = new HashMap<String, String>();
        cookies.put("username", "John");
        cookies.put("token", "1234");
        given().cookies(cookies).and().cookies("key1", "value1").then().expect().body(Matchers.equalTo("username, token, key1")).when().get("/cookie");
    }


/*    @Test
    public void cookiesTest() {
        ServiceExample service = init(ServiceExample.class);
        RestResponse response = service.getCookies.call(
                requestData(requestData ->
                        requestData.cookies = new MapArray<>(new Object[][] {
                                {"additionalCookie", "test"}
                        })));
        response.isOk()
                .body("cookies.additionalCookie", equalTo("test"))
                .body("cookies.session_id", equalTo("1234"))
                .body("cookies.hello", equalTo("world"));
    }*/

    @Test
    public void cookiesReturnsAMapWhereTheLastValueOfAMultiValueCookieIsUsed() {

 /*       ServiceExample service = init(ServiceExample.class);
        RestResponse response = service.getCookies.call(
                requestData(requestData ->
                        requestData.cookies = new MapArray<>(new Object[][] {
                                {"additionalCookie", "test"}
                        })));
        response.isOk();

        RestResponse response2 = service.getCookies.call(
                requestData(requestData ->
                        requestData.cookies = new MapArray<>(new Object[][] {
                                {"additionalCookie", "test"}
                        })));


        String cookieVal = response.cookie("additionalCookie");
        System.out.println("\n cookie value " + cookieVal);
        System.out.println("\n cookie value " + response.cookie("session_id"));
*/
        //  assertThat(cookies, hasEntry("cookie1", "cookieValue2"));


    //    Response resp = given().cookie("session_id", "1234").get("https://httpbin.org/cookies");
        Response resp = given().cookie("session_id", "1234")
                .post("https://api.trello.com/1/cards/5a27e722e2f04f3ab6924931/actions/comments?key=3445103a21ddca2619eaceb0e833d0db&token=a9b951262e529821308e7ecbc3e4b7cfb14a24fef5ea500a68c69d374009fcc0");

     //   given().cookie("session_id", "1234").when().get("/users/eugenp")
       //         .then().statusCode(200);
    //    System.out.println("\n boyd" + resp.getBody().asString());
        System.out.println("\n cookies are::: " +resp.cookies().size());
        final Map<String, String> cookies = resp.cookies();
        System.out.println("\n cookies are::: ");
        for (String key : cookies.keySet()) {
            System.out.println("\n key is: " + key + "\n value: " + cookies.get(key));
        }


        Response resp2 = given().cookie("session_id", "1234").auth().basic("user", "password")
                .post("https://httpbin.org/basic-auth/user/password");


        final Map<String, String> cookies2 = resp2.cookies();
        System.out.println("\n cookies are::: " +resp2.cookies().size());
        for (String key : cookies2.keySet()) {
            System.out.println("\n key is: " + key + "\n value: " + cookies2.get(key));
        }
    }


}
