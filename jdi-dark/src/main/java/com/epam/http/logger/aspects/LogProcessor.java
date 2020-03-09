package com.epam.http.logger.aspects;

import com.epam.http.requests.RestMethod;
import com.epam.http.response.RestResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class LogProcessor {

    @Pointcut(value = "execution(* com.epam.http.requests.RestMethod.call())")
    protected void callPointCut() { // empty
    }

    @Around("callPointCut()")
    public RestResponse darkLogging(ProceedingJoinPoint joinPoint) {
        RestMethod target = (RestMethod) joinPoint.getTarget();

        LogObject actionObject = new LogObject(target, Arrays.asList(target.getUserData(), target.getData()));
        try {
            LoggerHelper.LOG_REQUEST.execute(actionObject);
            RestResponse proceed = (RestResponse) joinPoint.proceed();
            LoggerHelper.LOG_RESPONSE.execute(proceed, actionObject.getUuid());
            return proceed;
        } catch (Throwable ex) {
            throw LoggerHelper.LOG_FAILURE.execute(actionObject, ex);
        }
    }
}