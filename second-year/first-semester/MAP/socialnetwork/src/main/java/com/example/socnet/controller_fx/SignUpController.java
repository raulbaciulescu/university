package com.example.socnet.controller_fx;

import com.example.socnet.domain.exceptions.InvalidException;
import com.example.socnet.domain.model.User;
import com.example.socnet.domain.util.Constants;
import com.example.socnet.domain.validation.PasswordValidator;
import com.example.socnet.domain.validation.UsernameValidator;
import com.example.socnet.resources.Resources;
import com.example.socnet.service.CurrentUser;
import com.example.socnet.service.UserService;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.Optional;

public class SignUpController {

    @FXML public TextField txtFieldUsername;
    @FXML public TextField txtFieldPassword;
    @FXML public TextField txtFieldPasswordConfirmation;

    @FXML public Button btnContinueSignUp;

    @FXML public Text txtUsernameError;
    @FXML public Text txtFirstNameError;
    @FXML public Text txtLastNameError;
    @FXML public Text txtPasswordError;
    @FXML public TextField txtFieldFirstName;
    @FXML public TextField txtFieldLastName;

    final UserService userService;
    final PasswordValidator passwordValidator;
    final UsernameValidator usernameValidator;

    public SignUpController()
            throws SQLException {
        this.userService = Resources.getInstance().getUserService();
        this.passwordValidator = new PasswordValidator();
        this.usernameValidator = new UsernameValidator();
    }

    @FXML
    public void onSignInClick(MouseEvent event) {
        NavController.navigate(Constants.UI.Scene.LOG_IN, event);
    }

    @FXML
    public void onContinueSignUp(ActionEvent event) {
        final String firstName = this.txtFieldFirstName.getText();
        final String lastName = this.txtFieldLastName.getText();

        this.hideErrors();

        try {
            final Optional<User> result = this.userService.add(firstName, lastName);
            if (result.isPresent()) {
                CurrentUser.initialize(result.get());
                this.login(result.get().getId(), event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvalidException e) {
            if (e.getMessage().contains("name")) {
                this.showNameErrors();
            } else if (e.getMessage().contains("password")) {
                this.showPasswordError();
            }
        }
    }

    private void hideErrors() {
        this.txtFirstNameError.setVisible(false);
        this.txtLastNameError.setVisible(false);
        this.txtPasswordError.setVisible(false);
    }

    private void showNameErrors() {
        this.txtFirstNameError.setVisible(true);
        this.txtLastNameError.setVisible(true);
    }

    private void showPasswordError() {
        this.txtPasswordError.setVisible(true);
    }

    private void login(final long userID,
                       @NotNull final Event event) {
        final String username = this.txtFieldUsername.getText();
        final String password = this.txtFieldPassword.getText();
        final String passwordConfirmation = this.txtFieldPasswordConfirmation.getText();

        try {
            this.usernameValidator.validate(username);
            if (password.equals(passwordConfirmation)) {
                this.passwordValidator.validate(password);
                if (this.userService.addLogin(userID, username, password).isPresent()) {
                    NavController.navigate(Constants.UI.Scene.MAIN_FEED, event);
                } else {
                    this.userService.delete(userID);
                }
            } else {
                this.userService.delete(userID);
                showPasswordError();
            }
        } catch (InvalidException e) {
            this.showPasswordError();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                this.userService.delete(userID);
            } catch (SQLException ignored) {}
        }
    }
}
