package com.epam.jdi.httptests.examples.customsettings;

import com.epam.http.requests.ServiceSettings;
import com.epam.jdi.services.JettyServiceHttps;
import com.epam.jdi.httptests.support.WithJetty;
import io.restassured.authentication.CertAuthScheme;
import io.restassured.authentication.CertificateAuthSettings;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;
import static org.hamcrest.Matchers.equalTo;

public class SslWithCertificateTests extends WithJetty {

    private CertAuthScheme certificate;

    @BeforeClass
    public void before() {
        certificate = new CertAuthScheme();
        certificate.setPathToTrustStore("src/test/resources/jetty_localhost_client.jks");
        certificate.setTrustStorePassword("test1234");
        certificate.setX509HostnameVerifier(CertificateAuthSettings.certAuthSettings().allowAllHostnames().getX509HostnameVerifier());
    }

    @Test
    public void givenKeystoreDefinedUsingGivenWhenSpecifyingJksKeyStoreFileWithCorrectPasswordAllowsToUseSSL() throws Exception {
        getJettyServiceHttps().getHello.call().isOk().body("hello", equalTo("Hello Scalatra"));
    }

    @Test
    public void whenLastParamInGetRequestEndsWithEqualItsTreatedAsANoValueParam() {
        getJettyServiceHttps().getGreet.call(d -> {
            d.queryParams.add("firstName", "John");
            d.queryParams.add("lastName", "");
        }).isOk().assertThat().body("greeting", equalTo("Greetings John "));
    }

    private JettyServiceHttps getJettyServiceHttps() {
        return init(JettyServiceHttps.class, ServiceSettings.builder().authenticationScheme(certificate).build());
    }
}