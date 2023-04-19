package org.openjfx;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class FlightsController {
    @FXML
    TextField leaveDate;

    @FXML
    TextField returnDate;

    @FXML
    ComboBox<String> tripType;

    @FXML
    public void initialize(){

        returnDate.disableProperty().bind(
                tripType.valueProperty().map("one-way flight"::equals)
        );
    }
}
