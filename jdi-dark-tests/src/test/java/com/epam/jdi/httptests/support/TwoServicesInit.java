package com.epam.jdi.httptests.support;

import com.epam.jdi.services.ServiceNoDomainAnnotation;
import org.testng.annotations.BeforeSuite;

import static com.epam.http.requests.ServiceInit.init;

public class TwoServicesInit {
    public static ServiceNoDomainAnnotation yahoo;
    public static ServiceNoDomainAnnotation google;

    @BeforeSuite
    public static void startJettyHttps() {
        yahoo = init(ServiceNoDomainAnnotation.class, "https://yahoo.com");
        google = init(ServiceNoDomainAnnotation.class, "https://google.com");
    }
}
