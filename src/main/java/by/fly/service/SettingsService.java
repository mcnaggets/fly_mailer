package by.fly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import static by.fly.config.PropertyConfig.PROPERTIES;
import static by.fly.config.PropertyConfig.PROPERTIES_FILE_PATH;

@Service
public class SettingsService {

    public static final String MAIL_PROPERTY_CLIENT_NAME = "mail.clientName";
    public static final String MAIL_PROPERTY_CLIENT_PHONE = "mail.clientPhone";
    public static final String MAIL_PROPERTY_MAIL_TO = "mail.toEmail";

    @Resource(name = PROPERTIES)
    private Properties properties;

    @Autowired
    private Environment environment;

    public void saveSettings(Properties properties) throws IOException {
        this.properties.putAll(properties);
        this.properties.store(new FileWriter(PROPERTIES_FILE_PATH), null);
    }

    public String getProperty(String key) {
        return environment.getProperty(key);
    }

    public String getMailTo() {
        return getProperty(MAIL_PROPERTY_MAIL_TO);
    }
}
