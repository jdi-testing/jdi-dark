package com.epam.http.requests.updaters;

import com.epam.http.ExceptionHandler;
import com.epam.http.requests.RequestData;
import com.epam.jdi.tools.func.JFunc;

public class NamedParamsUpdater extends SpecUpdater<com.epam.http.annotations.Method, String> {

    public NamedParamsUpdater() {
        this(RequestData::new);
    }

    public NamedParamsUpdater(JFunc<RequestData> dataFunc) {
        super(namedParams -> {
            RequestData rd = dataFunc.execute();
            rd.namedParams.addAll(namedParams);
            return rd;
        }, m -> {
            throw ExceptionHandler.exception("Not supported way to set Named params");
        }, (key, value) -> {
            throw ExceptionHandler.exception("Not supported way to set Named params");
        });
    }
}
