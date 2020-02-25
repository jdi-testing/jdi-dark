package com.epam.http.response;

import com.epam.jdi.tools.DataClass;
import io.restassured.response.Response;

/**
 * Represents response status.
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
public class ResponseStatus extends DataClass<ResponseStatus> {
    public final ResponseStatusType type;
    public final String text;
    public final int code;

    public ResponseStatus(Response response) {
        code = response.statusCode();
        type = ResponseStatusType.getStatusTypeFromCode(code);
        text = response.statusLine().substring(13);
    }

    @Override
    public String toString() {
        return "ResponseStatus{" +
                "type=" + type +
                ", text='" + text + '\'' +
                ", code=" + code +
                '}';
    }
}
