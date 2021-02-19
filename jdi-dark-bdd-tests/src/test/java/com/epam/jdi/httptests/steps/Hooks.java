package com.epam.jdi.httptests.steps;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.epam.http.JdiHttpSettings.logger;

public class Hooks {
    @Before
    public void beforeStart(Scenario scenario) {
        logger.info("Start scenario %s", scenario.getName());
    }

    @After
    public void afterStart(Scenario scenario) {
        logger.info("Finish scenario %s. Status: %s", scenario.getName(), scenario.isFailed()? "Failed" : "Passes");
    }

/*    @BeforeStep
    public void beforeStep(Scenario scenario) {
        logger.info("Before step %s line %d", scenario.getName(), scenario.getLine());
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        logger.info("After step %s line %d", scenario.getName(), scenario.getLine());
    }*/
}
