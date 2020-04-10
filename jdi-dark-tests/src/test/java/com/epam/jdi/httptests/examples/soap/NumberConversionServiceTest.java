package com.epam.jdi.httptests.examples.soap;

import com.epam.jdi.soap.NumberConversionService;
import com.epam.jdi.soap.com.dataaccess.webservicesserver.NumberToDollars;
import com.epam.jdi.soap.com.dataaccess.webservicesserver.NumberToDollarsResponse;
import com.epam.jdi.soap.com.dataaccess.webservicesserver.NumberToWords;
import com.epam.jdi.soap.com.dataaccess.webservicesserver.NumberToWordsResponse;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static com.epam.http.requests.ServiceInit.init;

public class NumberConversionServiceTest {

    @BeforeTest
    public void before() {
        init(NumberConversionService.class);
    }

    @Test
    public void checkNumberToDollars() {
        NumberToDollarsResponse response = NumberConversionService.numberToDollars.callSoap(new NumberToDollars()
        .withDNum(BigDecimal.valueOf(72500262.57)));
        Assertions.assertThat(response.getNumberToDollarsResult())
                .isEqualTo("seventy two million five hundred thousand two hundred and sixty two dollars and fifty seven cents");
    }

    @Test
    public void negativeNumberToDollars() {
        NumberToDollarsResponse response = NumberConversionService.numberToDollars.callSoap(new NumberToDollars()
                .withDNum(BigDecimal.valueOf(-5)));
        Assertions.assertThat(response.getNumberToDollarsResult()).isEqualTo("number too large dollars and number too large cents");
    }

    @Test
    public void checkNumberToWords() {
        NumberToWordsResponse response = NumberConversionService.numberToWords.callSoap(new NumberToWords()
        .withUbiNum(BigInteger.valueOf(90210)));
        Assertions.assertThat(response.getNumberToWordsResult()).isEqualTo("ninety thousand two hundred and ten ");
    }

    @Test
    public void negativeNumberToWords() {
        NumberToWordsResponse response = NumberConversionService.numberToWords.callSoap(new NumberToWords()
                .withUbiNum(BigInteger.valueOf(-5)));
        Assertions.assertThat(response.getNumberToWordsResult()).isEqualTo("number too large");
    }

    @Test
    public void checkNumberToDollars12() {
        NumberToDollarsResponse response = NumberConversionService.numberToDollars12.callSoap(new NumberToDollars()
                .withDNum(BigDecimal.valueOf(5093275.21)));
        Assertions.assertThat(response.getNumberToDollarsResult())
                .isEqualTo("five million ninety three thousand two hundred and seventy five dollars and twenty one cents");
    }

    @Test
    public void checkNumberToWords12() {
        NumberToWordsResponse response = NumberConversionService.numberToWords12.callSoap(new NumberToWords()
                .withUbiNum(BigInteger.valueOf(1234567890)));
        Assertions.assertThat(response.getNumberToWordsResult())
                .isEqualTo("one billion two hundred and thirty four million five hundred and sixty seven thousand eight hundred and ninety ");
    }

}
