package com.epam.jdi.services;

import com.epam.http.annotations.*;
import com.epam.http.requests.SoapMethod;
import com.epam.jdi.soap.net.yandex.speller.services.spellservice.CheckTextRequest;
import com.epam.jdi.soap.net.yandex.speller.services.spellservice.CheckTextResponse;
import com.epam.jdi.soap.net.yandex.speller.services.spellservice.CheckTextsRequest;
import com.epam.jdi.soap.net.yandex.speller.services.spellservice.CheckTextsResponse;

@ServiceDomain("http://speller.yandex.net/services/spellservice")
public class YandexSpeller {

    @POST("")
    @SOAPAction("http://speller.yandex.net/services/spellservice/checkText")
    public static SoapMethod<CheckTextRequest, CheckTextResponse> checkText;

    @POST("")
    @SOAPAction("http://speller.yandex.net/services/spellservice/checkTexts")
    public static SoapMethod<CheckTextsRequest, CheckTextsResponse> checkTexts;

}
