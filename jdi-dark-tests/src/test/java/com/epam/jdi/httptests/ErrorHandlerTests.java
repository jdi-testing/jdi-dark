package com.epam.jdi.httptests;

import com.epam.http.requests.errorhandler.ErrorHandler;
import com.epam.http.requests.ServiceSettings;
import com.epam.http.response.ResponseStatusType;
import com.epam.http.response.RestResponse;
import com.epam.jdi.dto.Organization;
import com.epam.jdi.httptests.utils.TrelloDataGenerator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.epam.http.requests.ServiceInit.init;
import static com.epam.http.response.ResponseStatusType.ERROR;

public class ErrorHandlerTests {

    private ServiceSettings serviceSettings;

    @BeforeClass
    public void initServiceSettings() {
        ErrorHandler errorHandler = new ErrorHandler() {
            @Override
            public boolean hasError(RestResponse restResponse) throws IOException {
                //only Client errors will be caught
                return ResponseStatusType.getStatusTypeFromCode(restResponse.getStatus().code) == ERROR;
            }

            @Override
            public void handleError(RestResponse restResponse) throws IOException {
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
        Organization organization = TrelloDataGenerator.generateOrganization();
        organization.setName(null);
        organization.setDisplayName(null);
        //this endpoint causes 400 error
        TrelloService.createOrganization(organization);
    }

}
