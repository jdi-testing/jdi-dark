package com.epam.jdi.httptests;

import com.epam.jdi.tools.DataClass;

/**
 * Created by Roman_Iovlev on 11/3/2017.
 */
public class Info extends DataClass<Info> {
    public Object args;
    public IdName headers;
    public String origin, url;
}
