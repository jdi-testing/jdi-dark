package com.epam.jdi.httptests.examples.soap;

import com.epam.jdi.soap.HerongYangService;
import com.epam.jdi.soap.com.herongyang.service.*;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.epam.http.requests.ServiceInit.init;

public class HerongYangServiceTests {

    @Test
    public void checkHello() {
        String response = getHerongYangService().hello.callSoap("Hello from client.");
        Assertions.assertThat(response).isEqualTo("Hello from server - herongyang.com.");
    }

    @Test
    public void checkRegistration() throws DatatypeConfigurationException {
        ObjectFactory objectFactory = new ObjectFactory();
        RegistrationResponse response = getHerongYangService().registration.callSoap(new RegistrationRequest()
                .withEvent("OpenGame")
                .withDate(DatatypeFactory.newInstance().newXMLGregorianCalendar("2008-08-08"))
                .withContent(objectFactory.createRegistrationRequestGuest("Herong Yang"),
                        objectFactory.createRegistrationRequestGuest("Joe Smith")));
        Assertions.assertThat(response.getConfirmation().stream().map(RegistrationResponse.Confirmation::getGuest)
                .collect(Collectors.toList())).isEqualTo(Arrays.asList("Herong Yang", "Joe Smith"));
    }

    @Test
    public void checkRefillOrder() throws DatatypeConfigurationException {
        RefillOrderResponse response = getHerongYangService().refillOrder.callSoap(new RefillOrderRequest()
                .withVersion("1.0")
        .withPatient(new PatientType().withName("Joe Smith")
                .withBirthDate(DatatypeFactory.newInstance().newXMLGregorianCalendar("1970-01-01")))
        .withPrescription(new PrescriptionType().withDoctor("Paul Gates").withDrug("Vitamin D 50000 IU")));
        Assertions.assertThat(response.getVersion()).isEqualTo("1.0");
        Assertions.assertThat(response.getOrderStatus().getNumber()).isEqualTo("20070707");
        Assertions.assertThat(response.getOrderStatus().getStatus()).isEqualTo("Verifying");
    }

    @Test
    public void checkRegistration12() throws DatatypeConfigurationException {
        ObjectFactory objectFactory = new ObjectFactory();
        RegistrationResponse response = getHerongYangService().registration12.callSoap(new RegistrationRequest()
                .withEvent("OpenGame")
                .withDate(DatatypeFactory.newInstance().newXMLGregorianCalendar("2008-08-08"))
                .withContent(objectFactory.createRegistrationRequestGuest("Herong Yang"),
                        objectFactory.createRegistrationRequestGuest("Joe Smith"),
                        objectFactory.createRegistrationRequestGuest("Bill Wang")));
        Assertions.assertThat(response.getConfirmation().stream().map(RegistrationResponse.Confirmation::getGuest)
                .collect(Collectors.toList())).isEqualTo(Arrays.asList("Herong Yang", "Joe Smith", "Bill Wang"));
    }

    @Test
    public void checkRefillOrder12() throws DatatypeConfigurationException {
        RefillOrderResponse response = getHerongYangService().refillOrder12.callSoap(new RefillOrderRequest()
                .withVersion("1.0")
                .withPatient(new PatientType().withName("Joe Smith")
                        .withBirthDate(DatatypeFactory.newInstance().newXMLGregorianCalendar("1970-01-01")))
                .withPrescription(new PrescriptionType().withDoctor("Paul Gates").withDrug("Vitamin D 50000 IU")));
        Assertions.assertThat(response.getVersion()).isEqualTo("1.0");
        Assertions.assertThat(response.getOrderStatus().getNumber()).isEqualTo("20070707");
        Assertions.assertThat(response.getOrderStatus().getStatus()).isEqualTo("Verifying");
    }

    public HerongYangService getHerongYangService() {
        return init(HerongYangService.class);
    }
}
