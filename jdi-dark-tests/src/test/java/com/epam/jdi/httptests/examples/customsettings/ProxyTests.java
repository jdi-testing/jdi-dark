package com.epam.jdi.httptests.examples.customsettings;

import com.epam.jdi.httptests.support.WithJetty;
import io.restassured.specification.ProxySpecification;
import org.apache.commons.io.FileUtils;
import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class ProxyTests extends WithJetty {

    private static HttpProxyServer proxyServer;

    @BeforeClass
    public void before() {
        proxyServer = DefaultHttpProxyServer.bootstrap().withPort(8888).withAllowLocalOnly(true).start();
    }

    @AfterClass
    public void after() {
        proxyServer.stop();
        proxyServer = null;
        FileUtils.deleteQuietly(new File("littleproxy_cert"));
        FileUtils.deleteQuietly(new File("littleproxy_keystore.jks"));
    }

    @Test
    public void usingProxyWithSetProxySpecification() {
        final Map<String, String> params = new HashMap<>();
        params.put("firstName", "John");
        params.put("lastName", "Doe");
        getJettyService().getGreenJSON.call(rd -> {
            rd.setProxySpec("http", "localhost", 8888);
            rd.queryParams.addAll(params);
        }).isOk().assertThat().
                body("greeting.firstName", equalTo("John")).
                body("greeting.lastName", equalTo("Doe"));
    }

    @Test
    public void usingProxyAnnotationOnServiceLayer() {
        final Map<String, String> params = new HashMap<>();
        params.put("firstName", "John");
        params.put("lastName", "Doe");
        getJettyService().getGreenJSONWithProxyParams.call(rd -> rd.queryParams.addAll(params))
                .isOk().assertThat().
                body("greeting.firstName", equalTo("John")).
                body("greeting.lastName", equalTo("Doe"));
    }

    @Test
    public void usingProxySpecification() {
        final Map<String, String> params = new HashMap<>();
        params.put("firstName", "John");
        params.put("lastName", "Doe");
        getJettyService().getGreenJSON.call(rd -> {
            rd.queryParams.addAll(params);
            rd.proxySpec = ProxySpecification.host("localhost");
        }).isOk().assertThat().
                body("greeting.firstName", equalTo("John")).
                body("greeting.lastName", equalTo("Doe"));
    }


}
