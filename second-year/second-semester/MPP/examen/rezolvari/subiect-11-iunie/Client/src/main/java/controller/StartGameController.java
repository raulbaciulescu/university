package controller;

import domain.Game;
import domain.ProposedWord;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.Observer;
import services.Service;
import utils.Resources;

import java.util.List;

public class StartGameController implements Observer {
    @FXML public Button btnStartGame;
    @FXML public TextField txtFieldWord;

    private Service server;
    private MainController mainController;
    private Parent mainParent;

    private MouseEvent event;

    public void setServer(Service service) {
        server = service;
    }

    public void setParent(Parent p){
        mainParent = p;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    public void onBtnStartGameClick(MouseEvent mouseEvent) {
        String word = txtFieldWord.getText();
        if (word.length() >= 0) {
            server.startGame(new ProposedWord(word, Resources.getInstance().getLoggedPlayer().getId(),
                makePositions(word)), mainController);
            btnStartGame.setDisable(true);
            event = mouseEvent;
        }
    }

    public String makePositions(String word) {
        return "_".repeat(word.length());
    }

    @Override
    public void startGameUpdate(List<ProposedWord> proposedWords, Game game) {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            stage.setTitle("Game window for player " + Resources.getInstance().getLoggedPlayer().getId().toString());
            mainController.initialize(proposedWords);
            stage.setScene(new Scene(mainParent));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });

    }

}
