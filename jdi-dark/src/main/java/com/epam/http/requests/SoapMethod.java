package com.epam.http.requests;

import com.epam.http.annotations.SOAPAction;
import com.epam.http.annotations.SOAPNamespace;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;

import static com.epam.http.ExceptionHandler.exception;
import static com.epam.http.requests.RequestDataFacrtory.requestBody;
import static com.epam.jdi.tools.ReflectionUtils.getGenericTypes;

public class SoapMethod<T, S> extends RestMethod {
    String envelop, soapHeader, action, namespace;
    Class<T> req;
    Class<S> resp;

    @SuppressWarnings("unchecked")
    public SoapMethod(Field field) {
        this.envelop = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
        this.soapHeader = "   <soap:Header/>";
        this.action = field.getAnnotation(SOAPAction.class).value();
        this.namespace = field.isAnnotationPresent(SOAPNamespace.class) ? " xmlns=\"" + field.getAnnotation(SOAPNamespace.class).value() + "\"" : "";
        this.req = (Class<T>) getGenericTypes(field)[0];
        this.resp = (Class<S>) getGenericTypes(field)[1];
    }

    public S callSoap(T object) {
        try {
            header.addAll(new Object[][]{{"Content-Type", "text/xml;charset=UTF-8"},
                    {"SOAPAction", action}});
            return getResponse(call(requestBody(createSoapBody(object))).getBody());
        } catch (Exception ex) {
            throw exception(ex.getMessage());
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
                "   <soap:Body"+ this.namespace + ">\n" +
                "   " + getXML(object) + "\n" +
                "   </soap:Body>\n" +
                "</soap:Envelope>";
    }

    private String getXML(Object object) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        StringWriter stringWriter = new StringWriter();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        marshaller.marshal(object, stringWriter);
        return stringWriter.toString();
    }

    private S getResponse(String body) throws ParserConfigurationException, IOException, SAXException, JAXBException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource src = new InputSource();
        src.setCharacterStream(new StringReader(body));
        Document doc = builder.parse(src);
        Node node = doc.getElementsByTagName("soap:Body").item(0).getChildNodes().item(0);
        JAXBContext jc = JAXBContext.newInstance(resp);
        Unmarshaller u = jc.createUnmarshaller();
        return u.unmarshal(node, resp).getValue();
    }


}
