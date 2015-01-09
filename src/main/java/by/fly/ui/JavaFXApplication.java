package by.fly.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaFXApplication extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaFXApplication.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        Thread.setDefaultUncaughtExceptionHandler((t, x) -> LOGGER.error(x.getMessage(), x));
        primaryStage.setTitle("Fly mailer");
        Pane myPane = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/application.fxml"));
        Scene myScene = new Scene(myPane, 800, 600);
        primaryStage.setScene(myScene);
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
