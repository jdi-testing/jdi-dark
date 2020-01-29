package com.epam.jdi.httptests;

import io.restassured.RestAssured;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.security.Constraint;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.util.Collections;


public abstract class WithJetty {
    public static final String itestPath;
    private static Server server;

    static {
        String fileSeparator = System.getProperty("file.separator");
        itestPath = "\\jdi-dark-tests\\src\\test\\java\\com\\epam\\jdi\\httptests";
        System.out.println("itestPath " + itestPath);
    }

    private final JettyOption jettyOption;

    protected WithJetty() {
        this(JettyOption.RESET_REST_ASSURED_BEFORE_TEST);
    }

//    @Rule
//    public ExpectedException exception = ExpectedException.none();

    protected WithJetty(JettyOption jettyOption) {
        this.jettyOption = jettyOption;
    }

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

        HttpConfiguration httpsConfig = new HttpConfiguration(httpConfig);
        httpsConfig.addCustomizer(new SecureRequestCustomizer());

        String file = WithJetty
                .class
                .getClassLoader()
                .getResource("jetty_localhost_server.jks")
                .getFile();

        SslContextFactory sslContextFactory = new SslContextFactory(file);
        sslContextFactory.setKeyStorePassword("test1234");

        ServerConnector https = new ServerConnector(server,
                new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString()),
                new HttpConnectionFactory(httpsConfig));
        https.setPort(8443);
        https.setIdleTimeout(50000);

        String canonicalPath = new File(".").getCanonicalPath();
        String scalatraPath = "/examples/scalatra-webapp";

        // Security config
        Constraint constraint = new Constraint();
        constraint.setName(Constraint.__BASIC_AUTH);

        constraint.setRoles(new String[]{"user", "admin", "moderator"});
        constraint.setAuthenticate(true);

        ConstraintMapping mapping = new ConstraintMapping();
        mapping.setConstraint(constraint);
        mapping.setPathSpec("/secured/*");


        final String realmPath = scalatraPath + "/etc/realm.properties";
        LoginService loginService = new HashLoginService("MyRealm", isExecutedFromMaven(canonicalPath) ? gotoProjectRoot().getCanonicalPath() + realmPath : canonicalPath + realmPath);
        server.addBean(loginService);

        ConstraintSecurityHandler security = new ConstraintSecurityHandler();
        server.setHandler(security);
        security.setConstraintMappings(Collections.singletonList(mapping));
        security.setAuthenticator(new BasicAuthenticator());
        security.setLoginService(loginService);

        // End security config

        WebAppContext wac = new WebAppContext();
        wac.setContextPath("/");
        String webAppPath = "/src/main/webapp";
        final String scalatraWebAppPath = scalatraPath + webAppPath;
        String warPath = isExecutedFromMaven(canonicalPath) ? gotoProjectRoot().getCanonicalPath() + scalatraWebAppPath : canonicalPath + scalatraWebAppPath;
        wac.setWar(warPath);
        wac.setServer(server);

        security.setHandler(wac);
        server.setHandler(security);
        server.setConnectors(new Connector[]{http, https});
        dontSendDateHeader(server);
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

    private static File gotoProjectRoot() {
        return new File("../../.");
    }

    private static boolean isExecutedFromMaven(String canonicalPath) {
        return canonicalPath.contains(itestPath);
    }

    @AfterClass
    public static void stopJetty() throws Exception {
        server.stop();
        server.join();
    }

    @BeforeTest
    public void setUpBeforeTest() {
        if (jettyOption == JettyOption.RESET_REST_ASSURED_BEFORE_TEST) {
            RestAssured.reset();
        }
    }

    public enum JettyOption {
        RESET_REST_ASSURED_BEFORE_TEST,
        DONT_RESET_REST_ASSURED_BEFORE_TEST
    }
}
