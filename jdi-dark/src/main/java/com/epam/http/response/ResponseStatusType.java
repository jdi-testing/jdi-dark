package com.epam.http.response;

import java.util.List;

import static com.epam.jdi.tools.EnumUtils.getAllEnumValues;
import static com.epam.jdi.tools.LinqUtils.first;

/**
 * Enumeration of response codes.
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
public enum ResponseStatusType {
    OK(2), REDIRECT(3), CLIENT_ERROR(4), SERVER_ERROR(5);

    public int firstNumber;

    public static List<ResponseStatusType> allValues() {
        return getAllEnumValues(ResponseStatusType.class);
    }

    ResponseStatusType(int num) {
        firstNumber = num;
    }

    public static ResponseStatusType getStatusTypeFromCode(int code) {
        int firstNum = code / 100;
        return first(allValues(), type -> type.firstNumber == firstNum);
    }
}
