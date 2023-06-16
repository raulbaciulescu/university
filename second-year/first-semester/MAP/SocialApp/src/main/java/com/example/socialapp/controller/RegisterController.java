package com.example.socialapp.controller;

import com.example.socialapp.domain.User;
import com.example.socialapp.domain.UserLogin;
import com.example.socialapp.service.UserService;
import com.example.socialapp.utils.Constants;
import com.example.socialapp.utils.Resources;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class RegisterController {
    @FXML TextField txtFieldFirstName;
    @FXML TextField txtFieldLastName;
    @FXML TextField txtFieldUsername;
    @FXML TextField txtFieldPassword;
    @FXML TextField txtFieldConfirmPassword;
    @FXML Button btnRegister;
    @FXML Text txtError;
    UserService userService;

    public RegisterController() {
        userService = Resources.userService;
    }


    public void onBtnRegister(ActionEvent event) throws SQLException, IOException, NoSuchAlgorithmException {
        String username = txtFieldUsername.getText();
        String lastName = txtFieldLastName.getText();
        String firstName = txtFieldFirstName.getText();
        String password = txtFieldPassword.getText();
        String confirmPassword = txtFieldPassword.getText();

        if (Objects.equals(password, confirmPassword)) {
            Optional<UserLogin> userLoginOptional = userService.findUserLogin(username, password);
            if (userLoginOptional.isEmpty()) {
                userService.addUserLogin(username, password, firstName, lastName);
                NavController.navigate(Constants.Scene.MAIN_FEED, event);
            }
            else {
                txtError.setVisible(true);
            }
        }
        else {
            txtError.setVisible(true);
        }
    }

    public void onLoginClicked(MouseEvent mouseEvent) throws IOException {
        NavController.navigate(Constants.Scene.LOG_IN, mouseEvent);
    }
}
