package com.epam.jdi.bookstore.restassured.bookapi;

import com.epam.jdi.bookstore.model.Book;
import com.epam.jdi.bookstore.restassured.base.BaseTestClass;
import com.epam.jdi.bookstore.model.Genre;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveOAuth2HeaderScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static com.epam.jdi.bookstore.restassured.base.Token.TOKEN;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@SuppressWarnings({"PMD.MethodNamingConventions", "PMD.JUnitTestsShouldIncludeAssert"})
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetBookTests extends BaseTestClass {

    @LocalServerPort
    private int port;

    RequestSpecification requestSpec;

    private Book book1;
    private Book book2;

    @Before
    public void setUp() {
        initBooks();
        PreemptiveOAuth2HeaderScheme oAuth2Scheme = new PreemptiveOAuth2HeaderScheme();
        oAuth2Scheme.setAccessToken(TOKEN);
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(port)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .setAuth(oAuth2Scheme)
                .log(LogDetail.ALL)
                .build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void getBook_byExistingBookId_shouldReturnBookObjectAnd200() {
        int id = createBookPrecondition(requestSpec, book1);
        given()
                .spec(requestSpec)
                .when()
                .get("/books/" + id)
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(id));
    }

    @Test
    public void getBook_byNonExistingBookId_shouldReturnErrorAnd404() {
        int id = 999;
        given()
                .spec(requestSpec)
                .when()
                .get("/books/" + id)
                .then()
                .assertThat()
                .statusCode(404)
                .body("message", equalTo("Book with ID '" + id + "' not found"));
    }

    @Test
    public void getBook_byNegativeId_shouldReturnErrorAnd400() {
        int id = -1;
        given()
                .spec(requestSpec)
                .when()
                .get("/books/" + id)
                .then()
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("getBookById.id: must be greater than or equal to 1"));
    }

    @Test
    public void getBook_byZeroId_shouldReturnErrorAnd400() {
        int id = 0;
        given()
                .spec(requestSpec)
                .when()
                .get("/books/" + id)
                .then()
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("getBookById.id: must be greater than or equal to 1"));
    }

    @Test
    public void getBook_byIsbnWithSizeLessThan10_shouldReturnErrorAnd400() {
        String isbn = "55453";
        given()
                .spec(requestSpec)
                .when()
                .get("/books/isbn/" + isbn)
                .then()
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("getBookByIsbn.isbn: must consist of 10 or 13 digits with or without hyphens. Spaces are not allowed."));
    }

    @Test
    public void getBook_byIsbnWithSizeGreaterThan13_shouldReturnErrorAnd400() {
        String isbn = "554536546456456464646545";
        given()
                .spec(requestSpec)
                .when()
                .get("/books/isbn/" + isbn)
                .then()
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("getBookByIsbn.isbn: must consist of 10 or 13 digits with or without hyphens. Spaces are not allowed."));
    }

    @Test
    public void getBook_byExistingBookIsbn_shouldReturnBookObjectAnd200() {
        createBookPrecondition(requestSpec, book2);
        String isbn = book2.getIsbn();
        given()
                .spec(requestSpec)
                .when()
                .get("/books/isbn/" + isbn)
                .then()
                .assertThat()
                .statusCode(200)
                .body("isbn", equalTo(isbn));
    }

    @Test
    public void getBook_byExistingGenreId_shouldReturnBookObjectAnd200() {
        int genreId = 1;
        given()
                .spec(requestSpec)
                .when()
                .get("/books/genre/" + genreId)
                .then()
                .assertThat()
                .statusCode(200)
                .body("genres[0].id[0]", equalTo(genreId));
    }

    @Test
    public void getBook_byNonExistingGenreId_shouldReturnErrorMessageAnd404() {
        int genreId = 767567567;
        given()
                .spec(requestSpec)
                .when()
                .get("/books/genre/" + genreId)
                .then()
                .assertThat()
                .statusCode(404)
                .body("message", equalTo("Genre with ID '" + genreId + "' not found"));
    }

    private void initBooks() {
        book1 = Book.builder()
                .isbn("9780679735779")
                .title("American Psycho")
                .author("Bret Easton Ellis")
                .publicationYear("1991")
                .price("9.89")
                .quantity(2)
                .genres(Arrays.asList(
                        Genre.builder().id(15L).build(),
                        Genre.builder().id(14L).build(),
                        Genre.builder().id(7L).build()
                )).build();

        book2 = Book.builder()
                .isbn("9780393341768")
                .title("A Clockwork Orange")
                .author("Anthony Burgess")
                .publicationYear("2019")
                .price("12.59")
                .quantity(1)
                .genres(Arrays.asList(
                        Genre.builder().id(3L).build()
                )).build();
    }
}
