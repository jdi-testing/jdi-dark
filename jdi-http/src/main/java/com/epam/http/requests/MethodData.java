package com.epam.http.requests;

/**
 * Created by Roman Iovlev on 14.02.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

import com.epam.jdi.tools.DataClass;

import static com.epam.http.requests.RestMethodTypes.GET;

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
