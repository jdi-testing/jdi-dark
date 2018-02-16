package com.epam.http.response;

/**
 * Created by Roman Iovlev on 14.02.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

import com.epam.jdi.tools.DataClass;
import io.restassured.response.Response;

public class ResponseStatus extends DataClass<ResponseStatus> {
    public final ResponseStatusType type;
    public final String text;
    public final int code;

    public ResponseStatus(Response response) {
        code = response.statusCode();
        type = ResponseStatusType.getStatusTypeFromCode(code);
        text = response.statusLine().substring(13);
    }
}
