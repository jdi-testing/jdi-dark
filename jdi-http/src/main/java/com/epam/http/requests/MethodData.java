package com.epam.http.requests;

import com.epam.jdi.tools.DataClass;

import static com.epam.http.requests.RestMethodTypes.GET;

/**
 * Represents the main HTTP request data, which are URL and request type.
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
public class MethodData extends DataClass {
    private String url = "";
    private RestMethodTypes type = GET;

    public String getUrl() {
        return url;
    }
    public RestMethodTypes getType() {
        return type;
    }
    public MethodData(String url, RestMethodTypes type) {
        this.url = url;
        this.type = type;
    }
}
