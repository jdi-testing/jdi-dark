package com.epam.http.requests.updaters;


import com.epam.http.requests.RequestData;
import com.epam.jdi.tools.func.JFunc;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;

import java.util.ArrayList;
import java.util.List;

public class CookieUpdater extends SpecUpdater<com.epam.http.annotations.Cookie, Cookie> {
    public CookieUpdater() { this(RequestData::new); }
    public CookieUpdater(JFunc<RequestData> dataFunc) {
        super(
            cookies -> {
                RequestData data = dataFunc.execute();
                List<Cookie> headerList = new ArrayList<>(data.cookies.asList());
                headerList.addAll(cookies);
                data.cookies = new Cookies(headerList);
                return data;
            },
            c -> new Cookie.Builder(c.name(), c.value()).build(),
            (name, value) -> new Cookie.Builder(name, value).build()
        );
    }
}
