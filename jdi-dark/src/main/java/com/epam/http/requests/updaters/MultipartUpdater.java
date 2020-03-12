package com.epam.http.requests.updaters;


import com.epam.http.requests.RequestData;
import com.epam.jdi.tools.func.JFunc;
import com.epam.jdi.tools.pairs.Pair;
import io.restassured.builder.MultiPartSpecBuilder;

import static com.epam.jdi.tools.LinqUtils.map;

public class MultipartUpdater extends SpecUpdater<com.epam.http.annotations.MultiPart, Pair<String, String>> {
    public MultipartUpdater() { this(RequestData::new); }
    public MultipartUpdater(JFunc<RequestData> dataFunc) {
        super(
            mp -> {
                RequestData data = dataFunc.execute();
                data.multiPartSpecifications.addAll(
                        map(mp, p -> new MultiPartSpecBuilder(p.key)
                                .controlName(p.value).build()));
                return data; },
            mp -> new Pair<>(mp.fileName(), mp.controlName()),
            Pair::new
        );
    }
}
