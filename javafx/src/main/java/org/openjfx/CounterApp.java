package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterApp extends Application {
    AtomicInteger i = new AtomicInteger();
    @Override
    public void start(Stage stage) throws Exception {
        Label label = new Label("0");
        Button button = new Button("Count");
        button.setOnAction(event -> {
            label.setText(String.valueOf(i.incrementAndGet()));
        });

        HBox hBox = new HBox();
        hBox.getChildren().add(label);
        hBox.getChildren().add(button);
        Scene scene = new Scene(hBox, 640, 480);
        stage.setScene(scene);
        stage.show();
   }

    public static void main(String[] args) {
        launch(args);
    }
}
