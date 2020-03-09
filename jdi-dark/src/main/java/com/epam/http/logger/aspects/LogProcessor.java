package com.epam.http.logger.aspects;

import com.epam.http.requests.RestMethod;
import com.epam.http.response.RestResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogProcessor {

    @Pointcut(value = "execution(* com.epam.http.requests.RestMethod.call())")
    protected void callRDPointCut() { // empty
    }

    @Pointcut(value = "execution(* com.epam.http.requests.RestMethod.call(io.restassured.specification.RequestSpecification))")
    protected void callRSPointCut() { // empty
    }

    @Pointcut(value = "execution(* com.epam.http.requests.RestMethod.call(io.restassured.config.RestAssuredConfig))")
    protected void callRACPointCut() { // empty
    }

    @Around("callRDPointCut() || callRSPointCut() || callRACPointCut()")
    public RestResponse darkLogging(ProceedingJoinPoint joinPoint) {
        RestMethod target = (RestMethod) joinPoint.getTarget();

        LogObject logObject = new LogObject(target);
        try {
            LoggerHelper.LOG_REQUEST.execute(logObject);
            RestResponse proceed = (RestResponse) joinPoint.proceed();
            LoggerHelper.LOG_RESPONSE.execute(proceed, logObject.getUuid());
            return proceed;
        } catch (Throwable ex) {
            throw LoggerHelper.LOG_FAILURE.execute(logObject, ex);
        }
    }
}