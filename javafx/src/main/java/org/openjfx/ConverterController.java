package org.openjfx;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class ConverterController {
    @FXML
    TextField celsiusTemp;

    @FXML
    TextField fahrenheitTemp;

    @FXML
    public void initialize(){
        fahrenheitTemp.textProperty().bindBidirectional(celsiusTemp.textProperty(), new StringConverter<String>() {
            @Override
            public String toString(String fahrenheit) {
                try {
                    double v = Double.parseDouble(fahrenheit);
                    return String.valueOf(v * 9.9 / 5.0 + 32.0);
                } catch (NumberFormatException nfe){
                    return "";
                }

            }

            @Override
            public String fromString(String celsius) {
                try {
                    double v = Double.parseDouble(celsius);
                    return String.valueOf(v - 32 * 5.0 / 9.0);
                } catch (NumberFormatException nfe){
                    return "";
                }
            }
        });
    }
}
