package com.epam.http;

import com.epam.http.logger.ILogger;
import com.epam.jdi.tools.PropertyReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.epam.http.logger.HTTPLogger.instance;
import static com.epam.http.logger.LogLevels.parseLogLevel;
import static com.epam.jdi.tools.PathUtils.mergePath;
import static com.epam.jdi.tools.PropertyReader.fillAction;

/**
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
public class JdiHttpSettigns {
    public static String TEST_PROPERTIES_PATH = "test.properties";
    public static boolean verifyOkStatus = false;
    public static ILogger logger = instance("JDI");
    public static String DOMAIN;

    public static String getDomain() {
        if (DOMAIN != null)
            return DOMAIN;
        return "No Domain Found. Use test.properties or WebSettings.DOMAIN";
    }

    public static void setDomain(String domain) { DOMAIN = domain; }

    public static synchronized void init() {
        getProperties(TEST_PROPERTIES_PATH);
        fillAction(p -> setDomain(p), "domain");
        fillAction(p -> logger.setLogLevel(parseLogLevel(p)), "log.level");
    }

    private static Properties getProperties(String path) {
        File propertyFile = new File(path);
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
            System.out.println("Property file found: " + propertyFile.getAbsolutePath());
            properties.load(new FileInputStream(propertyFile));
        } catch (IOException ex) {
            throw new RuntimeException("Couldn't load properties for CI Server" + path);
        }
        return properties;
    }
}
