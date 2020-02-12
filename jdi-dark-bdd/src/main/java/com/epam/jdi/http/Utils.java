package com.epam.jdi.http;

import com.epam.http.annotations.ServiceDomain;
import com.epam.http.performance.PerformanceResult;
import com.epam.http.requests.MethodData;
import com.epam.http.requests.RestMethod;
import com.epam.http.requests.RestMethodTypes;
import com.epam.http.response.RestResponse;
import io.restassured.http.ContentType;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import static com.epam.http.ExceptionHandler.exception;
import static com.epam.http.requests.RestMethodTypes.GET;


public class Utils {
    public static final ThreadLocal<Object> service = new ThreadLocal<>();
    public static final ThreadLocal<PerformanceResult> performanceResult = new ThreadLocal<>();
    public static final ThreadLocal<RestResponse> restResponse = new ThreadLocal<>();
    public static final ThreadLocal<ContentType> requestContentType = new ThreadLocal<>();
    public static final ThreadLocal<HashMap<String, String>> preparedHeader = new ThreadLocal<>();

    public static RestMethod getRestMethod(String restMethodName) throws InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Field field = service.get().getClass().getDeclaredField(restMethodName);
        field.setAccessible(true);
        return (RestMethod) field.get(service.get());
    }
}
