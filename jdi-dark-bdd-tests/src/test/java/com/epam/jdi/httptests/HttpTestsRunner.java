package com.epam.jdi.httptests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features/",
        glue = {"com/epam/jdi/httptests/steps", "com/epam/jdi/http/stepdefs/en"})
public class HttpTestsRunner extends AbstractTestNGCucumberTests {
}
