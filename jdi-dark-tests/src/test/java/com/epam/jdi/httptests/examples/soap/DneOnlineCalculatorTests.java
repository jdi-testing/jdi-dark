package com.epam.jdi.httptests.examples.soap;

import com.epam.jdi.soap.DneOnlineCalculator;
import com.epam.jdi.soap.org.tempuri.*;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;

public class DneOnlineCalculatorTests {

    @BeforeClass
    public void initTimeouts() {
        RestAssured.config = RestAssuredConfig.newConfig().httpClient(
                HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout",300000)
                        .setParam("http.socket.timeout",300000)
                        .setParam("http.connection-manager.timeout",300000)
        );
    }

    @BeforeMethod
    public void before() {
        init(DneOnlineCalculator.class);
    }

    @Test()
    public void checkAdd() {
        AddResponse response = DneOnlineCalculator.add.callSoap(new Add().withIntA(2).withIntB(3));
        Assertions.assertThat(response.getAddResult()).isEqualTo(5);
    }

    @Test
    public void checkDivide() {
        DivideResponse response = DneOnlineCalculator.divide.callSoap(new Divide().withIntA(6).withIntB(3));
        Assertions.assertThat(response.getDivideResult()).isEqualTo(2);
    }

    @Test
    public void checkMultiply() {
        MultiplyResponse response = DneOnlineCalculator.multiply.callSoap(new Multiply().withIntA(2).withIntB(3));
        Assertions.assertThat(response.getMultiplyResult()).isEqualTo(6);
    }

    @Test
    public void checkSubtract() {
        SubtractResponse response = DneOnlineCalculator.subtract.callSoap(new Subtract().withIntA(7).withIntB(3));
        Assertions.assertThat(response.getSubtractResult()).isEqualTo(4);
    }
}
