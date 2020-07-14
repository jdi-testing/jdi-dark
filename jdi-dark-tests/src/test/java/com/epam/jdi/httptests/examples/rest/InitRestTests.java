package com.epam.jdi.httptests.examples.rest;

import com.epam.jdi.dtoGenerated.Credentials;
import com.epam.jdi.dtoGenerated.Token;
import com.epam.jdi.services.RestService;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.services.RestService.usersAuth;
import static io.restassured.RestAssured.given;

public class InitRestTests {

    public RequestSpecification spec;
    public static Token TOKEN;

    @BeforeClass(alwaysRun = true)
    public void authentication() {
        init(RestService.class);
        Credentials credentials = new Credentials();
        credentials.setEmail("admin@epam.com");
        credentials.setPassword("1234");
//        String token = usersAuth.call().asData(Token.class).getAccess_token();
//        TOKEN = jsonUsersAuth(credentials);
        TOKEN = usersAuth.call(rd -> rd.setBody(credentials)).asData(Token.class);
        String token = "Bearer " + TOKEN.getAccess_token();
//        spec = given().auth().preemptive().oauth2(token);
        spec = given().header("Authorization", token);
    }
}
