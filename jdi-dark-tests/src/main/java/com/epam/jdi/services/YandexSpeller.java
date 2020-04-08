package com.epam.jdi.services;

import com.epam.http.annotations.*;
import com.epam.http.requests.SoapMethod;
import com.epam.jdi.dto.yandex.speller.services.spellservice.CheckTextRequest;
import com.epam.jdi.dto.yandex.speller.services.spellservice.CheckTextResponse;
import com.epam.jdi.dto.yandex.speller.services.spellservice.CheckTextsRequest;
import com.epam.jdi.dto.yandex.speller.services.spellservice.CheckTextsResponse;

@ServiceDomain("http://speller.yandex.net/services/spellservice")
public class YandexSpeller {

    @POST("")
    @Header(name = "Content-Type", value = "text/xml;charset=UTF-8")
    public static SoapMethod<CheckTextRequest, CheckTextResponse> checkText;

    @POST("")
    @Header(name = "Content-Type", value = "text/xml;charset=UTF-8")
    public static SoapMethod<CheckTextsRequest, CheckTextsResponse> checkTexts;

}
