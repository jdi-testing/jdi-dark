package com.epam.http;

import com.epam.http.logger.ILogger;
import com.epam.jdi.tools.PropertyReader;

import java.util.*;

import static com.epam.http.logger.HTTPLogger.instance;
import static com.epam.http.logger.LogLevels.parseLogLevel;
import static com.epam.jdi.tools.PropertyReader.fillAction;
import static java.util.Arrays.asList;

public class JdiHttpSettings {
    public static String TEST_PROPERTIES_PATH = "test.properties";
    public static ILogger logger = instance("JDI");
    public static Map<String, String> DOMAIN = new HashMap<>();

    public static String getDomain() {
        return !DOMAIN.isEmpty()
            ? DOMAIN.get("domain")
            : "No Domain Found. Use test.properties or WebSettings.DOMAIN";
    }

    public static String getDomain(String domainId) {
        return !DOMAIN.isEmpty()
            ? DOMAIN.getOrDefault(domainId, "No Domain Found. Use test.properties.")
            : "No Domain Found. Use test.properties.";
    }

    public static void setDomain(String domain) {
        List<String> params = asList(domain.trim().split(","));
        if (domain.contains("=")) {
            for(String p : params) {
                String[] pairs = p.split("=");
                DOMAIN.put(pairs[0].trim(), pairs[1]);
            }
        }
        else {
            DOMAIN.put("domain", domain);
        }
    }

    /**
     * Initialize settings from property file
     */
    public static synchronized void init() {
        Properties properties = PropertyReader.getProperties("pom.properties");
        System.out.println("---"+properties.getProperty("profile"));
        PropertyReader.getProperties((properties.size() > 0)
                ? ( (!properties.getProperty("profile").contains("$")) ? properties.getProperty("profile").concat(".properties") : TEST_PROPERTIES_PATH)
                : TEST_PROPERTIES_PATH);
        System.out.println("!---"+properties.getProperty("profile").concat(".properties") );
        fillAction(p -> setDomain(p), "domain");
        fillAction(p -> logger.setLogLevel(parseLogLevel(p)), "log.level");
    }
}
