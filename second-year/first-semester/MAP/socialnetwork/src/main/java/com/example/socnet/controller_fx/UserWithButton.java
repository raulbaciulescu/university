package com.example.socnet.controller_fx;

import com.example.socnet.domain.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UserWithButton extends User {
    @FXML
    Button button;

    public UserWithButton(long id, String firstName, String lastName, Button button) {
        super(id, firstName, lastName);
        this.button = button;
    }

    public Button getButton() {
        return button;
    }
}
