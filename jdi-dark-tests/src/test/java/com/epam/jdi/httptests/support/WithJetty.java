package com.epam.jdi.httptests.support;

import io.restassured.RestAssured;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.util.security.Constraint;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.util.Collections;

public abstract class WithJetty {
    public static final String itestPath;
    private static Server server;

    static {
        String fileSeparator = System.getProperty("file.separator");
        itestPath = fileSeparator + "examples" + fileSeparator + "rest-assured-itest-java";
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void startJetty() throws Exception {
        server = new Server();

        HttpConfiguration httpConfig = new HttpConfiguration();
        httpConfig.setSecureScheme("https");
        httpConfig.setSecurePort(8443);
        httpConfig.setOutputBufferSize(32768);

        ServerConnector http = new ServerConnector(server, new HttpConnectionFactory(httpConfig));
        http.setPort(8080);
        http.setIdleTimeout(30000);

        String warpath = "target/webapps/scalatra-webapp.war";

        WebAppContext context = new WebAppContext();
        context.setResourceBase(warpath);
        context.setConfigurations(new Configuration[]
                {
                        new AnnotationConfiguration(),
                        new WebInfConfiguration(),
                        new WebXmlConfiguration(),
                        new MetaInfConfiguration(),
                        new FragmentConfiguration(),
                        new EnvConfiguration(),
                        new PlusConfiguration(),
                        new JettyWebXmlConfiguration()
                });

        context.setContextPath("/");
//        context.setParentLoaderPriority(true);
        server.setHandler(context);
        server.setConnectors(new Connector[]{http});
        server.start();
    }


    @AfterClass
    public static void stopJetty() throws Exception {
        server.stop();
        server.join();
    }

}
