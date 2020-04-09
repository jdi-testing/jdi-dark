package com.epam.jdi.soap.net.yandex.speller.services.spellservice;

import com.epam.http.annotations.*;
import com.epam.http.requests.SoapMethod;

@ServiceDomain("http://speller.yandex.net/services/spellservice")
public class YandexSpeller {

    @POST("")
    public static SoapMethod<CheckTextRequest, CheckTextResponse> checkText;

    @POST("")
    public static SoapMethod<CheckTextsRequest, CheckTextsResponse> checkTexts;

}
