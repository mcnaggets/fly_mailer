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
    public TextField toEmail;

    @Autowired
    private SettingsService settingsService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        clientName.setText(settingsService.getProperty(MAIL_PROPERTY_CLIENT_NAME));
        clientPhone.setText(settingsService.getProperty(MAIL_PROPERTY_CLIENT_PHONE));
        toEmail.setText(settingsService.getProperty(MAIL_PROPERTY_MAIL_TO));
    }

    public void saveSettings() {
        try {
            Properties properties = new Properties();
            properties.put(MAIL_PROPERTY_CLIENT_NAME, clientName.getText());
            properties.put(MAIL_PROPERTY_CLIENT_PHONE, clientPhone.getText());
            properties.put(MAIL_PROPERTY_MAIL_TO, toEmail.getText());
            settingsService.saveSettings(properties);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
