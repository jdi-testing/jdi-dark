package com.epam.http.requests.components;

import com.epam.jdi.tools.map.MapArray;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;

import java.util.ArrayList;
import java.util.List;

public class JDICookies {

    public Cookies commonCookies = new Cookies();
    public Cookies serviceCookies = new Cookies();
    public Cookies userCookies = new Cookies();

    @Override
    public String toString() {
        return "commonCookies=" + commonCookies.toString() +
                ", serviceCookies=" + serviceCookies.toString() +
                ", userCookies=" + userCookies.toString();
    }

    public MapArray<String, String> toMapArray() {
        List<Cookie> allCookies = new ArrayList<>();
        allCookies.addAll(commonCookies.asList());
        allCookies.addAll(serviceCookies.asList());
        allCookies.addAll(userCookies.asList());

        MapArray<String, String> mapArray = new MapArray<>();
        for (Cookie cookie : allCookies) {
            mapArray.add(cookie.getName(), cookie.getValue());
        }
        return mapArray;
    }
}