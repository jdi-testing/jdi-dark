package com.epam.jdi.httptests.support;

import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.security.*;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.util.security.Constraint;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.Collections;

public abstract class WithJetty {
    private static Server server;

    @BeforeSuite
    public static void startJettyHttps() throws Exception {
        server = new Server();

        HttpConfiguration httpConfig = new HttpConfiguration();
        httpConfig.setSecureScheme("https");
        httpConfig.setSecurePort(8443);
        httpConfig.setOutputBufferSize(32768);

        ServerConnector http = new ServerConnector(server, new HttpConnectionFactory(httpConfig));
        http.setPort(8080);
        http.setIdleTimeout(300000);

        HttpConfiguration httpsConfig = new HttpConfiguration(httpConfig);
        httpsConfig.addCustomizer(new SecureRequestCustomizer());

        String file = "src/test/resources/jetty_localhost_server.jks";
        SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
        sslContextFactory.setKeyStorePath(file);
        sslContextFactory.setKeyStorePassword("test1234");

        ServerConnector https = new ServerConnector(server,
                new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString()),
                new HttpConnectionFactory(httpsConfig));
        https.setPort(8443);
        https.setIdleTimeout(500000);

        // Security config
        Constraint constraint = new Constraint();
        constraint.setName(Constraint.__BASIC_AUTH);
        constraint.setRoles(new String[]{"user", "admin", "moderator"});
        constraint.setAuthenticate(true);

        ConstraintMapping mapping = new ConstraintMapping();
        mapping.setConstraint(constraint);
        mapping.setPathSpec("/secured/*");

        LoginService loginService = new HashLoginService("MyRealm", "src/test/resources/realm.properties");
        server.addBean(loginService);

        ConstraintSecurityHandler security = new ConstraintSecurityHandler();
        server.setHandler(security);
        security.setConstraintMappings(Collections.singletonList(mapping));
        security.setAuthenticator(new BasicAuthenticator());
        security.setLoginService(loginService);

        // End security config

        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        String warPath = "target/webapps/scalatra-webapp.war";
        context.setWar(warPath);
        context.setServer(server);

        security.setHandler(context);
        server.setHandler(security);
        server.setConnectors(new Connector[]{http, https});
        server.start();
    }

    @AfterSuite
    public static void stopJetty() throws Exception {
        server.stop();
        server.join();
    }
}