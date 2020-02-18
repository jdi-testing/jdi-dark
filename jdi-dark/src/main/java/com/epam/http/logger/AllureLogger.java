package com.epam.http.logger;

import io.qameta.allure.Allure;
import io.qameta.allure.model.StepResult;

import java.util.UUID;

import static io.qameta.allure.aspects.StepsAspects.getLifecycle;
import static io.qameta.allure.model.Status.FAILED;
import static io.qameta.allure.model.Status.PASSED;

public class AllureLogger {
    public static boolean writeToAllure = true;

    public static void startStep(String message, String requestData) {
        if (!writeToAllure) return;

        StepResult step = new StepResult().withName(message).withStatus(PASSED);
        getLifecycle().startStep(UUID.randomUUID().toString(), step);
        attachRequest(message, requestData);
    }

    public static void failStep() {
        if (!writeToAllure) return;

        getLifecycle().updateStep(s -> s.setStatus(FAILED));
        getLifecycle().stopStep();
    }

    public static void passStep(String responseData) {
        if (!writeToAllure) return;

        attachResponse(responseData);
        getLifecycle().stopStep();
    }

    public static void attachRequest(String message, String requestData) {
        if (!writeToAllure) return;
        Allure.addAttachment("Request " + message, "text/html", requestData, "json");
    }

    public static void attachResponse(String responseData) {
        if (!writeToAllure) return;
        Allure.addAttachment("Response", "text/html", responseData, "json");
    }

}
