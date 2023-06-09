package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class CounterFXML extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/counter.fxml")));
        Scene scene = new Scene(load, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
