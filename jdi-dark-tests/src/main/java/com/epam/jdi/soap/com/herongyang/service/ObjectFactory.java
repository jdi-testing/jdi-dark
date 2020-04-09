package com.epam.jdi.soap.com.herongyang.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.herongyang.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RegistrationRequestGuest_QNAME = new QName("", "Guest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.herongyang.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegistrationResponse }
     * 
     */
    public RegistrationResponse createRegistrationResponse() {
        return new RegistrationResponse();
    }

    /**
     * Create an instance of {@link RegistrationRequest }
     * 
     */
    public RegistrationRequest createRegistrationRequest() {
        return new RegistrationRequest();
    }

    /**
     * Create an instance of {@link RegistrationResponse.Confirmation }
     * 
     */
    public RegistrationResponse.Confirmation createRegistrationResponseConfirmation() {
        return new RegistrationResponse.Confirmation();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "Guest", scope = RegistrationRequest.class)
    public JAXBElement<String> createRegistrationRequestGuest(String value) {
        return new JAXBElement<String>(_RegistrationRequestGuest_QNAME, String.class, RegistrationRequest.class, value);
    }

}
