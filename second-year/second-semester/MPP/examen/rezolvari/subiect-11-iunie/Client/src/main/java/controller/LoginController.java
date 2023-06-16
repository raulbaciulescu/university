package controller;

import domain.Player;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.LoginException;
import services.Observer;
import services.Service;
import utils.Resources;

import java.sql.SQLException;

public class LoginController {
    @FXML public TextField txtFieldUsername;
    @FXML public TextField txtFieldPassword;
    @FXML public Button btnLogin;
    @FXML public Text errors;

    private Service server;
    private StartGameController startGameController;
    private Parent startGameParent;


    public void setServer(Service service) {
        server = service;
    }

    public void setParent(Parent p){
        startGameParent = p;
    }

    public void setStartGameController(StartGameController startGameController) {
        this.startGameController = startGameController;
    }

    public void onBtnLoginClicked(MouseEvent mouseEvent) {
        String username = txtFieldUsername.getText();
        String password = txtFieldPassword.getText();

        try {
            Player player = new Player(username, password);
            Player loggedPlayer = server.login(player, startGameController);

            System.out.println("Login success!");

            Resources.getInstance().setLoggedPlayer(loggedPlayer);
            changeScene(mouseEvent);
        } catch (LoginException e) {
            System.out.println("Login exception");
        }
    }

    private void changeScene(MouseEvent mouseEvent) {
        Stage stage = new Stage();
        stage.setTitle("Start game window");
        stage.setScene(new Scene(startGameParent));
        stage.show();
        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
    }

}
