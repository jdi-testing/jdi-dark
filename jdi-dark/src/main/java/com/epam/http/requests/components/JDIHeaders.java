package com.epam.http.requests.components;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.internal.common.assertion.AssertParameter.notNull;

/**
 * This class is created to allow adding/removing headers of the fly
 * This is basically a wrapper around ArrayList
 */

public class JDIHeaders {

    private ArrayList<Header> headers;

    public JDIHeaders(Header... headers) {
        this(Arrays.asList(headers));
    }

    public JDIHeaders(List<Header> headers) {
        notNull(headers, "Headers");
        this.headers = new ArrayList<>(headers);
    }

    /**
     * This method is a mere example how one can use this class.
     * In this case the following method allows to build headers out
     * of a string array object like this:
     *  JDIHeader headers = new JDIHeader(
     *      new String[][] {{"Header_01", "Value_01"}, {"Header_02", "Value_02"},
     *      {"MultiValuesHeader", "multiValue_01", "multiValue_02", "multiValue_03}});
     * @param listOfHeaderStrings
     */
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

    /**
     * Checks if header's list is empty
     * @return boolean
     */
    public boolean isEmpty() {
        return this.headers.size() == 0;
    }

    /**
     * Add a header in the list using RestAssured Header object
     * @return boolean
     */
    public boolean add(Header header) {
        return this.headers.add(header);
    }

    /**
     * Add a header in the list using headers Name and Value strings
     * @return boolean
     */
    public boolean add(String name, String value) {
        return this.headers.add(new Header(name, value));
    }

    /**
     * Adds to headers all headers from given List of RestAssured Header objects
     * @return boolean
     */
    public boolean addAll(ArrayList<Header> headers) {
        for (Header header : headers)
            if (!add(header))
                return false;
        return true;
    }

    /**
     * Adds to headers all headers from given JDIHeaders object
     * @return boolean
     */
    public boolean addAll(JDIHeaders headers) {
        for (Header header : headers.asList())
            if (!add(header))
                return false;
        return true;
    }

    /**
     * Gives access to inner ArrayList for direct usage
     * @return ArrayList<Header>
     */
    public ArrayList<Header> asList() {
        return this.headers;
    }

    /**
     * Returns array size
     * @return int
     */
    public int size() {
        return this.headers.size();
    }

    /**
     * Returns headers as RestAssured Headers object,
     * which may be handy for some advanced RestAssured shenanigans
     * @return Headers
     */
    public Headers asRaHeaders() {
        Headers raHeaders = new Headers(this.headers);
        return raHeaders;
    }

    /**
     * Clears the list of headers
     * @return void
     */
    public void clear() {
        this.headers.clear();
    }

    /**
     * Returns True if list size is bigger than 0;
     * @return boolean
     */
    public boolean any() {
        return this.size() > 0;
    }
}

