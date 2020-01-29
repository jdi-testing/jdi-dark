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

import static com.epam.jdi.httptests.support.WithJetty.JettyOption.RESET_REST_ASSURED_BEFORE_TEST;

public abstract class WithJetty {
//    public static final String itestPath;
//
//    static {
//        String fileSeparator = System.getProperty("file.separator");
//        itestPath = fileSeparator + "examples" + fileSeparator + "rest-assured-itest-java";
//    }

    private final JettyOption jettyOption;

    protected WithJetty() {
        this(RESET_REST_ASSURED_BEFORE_TEST);
    }

    protected WithJetty(JettyOption jettyOption) {
        this.jettyOption = jettyOption;
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void startJetty() throws Exception {
        Server server = new Server();

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
//        context.setConfigurations(new Configuration[]
//                {
//                        new AnnotationConfiguration(),
//                        new WebInfConfiguration(),
//                        new WebXmlConfiguration(),
//                        new MetaInfConfiguration(),
//                        new FragmentConfiguration(),
//                        new EnvConfiguration(),
//                        new PlusConfiguration(),
//                        new JettyWebXmlConfiguration()
//                });

        context.setContextPath("/");
        context.setParentLoaderPriority(true);
        server.setHandler(context);
        server.setConnectors(new Connector[]{http});
        server.start();
    }

    private static void dontSendDateHeader(Server server) {
        // Remove the sending of date header since it makes testing of logging much harder
        for (Connector y : server.getConnectors()) {
            y.getConnectionFactories().stream()
                    .filter(x -> x instanceof HttpConnectionFactory)
                    .map(x -> ((HttpConnectionFactory) x))
                    .map(HttpConnectionFactory::getHttpConfiguration)
                    .forEach(conf -> conf.setSendDateHeader(false));
        }
    }

    @Before
    public void setUpBeforeTest() {
        if (jettyOption == RESET_REST_ASSURED_BEFORE_TEST) {
            RestAssured.reset();
        }
    }

    private static File gotoProjectRoot() {
        return new File("../../.");
    }

//    private static boolean isExecutedFromMaven(String canonicalPath) {
//        return canonicalPath.contains(itestPath);
//    }

    @AfterClass
    public static void stopJetty() throws Exception {
        server.stop();
        server.join();
    }

    private static Server server;

    public enum JettyOption {
        RESET_REST_ASSURED_BEFORE_TEST,
        DONT_RESET_REST_ASSURED_BEFORE_TEST
    }
}
