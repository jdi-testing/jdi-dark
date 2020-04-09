package com.epam.jdi.httptests.examples.soap;

import com.epam.jdi.soap.com.herongyang.service.HerongYangService;
import com.epam.jdi.soap.com.herongyang.service.ObjectFactory;
import com.epam.jdi.soap.com.herongyang.service.RegistrationRequest;
import com.epam.jdi.soap.com.herongyang.service.RegistrationResponse;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.epam.http.requests.ServiceInit.init;

public class HerongYangServiceTests {

    @BeforeTest
    public void before() {
        init(HerongYangService.class);
    }

    @Test
    public void checkHelloResponse() {
        String response = HerongYangService.hello.callSoap("Hello from client.");
        Assertions.assertThat(response).isEqualTo("Hello from server - herongyang.com.");
    }

    @Test
    public void checkRegistrationResponse() throws DatatypeConfigurationException {
        ObjectFactory objectFactory = new ObjectFactory();
        RegistrationResponse response = HerongYangService.registration.callSoap(new RegistrationRequest()
                .withEvent("OpenGame")
                .withDate(DatatypeFactory.newInstance().newXMLGregorianCalendar("2008-08-08"))
                .withContent(objectFactory.createRegistrationRequestGuest("Herong Yang"),
                        objectFactory.createRegistrationRequestGuest("Joe Smith"),
                        objectFactory.createRegistrationRequestGuest("Bill Wang")));
        Assertions.assertThat(response.getConfirmation().stream().map(RegistrationResponse.Confirmation::getGuest)
                .collect(Collectors.toList())).isEqualTo(Arrays.asList("Herong Yang", "Joe Smith", "Bill Wang"));
    }
}
