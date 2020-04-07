package com.epam.jdi.httptests.examples.soap;
import com.epam.jdi.dto.yandex.speller.services.spellservice.CheckTextRequest;
import com.epam.jdi.dto.yandex.speller.services.spellservice.CheckTextResponse;
import com.epam.jdi.services.YandexSpeller;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;

public class TestYandexSpellerSOAP {

    @BeforeTest
    public void before() {
        init(YandexSpeller.class);
    }

    @Test
    public void checkResponse(){
        CheckTextResponse response = YandexSpeller.checkText.callSoap(new CheckTextRequest().withText("кортошка").withLang("ru"));
        Assert.assertEquals(response.getSpellResult().getError().get(0).getWord(), "кортошка");
        Assert.assertEquals(response.getSpellResult().getError().get(0).getS().get(0), "картошка");
    }
}
