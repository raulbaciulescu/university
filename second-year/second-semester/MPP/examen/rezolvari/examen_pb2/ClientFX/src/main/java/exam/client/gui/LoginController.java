package exam.client.gui;

import exam.client.RestClient;
import exam.model.Player;
import exam.model.dto.GameDTO;
import exam.services.ExamException;
import exam.services.IExamServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


import java.time.LocalDateTime;

public class LoginController {
    private RestClient server;
    private MainController mainController;
    private Player loggedPlayer;

    Parent mainParent;


    public void setServer(RestClient server) {
        this.server = server;
    }

    public void setParent(Parent p) {
        mainParent = p;
    }

    public void setOfficeWorker(Player player) {
        this.loggedPlayer = player;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    @FXML
    public TextField aliasTextField;

    public void closeLoginWindow() {
        Stage stage = (Stage) aliasTextField.getScene().getWindow();
        stage.close();
    }

    public void login(ActionEvent actionEvent) {
        String alias = aliasTextField.getText();

        loggedPlayer = server.loginPlayer(alias);
        if(loggedPlayer==null){
            AlertMessage.showErrorMessage("Player not found!");
            return;
        }
        Stage stage = new Stage();
        stage.setTitle("Window for " + alias);
        stage.setScene(new Scene(mainParent));

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                mainController.logout();
                System.exit(0);
            }
        });
        stage.show();
        GameDTO game=server.startGame(alias, LocalDateTime.now());
        mainController.setGame(game);
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();


    }
}
