package com.epam.http.requests.updaters;

import com.epam.http.annotations.QueryParameter;
import com.epam.http.requests.RequestData;
import com.epam.jdi.tools.func.JFunc;
import com.epam.jdi.tools.pairs.Pair;

public class QueryParamsUpdater extends SpecUpdater<QueryParameter, Pair<String, String>> {
    public QueryParamsUpdater() {
        this(RequestData::new);
    }

    public QueryParamsUpdater(JFunc<RequestData> dataFunc) {
        super(
                qpList -> {
                    RequestData data = dataFunc.execute();
                    data.queryParams.addAll(qpList);
                    return data;
                },
                qp -> new Pair<>(qp.name(), qp.value()),
                Pair::new
        );
    }
}
