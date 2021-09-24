package com.epam.http.requests.updaters;

import com.epam.http.requests.RequestData;
import com.jdiai.tools.func.JFunc;
import io.restassured.http.Header;
import io.restassured.http.Headers;

import java.util.ArrayList;
import java.util.List;

public class HeaderUpdater extends SpecUpdater<com.epam.http.annotations.Header, Header> {
    public HeaderUpdater() {
        this(RequestData::new);
    }

    public HeaderUpdater(JFunc<RequestData> dataFunc) {
        super(
                headers -> {
                    RequestData data = dataFunc.execute();
                    List<Header> headerList = new ArrayList<>(data.headers.asList());
                    headerList.addAll(headers);
                    data.headers = new Headers(headerList);
                    return data;
                },
                h -> new Header(h.name(), h.value()),
                Header::new
        );
    }
}
