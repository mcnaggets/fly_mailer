package by.fly.ui.controller;

import by.fly.service.MailService;
import by.fly.service.SettingsService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApplicationController extends AbstractController {

    @Autowired
    private SettingsController settingsController;

    @Autowired
    private MailService mailService;

    @Autowired
    private SettingsService settingsService;

    public void changeSettings(ActionEvent actionEvent) throws IOException {
        final Alert alert = new Alert(Alert.AlertType.NONE, "Настройки", ButtonType.APPLY, ButtonType.CANCEL);
        alert.getDialogPane().setContent(settingsController.getView());
        alert.showAndWait()
                .filter(response -> response == ButtonType.APPLY)
                .ifPresent(response -> settingsController.saveSettings());
    }

    public void sendMail(ActionEvent actionEvent) {
        mailService.sendMail(settingsService.getMailTo(), "Test", "Test",
                x -> Platform.runLater(() -> new Alert(Alert.AlertType.ERROR, x.getMessage()).showAndWait()));
    }

}
