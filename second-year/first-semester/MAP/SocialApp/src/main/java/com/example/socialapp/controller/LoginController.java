package com.example.socialapp.controller;

import com.example.socialapp.domain.User;
import com.example.socialapp.domain.UserLogin;
import com.example.socialapp.service.UserService;
import com.example.socialapp.utils.Constants;
import com.example.socialapp.utils.Resources;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Optional;

public class LoginController {
    @FXML public TextField txtFieldUsername;
    @FXML public TextField txtFieldPassword;
    @FXML public Button btnLogin;
    @FXML public Text registerText;
    @FXML public Text errors;

    public UserService userService;

    public LoginController() {
        this.userService = Resources.userService;
    }


    public void onBtnRegisterClicked(MouseEvent mouseEvent) throws IOException {
        NavController.navigate(Constants.Scene.REGISTER, mouseEvent);
    }

    public void onBtnLoginClicked(MouseEvent mouseEvent) {
        String username = txtFieldUsername.getText();
        String password = txtFieldPassword.getText();
        errors.setVisible(false);
        try {
            Optional<UserLogin> userLoginOptional = userService.findUserLogin(username, password);
            if (userLoginOptional.isPresent()) {
                Optional<User> user = userService.findByID(userLoginOptional.get().getUserId());
                user.ifPresent(value -> Resources.currentUser.setUser(value));
                NavController.navigate(Constants.Scene.MAIN_FEED, mouseEvent);
            }
            else {
                errors.setVisible(true);
            }
        } catch (SQLException | IOException | NoSuchAlgorithmException e) {
            errors.setVisible(true);
        }
    }
}
