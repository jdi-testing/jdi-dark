package com.epam.http.requests;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class RestDataMethod<T> extends RestMethod {

    @SuppressWarnings("unchecked")
    public RestDataMethod(Field field) {
        Type type = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
        if (type instanceof Class) {
            super.responseType = "Object";
            super.dataType = (Class<T>) type;
        } else {
            String responseTypeName = ((ParameterizedType) type).getRawType().getTypeName();
            super.responseType = responseTypeName.substring(responseTypeName.lastIndexOf(".") + 1);
            try {
                super.dataType = Class.forName("[L" + ((ParameterizedType) type).getActualTypeArguments()[0].getTypeName() + ";");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
