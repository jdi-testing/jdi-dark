package com.epam.jdi.httptests;

import com.epam.http.logger.HTTPLogger;
import com.epam.http.logger.ILogger;
import com.epam.http.requests.ErrorHandler;
import com.epam.http.requests.ServiceSettings;
import com.epam.http.response.ResponseStatusType;
import com.epam.http.response.RestResponse;
import com.epam.jdi.dto.Organization;
import com.epam.jdi.httptests.utils.TrelloDataGenerator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.epam.http.requests.ServiceInit.init;
import static com.epam.http.response.ResponseStatusType.ERROR;

public class ErrorHandlerTests {

    private ServiceSettings serviceSettings;
    ILogger logger = new HTTPLogger("JDI Error Handler");

    @BeforeClass
    public void iniServiceSettings() {
        ErrorHandler errorHandler = new ErrorHandler() {
            @Override
            public boolean hasError(RestResponse restResponse) throws IOException {
                //only Client errors will be caught
                return ResponseStatusType.getStatusTypeFromCode(restResponse.getStatus().code) == ERROR;
            }

            @Override
            public void handleError(RestResponse restResponse) throws IOException {
                throw new IOException("Error is caught: " + restResponse.getBody());
            }
        };
        serviceSettings = ServiceSettings.builder().errorHandler(errorHandler).build();
    }

    @BeforeClass
    public void initService() {
        init(TrelloService.class, serviceSettings);
    }

    @Test
    public void assignBoardToOrganization() {

        //Create organization
        Organization organization = TrelloDataGenerator.generateOrganization();
        organization.setName(null);
        organization.setDisplayName(null);
        //this endpoint causes 400 error
        TrelloService.createOrganization(organization);
    }

}
