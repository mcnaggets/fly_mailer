package by.fly.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import static by.fly.config.PropertyConfig.PROPERTIES;
import static by.fly.config.PropertyConfig.PROPERTIES_FILE_PATH;

@Service
public class SettingsService {

    public static final String MAIL_PROPERTY_PREFIX = "mail.";

    @Resource(name = PROPERTIES)
    private Properties properties;

    public void saveSettings(Properties properties) throws IOException {
        this.properties.putAll(properties);
        this.properties.store(new FileWriter(PROPERTIES_FILE_PATH), null);
    }

    public Properties getProperties() {
        return properties;
    }
}
