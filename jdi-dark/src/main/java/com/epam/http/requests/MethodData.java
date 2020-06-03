package com.epam.http.requests;

import com.epam.jdi.tools.DataClass;

import static com.epam.http.requests.RestMethodTypes.GET;

/**
 * Represents the main HTTP request data, which are URL and request type.
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
public class MethodData extends DataClass<MethodData> {
    public String path = "";
    public RestMethodTypes type = GET;

    public MethodData(String path, RestMethodTypes type) {
        this.path = path;
        this.type = type;
    }
}
