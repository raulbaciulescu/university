package config;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class Config {

    public static final String CONFIG_LOCATION;

    static {
        @Nullable final URL url = Config.class.getClassLoader()
                .getResource("config.properties");

        CONFIG_LOCATION = url != null ? url.getFile() : "";
    }

    @NotNull
    public static Properties getProperties() {
        try {
            final Properties properties = new Properties();
            final FileReader fileReader = new FileReader(CONFIG_LOCATION);
            properties.load(fileReader);
            return properties;
        } catch (@Nullable final IOException exception) {
            throw new RuntimeException("Cannot load configurations properties");
        }
    }
}
