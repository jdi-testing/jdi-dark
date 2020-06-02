package com.epam.http.requests;

import com.epam.jdi.tools.func.JAction1;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class RestDataMethod<T> extends RestMethod {
    private Class<T> cl;
    private final String responseType;

    @SuppressWarnings("unchecked")
    public RestDataMethod(Field field) {
        Type type = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
        if (type instanceof Class) {
            responseType = "Object";
            this.cl = (Class<T>) type;
        } else {
            responseType = ((ParameterizedTypeImpl) type).getRawType().getSimpleName();
            try {
                this.cl = (Class<T>) Class.forName("[L" + ((ParameterizedType) type).getActualTypeArguments()[0].getTypeName() + ";");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public T postAsData(Object object) {
        return post(object).asData(cl, responseType);
    }

    public T callAsData() {
        return call().asData(cl, responseType);
    }

    public T callAsData(RequestSpecification requestSpecification) {
        return call(requestSpecification).asData(cl, responseType);
    }

    public T callAsSpecAsData(RequestSpecification requestSpecification) {
        return callAsSpec(requestSpecification).asData(cl, responseType);
    }

    public T callAsData(RestAssuredConfig restAssuredConfig) {
        return call(restAssuredConfig).asData(cl, responseType);
    }

    public T callAsData(JAction1<RequestData> action) {
        return call(action).asData(cl, responseType);
    }

}
