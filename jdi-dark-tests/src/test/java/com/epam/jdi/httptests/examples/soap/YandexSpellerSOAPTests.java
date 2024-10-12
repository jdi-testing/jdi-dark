package com.epam.jdi.httptests.examples.soap;

import com.epam.jdi.soap.YandexSpeller;
import com.epam.jdi.soap.net.yandex.speller.services.spellservice.*;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;

public class YandexSpellerSOAPTests {

    @BeforeTest
    public void before() {
        init(YandexSpeller.class);
    }

    @Test(enabled = false)
    public void checkTestResponse() {
        CheckTextResponse response = YandexSpeller.checkText.callSoap(new CheckTextRequest().withText("soap").withLang("en"));
        Assertions.assertThat(response.getSpellResult().getError().size()).isZero();
    }

    @Test(enabled = false)
    public void checkTestResponseError() {
        CheckTextResponse response = YandexSpeller.checkText.callSoap(new CheckTextRequest().withText("кортошка").withLang("ru"));
        SpellError spellError = response.getSpellResult().getError().get(0);
        Assertions.assertThat(spellError.getWord()).isEqualTo("кортошка");
        Assertions.assertThat(spellError.getS()).contains("картошка");
    }

    @Test(enabled = false)
    public void checkTextsResponse() {
        CheckTextsResponse response = YandexSpeller.checkTexts.callSoap(new CheckTextsRequest().withText("soap").withLang("en"));
        response.getArrayOfSpellResult().getSpellResult().forEach(e -> Assertions.assertThat(e.getError().size()).isZero());
    }

    @Test(enabled = false)
    public void checkTextsResponseError() {
        CheckTextsResponse response = YandexSpeller.checkTexts
                .callSoap(new CheckTextsRequest().withText("saop").withLang("en"));
        SpellError spellError = response.getArrayOfSpellResult().getSpellResult().get(0).getError().get(0);
        Assertions.assertThat(spellError.getWord()).isEqualTo("saop");
        Assertions.assertThat(spellError.getS()).contains("shop", "stop", "soap");
    }
}
