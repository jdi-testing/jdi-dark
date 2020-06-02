package com.epam.http.requests.updaters;

import com.epam.http.ExceptionHandler;
import com.epam.http.requests.RequestData;
import com.epam.jdi.tools.func.JFunc;
import com.epam.jdi.tools.pairs.Pair;

import java.lang.annotation.Annotation;

public class PathParamsUpdater extends SpecUpdater<Annotation, Pair<String, String>> {
    public PathParamsUpdater() {
        this(RequestData::new);
    }

    public PathParamsUpdater(JFunc<RequestData> dataFunc) {
        super(pathParams -> {
            RequestData rd = dataFunc.execute();
            rd.pathParams.addAll(pathParams);
            return rd;
        }, m -> {
            throw ExceptionHandler.exception("Not supported way to set Path params");
        }, Pair::new);
    }
}
