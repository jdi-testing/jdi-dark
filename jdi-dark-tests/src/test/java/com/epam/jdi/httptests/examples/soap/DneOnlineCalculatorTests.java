package com.epam.jdi.httptests.examples.soap;

import com.epam.jdi.soap.DneOnlineCalculator;
import com.epam.jdi.soap.org.tempuri.*;
import org.assertj.core.api.Assertions;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.ConnectException;
import static com.epam.http.JdiHttpSettings.logger;

import static com.epam.http.requests.ServiceInit.init;

public class DneOnlineCalculatorTests {
    private static final String skipMessage = "External Server is unavailable. Skip test";

    @BeforeClass
    public void before() {
        init(DneOnlineCalculator.class);
    }

    @Test()
    public void checkAdd() {
        try {
            AddResponse response = DneOnlineCalculator.add.callSoap(new Add().withIntA(2).withIntB(3));
            Assertions.assertThat(response.getAddResult()).isEqualTo(5);
        }
        catch (RuntimeException ex) {
            if (ex.getCause() instanceof ConnectException) {
                logger.info("checkAdd test is skipped due to Connection problems");
                throw new SkipException(skipMessage);
            }
            else {
                logger.error("checkAdd test is failed with message '%s' and exception %s", ex.getMessage(), ex.getCause().getClass().getName(), ex);
                throw ex;
            }
        }
    }

    @Test()
    public void checkDivide() {
        try {
            DivideResponse response = DneOnlineCalculator.divide.callSoap(new Divide().withIntA(6).withIntB(3));
            Assertions.assertThat(response.getDivideResult()).isEqualTo(2);
        }
        catch (RuntimeException ex) {
            if (ex.getCause() instanceof ConnectException) {
                logger.info("checkDivide test is skipped due to Connection problems");
                throw new SkipException(skipMessage);
            }
            else {
                logger.error("checkDivide test is failed with message '%s' and exception %s", ex.getMessage(), ex.getCause().getClass().getName(), ex);
                throw ex;
            }
        }
    }

    @Test()
    public void checkMultiply() {
        try {
            MultiplyResponse response = DneOnlineCalculator.multiply.callSoap(new Multiply().withIntA(2).withIntB(3));
            Assertions.assertThat(response.getMultiplyResult()).isEqualTo(6);
        }
        catch (RuntimeException ex) {
            if (ex.getCause() instanceof ConnectException) {
                logger.info("checkMultiply test is skipped due to Connection problems");
                throw new SkipException(skipMessage);
            }
            else {
                logger.error("checkMultiply test is failed with message '%s' and exception %s", ex.getMessage(), ex.getCause().getClass().getName(), ex);
                throw ex;
            }
        }
    }

    @Test()
    public void checkSubtract() {
        try {
            SubtractResponse response = DneOnlineCalculator.subtract.callSoap(new Subtract().withIntA(7).withIntB(3));
            Assertions.assertThat(response.getSubtractResult()).isEqualTo(4);
        }
        catch (RuntimeException ex) {
            if (ex.getCause() instanceof ConnectException) {
                logger.info("checkSubtract test is skipped due to Connection problems");
                throw new SkipException(skipMessage);
            }
            else {
                logger.error("checkSubtract test is failed with message '%s' and exception %s", ex.getMessage(), ex.getCause().getClass().getName(), ex);
                throw ex;
            }
        }
    }
}