package com.epam.http.requests.components;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.internal.common.assertion.AssertParameter.notNull;

public class JDIHeaders {

    private ArrayList<Header> headers;

    public JDIHeaders(Header... headers) {
        this(Arrays.asList(headers));
    }

    public JDIHeaders(List<Header> headers) {
        notNull(headers, "Headers");
        this.headers = new ArrayList<>(headers);
    }

    public JDIHeaders(String[][] listOfHeaderStrings) {
        this.headers = new ArrayList<>();
        for (String[] headerArray: listOfHeaderStrings) {
            if (headerArray.length < 2) {
                throw new IllegalArgumentException("Not sufficient arguments to determine Name and Value for a header");
            }
            else if (headerArray.length == 2) {
                this.headers.add(new Header(headerArray[0], headerArray[1]));
            }
            else {
                String name = headerArray[0];
                String[] valuesArray = Arrays.copyOfRange(headerArray, 1, headerArray.length);
                String value = String.join(",", valuesArray);
                this.headers.add(new Header(name, value));
            }
        }
    }

    public boolean isEmpty() {
        return this.headers.size() == 0;
    }

    public boolean add(Header header) {
        return this.headers.add(header);
    }

    public boolean add(String name, String value) {
        return this.headers.add(new Header(name, value));
    }

    public boolean addAll(ArrayList<Header> headers) {
        for (Header header : headers)
            if (!add(header))
                return false;
        return true;
    }

    public boolean addAll(JDIHeaders headers) {
        for (Header header : headers.asList())
            if (!add(header))
                return false;
        return true;
    }

    public ArrayList<Header> asList() {
        return this.headers;
    }

    public int size() {
        return this.headers.size();
    }

    public Headers asRaHeaders() {
        Headers raHeaders = new Headers(this.headers);
        return raHeaders;
    }

    public void clear() {
        this.headers.clear();
    }

    public boolean any() {
        return this.size() > 0;
    }
}

