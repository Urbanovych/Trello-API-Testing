package trello.api.urbanovych.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {

    static FileInputStream fileInputStream;
    static Properties properties = new Properties();

    public static Properties getPropertiesFromPath(String path) {
        try {
            fileInputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    public static String getBaseUrlProperties() {
        Properties urlProperties = getPropertiesFromPath("src/main/resources/url.properties");
        return urlProperties.getProperty("baseUrl");
    }

    public static String getEndpointProperties (String endpoint) {
        Properties urlProperties = getPropertiesFromPath("src/main/resources/url.properties");
        return urlProperties.getProperty(endpoint);
    }
}
