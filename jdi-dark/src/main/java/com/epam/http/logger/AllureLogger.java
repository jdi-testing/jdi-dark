package com.epam.http.logger;

import io.qameta.allure.Allure;
import io.qameta.allure.model.StepResult;

import java.util.UUID;

import static io.qameta.allure.aspects.StepsAspects.getLifecycle;
import static io.qameta.allure.model.Status.FAILED;
import static io.qameta.allure.model.Status.PASSED;

public class AllureLogger {
    public static boolean writeToAllure = true;

    public static String startStep(String message, String requestData) {
        StepResult step = new StepResult().withName(message).withStatus(PASSED);
        String uuid = UUID.randomUUID().toString();
        getLifecycle().startStep(uuid, step);
        attachRequest(message, requestData);
        return uuid;
    }

    public static void failStep(String uuid) {
        if (!writeToAllure) return;
        getLifecycle().updateStep(uuid, s -> s.withStatus(FAILED));
        getLifecycle().stopStep();
    }

    public static void passStep(String responseData, String stepUuid) {
        if (!writeToAllure) return;
        attachResponse(responseData);
        getLifecycle().updateStep(stepUuid, s -> s.withStatus(PASSED));
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
