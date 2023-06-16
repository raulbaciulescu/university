package controller;
import domain.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import services.LoginException;
import services.Service;
import utils.Constants;
import utils.Navigation;
import utils.Resources;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class LoginController {
    @FXML public TextField txtFieldUsername;
    @FXML public TextField txtFieldPassword;
    @FXML public Button btnLogin;
    @FXML public Text errors;

    private Service server;

    private MainController mainController;
    Parent mainChatParent;
    public void setServer(Service service) {
        server = service;
    }
    public void setParent(Parent p){
        mainChatParent=p;
    }
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void onBtnLoginClicked(MouseEvent mouseEvent) {
        String username = txtFieldUsername.getText();
        String password = txtFieldPassword.getText();
        try {
            User user = new User(username, password);
            server.login(user, mainController);
            Resources.getInstance().loginCurrentUser(user);
            System.out.println("Login Succes!!");
            //Navigation.navigate(Constants.Scene.MAIN, mouseEvent);

            Stage stage=new Stage();
            stage.setTitle("Main Window for " + user.getUsername());
            stage.setScene(new Scene(mainChatParent));

            stage.show();
            mainController.initialize1();
            ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();

        } catch (LoginException e) {
            System.out.println("Login exception!!!!!!!!");
        } catch (SQLException throwables) {
            System.out.println("SqlException!!");
        }
    }

}
