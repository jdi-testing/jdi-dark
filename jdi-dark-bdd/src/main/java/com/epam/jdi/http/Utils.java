package com.epam.jdi.http;

import com.epam.http.performance.PerformanceResult;
import com.epam.http.requests.RestMethod;
import com.epam.http.response.RestResponse;
import io.restassured.http.ContentType;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class Utils {
    public static final ThreadLocal<Object> service = new ThreadLocal<>();
    public static final ThreadLocal<PerformanceResult> performanceResult = new ThreadLocal<>();
    public static final ThreadLocal<RestResponse> restResponse = new ThreadLocal<>();
    public static final ThreadLocal<ContentType> requestContentType = new ThreadLocal<>();
    public static final ThreadLocal<HashMap<String, String>> preparedHeader = new ThreadLocal<>();

    protected static RestMethod getRestMethod(String restMethodName) throws IllegalAccessException, NoSuchFieldException {
        Field field = service.get().getClass().getDeclaredField(restMethodName);
        field.setAccessible(true);
        return (RestMethod) field.get(service.get());
    }

    protected RestMethod prepareRestMethod(String methodName) throws NoSuchFieldException, IllegalAccessException {
        RestMethod restMethod = getRestMethod(methodName);
        if (preparedHeader.get() != null) {
            for (Map.Entry<String, String> entry : preparedHeader.get().entrySet()) {
                restMethod.header.add(entry.getKey(), entry.getValue());
            }
        }
        if (requestContentType.get() != null) {
            restMethod.getData().setContentType(requestContentType.get());
        }
        return restMethod;
    }

}
