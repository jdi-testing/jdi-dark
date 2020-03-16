package com.epam.jdi.httptests.examples.customsettings;

import com.epam.http.requests.ServiceSettings;
import com.epam.http.requests.errorhandler.ErrorHandler;
import com.epam.http.response.ResponseStatusType;
import com.epam.http.response.RestResponse;
import com.epam.jdi.dto.Organization;
import com.epam.jdi.httptests.TrelloService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;
import static com.epam.http.response.ResponseStatusType.CLIENT_ERROR;
import static com.epam.jdi.httptests.TrelloService.createOrganization;
import static com.epam.jdi.httptests.utils.TrelloDataGenerator.generateOrganization;

public class ErrorHandlerTests {

    private ServiceSettings serviceSettings;

    @BeforeClass
    public void initServiceSettings() {
        ErrorHandler errorHandler = new ErrorHandler() {
            @Override
            public boolean hasError(RestResponse restResponse) {
                //only Client errors will be caught
                return ResponseStatusType.getStatusTypeFromCode(restResponse.getStatus().code) == CLIENT_ERROR;
            }

            @Override
            public void handleError(RestResponse restResponse) {
                Assert.fail("Exception is caught: " + restResponse.toString());
            }
        };
        serviceSettings = ServiceSettings.builder().errorHandler(errorHandler).build();
    }

    @BeforeClass(dependsOnMethods = {"initServiceSettings"})
    public void initService() {
        init(TrelloService.class, serviceSettings);
    }

    @Test(expectedExceptions = {AssertionError.class})
    public void assignBoardToOrganization() {
        //Create organization
        Organization organization = generateOrganization().set(o -> {
            o.name = null; o.displayName = null;
        });
        //this endpoint causes 400 error
        createOrganization(organization);
    }

}
