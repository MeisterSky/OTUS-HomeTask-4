package config;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    private static final String PROPERTIES = "/app.properties";

    public static String getProperty(String name) {
        Properties properties = new Properties();

        try {
            properties.load(PropertyLoader.class.getResourceAsStream(PROPERTIES));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String value = "";

        if (name != null) {
            value = properties.getProperty(name);
        }
        return value;
    }
}