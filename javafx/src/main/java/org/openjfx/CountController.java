package org.openjfx;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.util.concurrent.atomic.AtomicInteger;

public class CountController {
    AtomicInteger i = new AtomicInteger();

    @FXML
    Text currentCount;

    public void increment(){
        currentCount.setText(String.valueOf(i.incrementAndGet()));
    }
}
