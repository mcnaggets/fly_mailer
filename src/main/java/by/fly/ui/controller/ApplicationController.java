package by.fly.ui.controller;

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

    public void changeSettings(ActionEvent actionEvent) throws IOException {
        final Alert alert = new Alert(Alert.AlertType.NONE, "Настройки", ButtonType.APPLY, ButtonType.CANCEL);
        alert.getDialogPane().setContent(settingsController.getView());
        alert.showAndWait()
                .map(response -> response == ButtonType.APPLY)
                .ifPresent(response -> settingsController.saveSettings());
    }

}
