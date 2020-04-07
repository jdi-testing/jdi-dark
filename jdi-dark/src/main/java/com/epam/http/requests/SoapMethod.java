package com.epam.http.requests;

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
import static com.epam.http.requests.RequestDataFacrtory.headers;
import static com.epam.jdi.tools.ReflectionUtils.getGenericTypes;

public class SoapMethod<Req, Resp> extends RestMethod {
    String name;
    Class<Req> req;
    Class<Resp> resp;

    public SoapMethod(Field field) {
        this.name = field.getName();
        this.req = (Class<Req>) getGenericTypes(field)[0];
        this.resp = (Class<Resp>) getGenericTypes(field)[1];
    }

    public Resp callSoap(Req object) {
        try {
            return getResponse(call(headers().add("SOAPAction", name)
                    .requestBody(createSoapBody(object))).getBody());
        } catch (Exception ex) {
            throw exception("Can't init class '%s'", resp.getSimpleName());
        }
    }

    private String createSoapBody(Req object) throws JAXBException {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                getXML(object) +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }

    private String getXML(Req object) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(req);
        Marshaller marshaller = context.createMarshaller();
        StringWriter stringWriter = new StringWriter();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        marshaller.marshal(object, stringWriter);
        return stringWriter.toString();
    }

    private Resp getResponse(String body) throws ParserConfigurationException, IOException, SAXException, JAXBException {
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
