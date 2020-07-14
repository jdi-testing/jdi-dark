package com.epam.jdi.services;

import com.epam.http.annotations.*;
import com.epam.http.requests.RestMethod;
import com.epam.jdi.dtoGenerated.Book;

import java.time.LocalDateTime;

import static com.epam.http.requests.RequestDataFactory.body;
import static com.epam.http.requests.RequestDataFactory.pathParams;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.format;

@RetryOnFailure
@ServiceDomain(value = "http://localhost:8080/")
public class RestService {

    @POST(value = "users/auth")
    @ContentType(JSON)
    public static RestMethod usersAuth;

    @GET(value = "status")
    public static RestMethod getStatus;

    @POST(value = "actuator/shutdown")
    @ContentType(JSON)
    public static RestMethod shutDown;

    @GET(value = "books/{id}")
    public static RestMethod getBookById;

    public static Book getBook (String bookId) {
        return getBookById.call(pathParams().add("id", bookId)).getRaResponse().as(Book.class);
    }

    @GET(value = "users/email/email")
    public static RestMethod getUserByEmail;

    @DELETE(value = "users/{id}")
    public static RestMethod deleteUserById;

    @POST(value = "books")
    @ContentType(JSON)
    public static RestMethod addBook;

    public static Book addUniqueBook () {
        return addBook.call(body(format("{\"title\": \"%s\", \"ISBN\": \"%s\", \"Price\": \"%s\", \"Quantity\": \"%s\"}",
                "Title" + LocalDateTime.now(),
                "ISBN" + LocalDateTime.now(),
                "Price" + LocalDateTime.now(),
                "Quantity" + LocalDateTime.now()
                )))
                .getRaResponse().as(Book.class);
    }

}
