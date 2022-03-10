package com.epam.jdi.soap;

import com.epam.http.annotations.POST;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.SoapMethod;
import com.epam.jdi.soap.net.yandex.speller.services.spellservice.CheckTextRequest;
import com.epam.jdi.soap.net.yandex.speller.services.spellservice.CheckTextResponse;
import com.epam.jdi.soap.net.yandex.speller.services.spellservice.CheckTextsRequest;
import com.epam.jdi.soap.net.yandex.speller.services.spellservice.CheckTextsResponse;

@ServiceDomain("http://speller.yandex.net/services/spellservice")
public class YandexSpeller {

    @POST
    public SoapMethod<CheckTextRequest, CheckTextResponse> checkText;

    @POST
    public SoapMethod<CheckTextsRequest, CheckTextsResponse> checkTexts;

}
