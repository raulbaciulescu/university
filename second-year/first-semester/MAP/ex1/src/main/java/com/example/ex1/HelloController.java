package com.example.ex1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class HelloController {
    private int count = 0;
    @FXML
    private Label welcomeText;
    public HelloController() {

    }
//    @FXML
//    private CheckBox checkBoxActive;

    @FXML
    protected void onHelloButtonClick() {
        count++;
        welcomeText.setText(Integer.toString(count));
    }

    public void onActionActive(ActionEvent actionEvent) {
//
//        if (checkBoxActive.isSelected())
//            welcomeText.setText("Popescu este activ!");
//        else
//            welcomeText.setText("Popescu nu este activ!");
    }
}