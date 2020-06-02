package com.epam.http.requests;

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
            String responseTypeName = ((ParameterizedType) type).getRawType().getTypeName();
            responseType = responseTypeName.substring(responseTypeName.lastIndexOf(".") + 1);
            try {
                this.cl = (Class<T>) Class.forName("[L" + ((ParameterizedType) type).getActualTypeArguments()[0].getTypeName() + ";");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public T postAsData(Object object) {
        return body(object).call().asData(cl, responseType);
    }

    public T callAsData() {
        return call().asData(cl, responseType);
    }

}
