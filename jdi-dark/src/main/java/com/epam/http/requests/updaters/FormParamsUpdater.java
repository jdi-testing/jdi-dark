package com.epam.http.requests.updaters;


import com.epam.http.requests.RequestData;
import com.epam.jdi.tools.func.JFunc;
import com.epam.jdi.tools.pairs.Pair;

public class FormParamsUpdater extends SpecUpdater<com.epam.http.annotations.FormParameter, Pair<String, String>> {
    public FormParamsUpdater() { this(RequestData::new); }
    public FormParamsUpdater(JFunc<RequestData> dataFunc) {
        super(
            fp -> {
                RequestData data = dataFunc.execute();
                data.formParams.addAll(fp);
                return data;
            },
            c -> new Pair<>(c.name(), c.value()),
            Pair::new
        );
    }
}
