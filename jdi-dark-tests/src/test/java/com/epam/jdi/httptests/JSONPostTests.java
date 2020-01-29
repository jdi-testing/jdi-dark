package com.epam.jdi.httptests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class JSONPostTests extends WithJetty {
    @Test
    public void simpleJSONAndHamcrestMatcher() {
        given().params("firstName", "John", "lastName", "Doe").expect().body("greeting", Matchers.equalTo("Greetings John Doe")).when().post("/greet");
    }

    @Test
    public void simpleJSONAndHamcrestMatcher4() {
        expect().body("hello", equalTo("Hello Scalatra")).when().get("/hello");
    }
}
