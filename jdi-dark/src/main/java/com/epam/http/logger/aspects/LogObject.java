package com.epam.http.logger.aspects;

import com.epam.http.requests.RequestData;
import com.epam.http.requests.RestMethod;

import java.util.Arrays;
import java.util.List;

public class LogObject {

    private String uuid;
    private RestMethod restMethod;
    private List<RequestData> rd;

    public LogObject(RestMethod restMethod) {
        this.restMethod = restMethod;
        this.rd = Arrays.asList(restMethod.getUserData(), restMethod.getData());
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public RestMethod getRestMethod() {
        return restMethod;
    }

    public List<RequestData> getRd() {
        return rd;
    }
}
