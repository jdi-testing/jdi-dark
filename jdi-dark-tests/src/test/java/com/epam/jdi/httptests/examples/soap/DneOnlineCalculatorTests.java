package com.epam.jdi.httptests.examples.soap;

import com.epam.jdi.services.DneOnlineCalculator;
import com.epam.jdi.soap.org.tempuri.*;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;

public class DneOnlineCalculatorTests {

    @BeforeTest
    public void before() {
        init(DneOnlineCalculator.class);
    }

    @Test
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
