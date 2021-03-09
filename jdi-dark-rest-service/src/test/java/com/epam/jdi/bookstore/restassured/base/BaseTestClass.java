package com.epam.jdi.bookstore.restassured.base;

import com.epam.jdi.bookstore.model.Book;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseTestClass {

    public int createBookPrecondition(RequestSpecification requestSpec, Book book) {
        String id = given()
                .auth().basic("admin@epam.com", "1234")
            .spec(requestSpec)
            .body(book)
            .when()
            .post("/books")
            .then()
            .assertThat()
            .statusCode(201)
            .extract()
            .body().jsonPath().getString("id");
        return Integer.parseInt(id);
    }

    public void createBookPreconditionNoValidation(RequestSpecification requestSpec, Book book) {
        given()
            .auth().basic("admin@epam.com", "1234")
            .spec(requestSpec)
            .body(book)
            .when()
            .post("/books");
    }
}
