package com.epam.jdi.httptests.examples.custom;

import com.epam.http.response.RestResponse;
import com.epam.jdi.httptests.examples.rest.InitRestTests;
import com.epam.jdi.services.RestService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestDataFactory.pathParams;
import static com.epam.http.requests.RequestDataFactory.queryParams;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.services.RestService.*;
import static org.testng.Assert.assertTrue;

/**
 * This class is using for error handling cases for RestServiceAPI
 */
public class ErrorMessageRestServiceTests extends InitRestTests {

    public static final String INVALID_BOOK_ID = "0";
    public static final String NON_EXISTENT_BOOK_ID = "415345";
    public static final String NON_EXISTENT_USER_ID = "900114";
    public static final int NOT_FOUND_CODE = 404;
    public static final int ERROR_CODE = 400;

    @BeforeClass
    public void initService() {
        init(RestService.class, spec);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = ".*Bad raResponse:.*")
    public void getBookByNonexistentId() {
        RestResponse response = getBookById.call(pathParams().add("id", NON_EXISTENT_BOOK_ID));
        response.hasErrors()
                .statusCode(NOT_FOUND_CODE);
        assertTrue(response.getBody().contains("Book with ID '" + NON_EXISTENT_BOOK_ID + "' not found"));
        response.isEmpty();
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = ".*Bad raResponse:.*")
    public void getBookByInvalidId() {
        RestResponse response = getBookById.call(pathParams().add("id", INVALID_BOOK_ID));
        response.hasErrors()
                .statusCode(ERROR_CODE);
        assertTrue(response.getBody().contains("getBookById.id: must be greater than or equal to 1"));
        response.isEmpty();
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = ".*Bad raResponse:.*")
    public void getUserByInvalidEmail() {
        String invalidEmail = "";
        RestResponse response = getUserByEmail
                .call(queryParams().add("email", invalidEmail));
        response.hasErrors()
                .statusCode(ERROR_CODE);
        assertTrue(response.getBody().contains("must not be blank"));
        response.isEmpty();
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = ".*Bad raResponse:.*")
    public void deleteNotExistingUser() {
        RestResponse response = deleteUserById
                .call(pathParams().add("id", NON_EXISTENT_USER_ID));
        response.hasErrors()
                .statusCode(NOT_FOUND_CODE);
        assertTrue(response.getBody().contains("User with ID '" + NON_EXISTENT_USER_ID + "' not found!"));
        response.isEmpty();
    }
}
