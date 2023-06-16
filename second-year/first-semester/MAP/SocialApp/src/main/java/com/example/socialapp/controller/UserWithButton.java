package com.example.socialapp.controller;

import com.example.socialapp.domain.User;
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
