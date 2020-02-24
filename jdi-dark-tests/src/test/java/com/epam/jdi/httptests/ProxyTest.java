package com.epam.jdi.httptests;

import com.epam.http.requests.RequestData;
import com.epam.jdi.httptests.support.WithJetty;
import org.apache.commons.io.FileUtils;
import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.epam.http.requests.ServiceInit.init;

public class ProxyTest extends WithJetty {

    static HttpProxyServer proxyServer;

    @BeforeClass
    public void before() {
        proxyServer = DefaultHttpProxyServer.bootstrap().withPort(8888).withAllowLocalOnly(true).start();
        init(JettyService.class);
    }

    @AfterClass
    public void after() {
        proxyServer.stop();
        proxyServer = null;
        FileUtils.deleteQuietly(new File("littleproxy_cert"));
        FileUtils.deleteQuietly(new File("littleproxy_keystore.jks"));
    }

    @Test
    public void using_proxy_with_proxy_specification() {
        final Map<String, String> params = new HashMap<>();
        params.put("firstName", "John");
        params.put("lastName", "Doe");
        JettyService.getGreenJSON.call(RequestData.requestData(rd -> {
            //rd.requestPathParams((new Object[][]{{"firstName", "John"}, {"lastName", "Doe"}}));
            //rd.setProxy(new RequestSpecBuilder().setProxy("localhost").setPort(8888));
            rd.setProxySpecification("http", "localhost", 8888);
            rd.queryParams.addAll(params);
        }));


        /*given().
                proxy(host("localhost").and().withPort(8888).and().withScheme("http")).
                param("firstName", "John").
                param("lastName", "Doe").
                when().
                get("/greetJSON").
                then().
                header("Via", not(emptyOrNullString())).
                body("greeting.firstName", equalTo("John")).
                body("greeting.lastName", equalTo("Doe"));*/
    }


}
