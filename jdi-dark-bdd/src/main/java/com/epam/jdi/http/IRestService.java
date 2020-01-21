package com.epam.jdi.http;

import com.epam.http.requests.RestMethod;

public interface IRestService {
    RestMethod getGetMethod();

    RestMethod getPostMethod();

    RestMethod getPutMethod();

    RestMethod getPatch();

    RestMethod getDelete();

    RestMethod getStatus();
}
