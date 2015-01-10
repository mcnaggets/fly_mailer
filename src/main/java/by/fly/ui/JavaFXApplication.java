package by.fly.ui;

import by.fly.ui.controller.Controller;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaFXApplication extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaFXApplication.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        Thread.setDefaultUncaughtExceptionHandler((t, x) -> LOGGER.error(x.getMessage(), x));
        loadSettingsController();
        loadApplicationController(primaryStage);
    }

    private void loadSettingsController() {
        SpringFXMLLoader.load("/fxml/settings.fxml");
    }

    private void loadApplicationController(Stage primaryStage) {
        Controller controller = SpringFXMLLoader.load("/fxml/application.fxml");
        Scene scene = new Scene((Parent) controller.getView(), 800, 600);
        primaryStage.setTitle(SpringFXMLLoader.APPLICATION_CONTEXT.getEnvironment().getProperty("application.name"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        try {
            launch(args);
        } catch (Exception x) {
            LOGGER.error(x.getMessage(), x);
            throw x;
        }
    }

}
