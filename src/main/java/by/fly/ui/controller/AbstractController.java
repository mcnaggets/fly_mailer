package by.fly.ui.controller;

import javafx.scene.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class AbstractController implements Controller {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);

    protected Node view;
    protected ResourceBundle resourceBundle;

    public Node getView() {
        return view;
    }

    public void setView(Node view) {
        this.view = view;
    }

    @Override
    public void refresh() {
        // do nothing by default
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

}