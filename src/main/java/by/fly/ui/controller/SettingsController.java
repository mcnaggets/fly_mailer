package by.fly.ui.controller;

import by.fly.service.SettingsService;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import static by.fly.service.SettingsService.*;

@Component
public class SettingsController extends AbstractController {

    public TextField clientName;
    public TextField clientPhone;

    @Autowired
    private SettingsService settingsService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        clientName.setText(settingsService.getProperties().getProperty(MAIL_PROPERTY_PREFIX + clientName.getId()));
        clientPhone.setText(settingsService.getProperties().getProperty(MAIL_PROPERTY_PREFIX + clientPhone.getId()));
    }

    public void saveSettings() {
        try {
            Properties properties = new Properties();
            properties.put(MAIL_PROPERTY_PREFIX + clientName.getId(), clientName.getText());
            properties.put(MAIL_PROPERTY_PREFIX + clientPhone.getId(), clientPhone.getText());
            settingsService.saveSettings(properties);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
