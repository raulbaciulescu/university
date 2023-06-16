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
import org.jboss.jandex.Main;
import services.LoginException;
import services.Service;
import utils.Resources;

public class LoginController {
    @FXML public TextField txtFieldUsername;
    @FXML public Button btnLogin;
    @FXML public Text txtErrors;

    private Service server;
    private StartGameController startGameController;
    private Parent startRoot;


    public void setServer(Service service) {
        server = service;
    }

    public void setParent(Parent p){
        startRoot = p;
    }

    public void setMainController(StartGameController startGameController) {
        this.startGameController = startGameController;
    }

    public void onBtnLoginClicked(MouseEvent mouseEvent) {
        String alias = txtFieldUsername.getText();
        txtErrors.setVisible(false);

        try {
            Player player = new Player(alias);
            server.login(player, startGameController);

            System.out.println("Login success!");

            Resources.getInstance().setLoggedPlayer(player);
            changeScene(mouseEvent);
        } catch (LoginException e) {
            txtErrors.setVisible(true);
        }
    }

    private void changeScene(MouseEvent mouseEvent) {
        Stage stage = new Stage();
        stage.setTitle("Main window");
        stage.setScene(new Scene(startRoot));
        stage.show();
        startGameController.initialize();
        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
    }

}
