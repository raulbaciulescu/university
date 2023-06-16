package config;

import java.util.Properties;

public class Context {

    private static final Properties PROPERTIES = Config.getProperties();

    public static Properties getProperties() {
        return PROPERTIES;
    }
}
