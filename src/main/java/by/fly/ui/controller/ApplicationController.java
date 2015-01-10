package by.fly.ui.controller;

import by.fly.service.MailService;
import by.fly.service.SettingsService;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ApplicationController extends AbstractController {

    public ProgressIndicator progressIndicator;

    private final SendMailService service = new SendMailService();

    @Autowired
    private SettingsController settingsController;

    @Autowired
    private MailService mailService;

    @Autowired
    private SettingsService settingsService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        initializeProgress();
    }

    private void initializeProgress() {
        progressIndicator.setMaxSize(150, 150);
        progressIndicator.progressProperty().bind(service.progressProperty());
        progressIndicator.visibleProperty().bind(service.runningProperty());
    }

    public void changeSettings(ActionEvent actionEvent) throws IOException {
        final Alert alert = new Alert(Alert.AlertType.NONE, "Настройки", ButtonType.APPLY, ButtonType.CANCEL);
        alert.getDialogPane().setContent(settingsController.getView());
        alert.showAndWait()
                .filter(response -> response == ButtonType.APPLY)
                .ifPresent(response -> settingsController.saveSettings());
    }

    public void sendMail(ActionEvent actionEvent) {
        service.restart();
    }

    class SendMailService extends Service<Void> {

        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    mailService.sendMail(settingsService.getMailTo(), "Test", "Test",
                            x -> Platform.runLater(() -> new Alert(Alert.AlertType.ERROR, x.getMessage()).showAndWait()));
                    return null;
                }
            };
        }

    }

}
