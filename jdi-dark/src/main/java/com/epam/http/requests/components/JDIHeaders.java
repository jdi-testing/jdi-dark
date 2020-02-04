package com.epam.http.requests.components;

import com.epam.jdi.tools.map.MapArray;
import io.restassured.http.Header;

import java.util.ArrayList;
import java.util.List;

public class JDIHeaders {

    public List<Header> commonHeaders = new ArrayList<>();
    public MapArray<String, String> serviceHeaders = new MapArray<>();
    public MapArray<String, String> userHeaders = new MapArray<>();

}
