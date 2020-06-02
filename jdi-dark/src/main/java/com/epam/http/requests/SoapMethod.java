package com.epam.http.requests;

import com.epam.http.annotations.SOAP12;
import com.epam.http.annotations.SOAPAction;
import com.epam.http.annotations.SOAPNamespace;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.StreamReaderDelegate;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;

import static com.epam.http.ExceptionHandler.exception;
import static com.epam.http.requests.RequestDataFactory.headers;
import static com.epam.jdi.tools.ReflectionUtils.getGenericTypes;

public class SoapMethod<T, S> extends RestMethod {
    boolean soap12;
    String envelop, soapHeader, action, namespace;
    Class<T> req;
    Class<S> resp;
    Object[][] headers;

    @SuppressWarnings("unchecked")
    public SoapMethod(Field field, Class<T> c) {
        this.soap12 = field.isAnnotationPresent(SOAP12.class) || c.isAnnotationPresent(SOAP12.class);
        this.soapHeader = "   <soap:Header/>";
        this.action = field.isAnnotationPresent(SOAPAction.class) ? field.getAnnotation(SOAPAction.class).value() :
                field.getName();
        this.namespace = c.isAnnotationPresent(SOAPNamespace.class) ? " xmlns=\"" +
                c.getAnnotation(SOAPNamespace.class).value() + "\" \n" :
                field.isAnnotationPresent(SOAPNamespace.class) ? " xmlns=\"" +
                        field.getAnnotation(SOAPNamespace.class).value() + "\" \n" : "";
        this.envelop = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n" +
                "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" \n" +
                this.namespace + (this.soap12 ? "xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">" :
                "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
        this.req = (Class<T>) getGenericTypes(field)[0];
        this.resp = (Class<S>) getGenericTypes(field)[1];
        this.headers = this.soap12 ? new Object[][]{{"Content-Type", "application/soap+xml;charset=UTF-8"},
                {"Action", action}} : new Object[][]{{"Content-Type", "text/xml;charset=UTF-8"},
                {"SOAPAction", action}};
    }

    public S callSoap(T object) {
        try {
            return getResponse(call(headers().addAll(headers).setBody(createSoapBody(object))).getBody());
        } catch (Exception ex) {
            throw exception(ex.toString());
        }
    }

    public SoapMethod<T, S> withSoapHeader(Object header) {
        try {
            this.soapHeader = "   <soap:Header>" + "\n" +
                    "   " + getXML(header) + "\n" +
                    "   </soap:Header>";
            return this;
        } catch (Exception ex) {
            throw exception(ex.getMessage());
        }
    }

    private String createSoapBody(T object) throws JAXBException {
        return this.envelop + "\n" +
                this.soapHeader + "\n" +
                "   <soap:Body>\n" +
                "   " + getXML(object) + "\n" +
                "   </soap:Body>\n" +
                "</soap:Envelope>";
    }

    private String getXML(Object object) throws JAXBException {
        if (object instanceof String) {
            return object.toString();
        }
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        StringWriter stringWriter = new StringWriter();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        marshaller.marshal(object, stringWriter);
        return stringWriter.toString();
    }

    @SuppressWarnings("unchecked")
    private S getResponse(String body) throws JAXBException, XMLStreamException, SOAPException, IOException {
        class XMLReaderWithoutNamespace extends StreamReaderDelegate {
            public XMLReaderWithoutNamespace(XMLStreamReader reader) {
                super(reader);
            }

            @Override
            public String getAttributeNamespace(int arg0) {
                return "";
            }

            @Override
            public String getNamespaceURI() {
                return "";
            }
        }
        InputStream is = new ByteArrayInputStream(body.getBytes());
        SOAPMessage request = MessageFactory.newInstance(soap12 ? SOAPConstants.SOAP_1_2_PROTOCOL :
                SOAPConstants.SOAP_1_1_PROTOCOL).createMessage(null, is);
        SOAPBody soapBody = request.getSOAPBody();
        if (soapBody.hasFault()) {
            throw new SOAPException(soapBody.getFault().getFaultString());
        }
        if (resp == String.class) {
            return (S) soapBody.getTextContent().trim();
        }
        XMLStreamReader xsr = XMLInputFactory.newFactory()
                .createXMLStreamReader(new StringReader(body.replaceAll("\\n", "")));
        XMLReaderWithoutNamespace xr = new XMLReaderWithoutNamespace(xsr);
        JAXBContext jc = JAXBContext.newInstance(resp);
        Unmarshaller u = jc.createUnmarshaller();
        while (xr.hasNext()) {
            xr.nextTag();
            if (xr.getName().getLocalPart().equals(resp.getSimpleName())) {
                break;
            }
        }
        return u.unmarshal(xr, resp).getValue();
    }

}
