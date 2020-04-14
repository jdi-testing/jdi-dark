package com.epam.http;

import com.epam.http.logger.ILogger;
import com.epam.jdi.tools.PropertyReader;

import java.io.*;
import java.util.*;

import static com.epam.http.logger.HTTPLogger.instance;
import static com.epam.http.logger.LogLevels.parseLogLevel;
import static com.epam.jdi.tools.PathUtils.mergePath;
import static com.epam.jdi.tools.PropertyReader.fillAction;
import static java.util.Arrays.asList;

public class JdiHttpSettigns {
    public static String TEST_PROPERTIES_PATH = "test.properties";
    public static boolean verifyOkStatus = false;
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


    public static synchronized void init() {
        Properties properties = getProperties("pom.properties");
        getProperties((properties.size() > 0) ? ((!properties.getProperty("profile").contains("$")) ? properties.getProperty("profile").concat(".properties") : TEST_PROPERTIES_PATH) : TEST_PROPERTIES_PATH);
        getProperties((properties.size() > 0)
                ? ((!properties.getProperty("profile").contains("$")) ? properties.getProperty("profile").concat(".properties") : TEST_PROPERTIES_PATH)
                : TEST_PROPERTIES_PATH);
        fillAction(p -> setDomain(p), "domain");
        fillAction(p -> logger.setLogLevel(parseLogLevel(p)), "log.level");
    }

    private static Properties getProperties(String path) {
        logger.info("Properties file is: " + path);
        File propertyFile = new File(JdiHttpSettigns.class.getClassLoader().getResource(path).getFile());
        Properties properties;
        if (propertyFile.exists()) {
            properties = getCiProperties(path, propertyFile);
        } else {
            Properties pTest = PropertyReader.getProperties(mergePath(path));
            Properties pTarget = PropertyReader.getProperties(mergePath("/../../target/classes/" + path));
            if (pTarget.size() > 0)
                return pTarget;
            String propertiesPath = pTest.size() > 0
                    ? path
                    : mergePath("/../../target/classes/" + path);
            properties = PropertyReader.getProperties(propertiesPath);
        }
        return properties;
    }

    private static Properties getCiProperties(String path, File propertyFile) {
        Properties properties = new Properties();
        try {
            logger.debug("Property file found: " + propertyFile.getAbsolutePath());
            properties.load(new FileInputStream(propertyFile));
        } catch (IOException ex) {
            throw new RuntimeException("Couldn't load properties for CI Server" + path);
        }
        return properties;
    }
}
