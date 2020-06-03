package com.epam.http;

import com.epam.http.logger.ILogger;

import java.util.HashMap;
import java.util.Map;

import static com.epam.http.logger.HTTPLogger.instance;
import static com.epam.http.logger.LogLevels.parseLogLevel;
import static com.epam.jdi.tools.PropertyReader.fillAction;
import static com.epam.jdi.tools.PropertyReader.getProperties;

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
        String[] params = domain.trim().split(",");
        if (domain.contains("=")) {
            for (String p : params) {
                String[] pairs = p.split("=");
                DOMAIN.put(pairs[0].trim(), pairs[1]);
            }
        } else {
            DOMAIN.put("domain", domain);
        }
    }

    /**
     * Initialize settings from property file
     */
    public static synchronized void init() {
        getProperties(TEST_PROPERTIES_PATH);
        fillAction(JdiHttpSettings::setDomain, "domain");
        fillAction(p -> logger.setLogLevel(parseLogLevel(p)), "log.level");
    }
}
