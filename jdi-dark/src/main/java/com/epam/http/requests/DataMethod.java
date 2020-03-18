package com.epam.http.requests;

import java.lang.reflect.Field;

import static com.epam.http.ExceptionHandler.exception;
import static com.epam.jdi.tools.ReflectionUtils.getGenericType;

public class DataMethod<T> extends RestMethod {
    Class<T> cl;
    public DataMethod(Field field) {
        this.cl = (Class<T>) getGenericType(field);
    }
    public T callObject(T object) {
        try {
            System.out.println("body : " + object.toString());
            System.out.println("request1 : " + new RequestData().set(rd -> rd.body = object).toString());
            T res = call(rd -> rd.body = object).asData(cl);
            System.out.println("response2 : " + res.toString());
           return res;
//            return call(rd -> rd.body = object).asData(cl);
        } catch (Exception ex) {
            throw exception("Can't init class '%s'", cl.getSimpleName());
        }
    }
}
