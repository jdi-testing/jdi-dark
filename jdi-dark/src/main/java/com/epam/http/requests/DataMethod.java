package com.epam.http.requests;

import com.epam.jdi.tools.func.JAction1;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class DataMethod<T> extends RestMethod {
    private Class<T> cl;
    private final Boolean asList;

    @SuppressWarnings("unchecked")
    public DataMethod(Field field) {
        Type type = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
        if (type instanceof Class) {
            asList = false;
            this.cl = (Class<T>) type;
        } else {
            asList = true;
            try {
                this.cl = (Class<T>) Class.forName("[L" + ((ParameterizedType) type).getActualTypeArguments()[0].getTypeName() + ";");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public T postAsData(Object object) {
        return post(object).asData(cl, asList);
    }

    public T callAsData() {
        return call().asData(cl, asList);
    }

    public T callAsData(RequestSpecification requestSpecification) {
        return call(requestSpecification).asData(cl, asList);
    }

    public T callAsSpecAsData(RequestSpecification requestSpecification) {
        return callAsSpec(requestSpecification).asData(cl, asList);
    }

    public T callAsData(RestAssuredConfig restAssuredConfig) {
        return call(restAssuredConfig).asData(cl, asList);
    }

    public T callAsData(String queryParams) {
        return call(queryParams).asData(cl, asList);
    }

    public T callPathParamsAsData(Object... pathParams) {
        return callPathParams(pathParams).asData(cl, asList);
    }

    public T callAsData(RequestData requestData) {
        return call(requestData).asData(cl, asList);
    }

    public T callAsData(JAction1<RequestData> action) {
        return call(action).asData(cl, asList);
    }

}
