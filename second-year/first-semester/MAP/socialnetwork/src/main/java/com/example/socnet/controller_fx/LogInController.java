package com.example.socnet.controller_fx;

import com.example.socnet.domain.exceptions.InvalidException;
import com.example.socnet.domain.model.User;
import com.example.socnet.domain.model.UserLogin;
import com.example.socnet.domain.util.Constants;
import com.example.socnet.domain.validation.PasswordValidator;
import com.example.socnet.domain.validation.UsernameValidator;
import com.example.socnet.resources.Resources;
import com.example.socnet.service.CurrentUser;
import com.example.socnet.service.UserService;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Optional;

public class LogInController {

    @FXML public TextField txtFieldUsername;
    @FXML public PasswordField txtFieldPassword;

    @FXML public Button btnLogin;

    @FXML public Text txtSignUpLink;
    @FXML public Text txtCredentialsError;

    final UserService userService;
    final PasswordValidator passwordValidator;
    final UsernameValidator usernameValidator;

    public LogInController()
            throws SQLException {
        this.userService = Resources.getInstance().getUserService();
        this.passwordValidator = new PasswordValidator();
        this.usernameValidator = new UsernameValidator();
    }

    @FXML
    public void onLogInContinueClick(Event event) {
        final String username = this.txtFieldUsername.getText();
        final String password = this.txtFieldPassword.getText();

        this.hideError();

        try {
            this.usernameValidator.validate(username);
            this.passwordValidator.validate(password);

            final Optional<UserLogin> result = this.userService.findLogin(username, password);
            if (result.isPresent()) {
                final Optional<User> userOptional = this.userService.findByID(result.get().getUserID());
                if (userOptional.isPresent()) {
                    CurrentUser.initialize(userOptional.get());
                    NavController.navigate(Constants.UI.Scene.MAIN_FEED, event);
                } else {
                    showError();
                }
            } else {
                this.showError();
            }
        } catch (InvalidException e) {
            this.showError();
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private void hideError() {
        this.txtCredentialsError.setVisible(false);
    }

    private void showError() {
        this.txtCredentialsError.setVisible(true);
    }

    @FXML
    public void onSignUpClick(@NotNull final MouseEvent event) {
        NavController.navigate(Constants.UI.Scene.SIGN_UP, event);
    }
}
