package com.epam.http.requests;

import com.epam.jdi.tools.func.JAction1;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;

import java.lang.reflect.Field;

import static com.epam.jdi.tools.ReflectionUtils.getGenericType;

public class DataMethod<T> extends RestMethod {
    Class<T> cl;

    @SuppressWarnings("unchecked")
    public DataMethod(Field field) {
        this.cl = (Class<T>) getGenericType(field);
    }

    public T callAsData(Object object) {
        return call(rd -> rd.body = object).asData(cl);
    }

    public T callAsData() {
        return call().asData(cl);
    }

    public T callAsData(RequestSpecification requestSpecification) {
        return call(requestSpecification).asData(cl);
    }

    public T callAsSpecAsData(RequestSpecification requestSpecification) {
        return callAsSpec(requestSpecification).asData(cl);
    }

    public T callAsData(RestAssuredConfig restAssuredConfig) {
        return call(restAssuredConfig).asData(cl);
    }

    public T callAsData(String queryParams) {
        return call(queryParams).asData(cl);
    }

    public T callPathParamsAsData(Object... pathParams) {
        return callPathParams(pathParams).asData(cl);
    }

    public T callAsData(RequestData requestData) {
        return call(requestData).asData(cl);
    }

    public T callAsData(JAction1<RequestData> action) {
        return call(action).asData(cl);
    }

}
