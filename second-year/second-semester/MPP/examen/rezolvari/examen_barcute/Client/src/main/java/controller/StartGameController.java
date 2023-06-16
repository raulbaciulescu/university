package controller;

import domain.Game;
import domain.GameDTO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import services.Observer;
import services.Service;
import utils.Resources;

import java.util.List;

public class StartGameController implements Observer {
    @FXML public Button btnStartGame;
    @FXML public TextField txtFieldWord;
    public TextField txtFieldX;
    public TextField txtFieldY;
    public GridPane gridPane;

    private Service server;
    private MainController mainController;
    private Parent mainParent;
    private Integer x1;
    private Integer y1;
    private Integer x2;
    private Integer y2;
    private Game game;
    private Integer clicks = 0;
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

    public void initialize() {
        gridPane.setGridLinesVisible(true);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Label label = new Label("x");
                label.setMinWidth(50);
                gridPane.add(label, i, j);
            }
        }

        var children= gridPane.getChildren();

        for (var child :children){
            Integer row = GridPane.getRowIndex(child);
            Integer column = GridPane.getColumnIndex(child);
            child.setOnMouseClicked(event-> handleClick(row,column));
        }
    }

    private void handleClick(Integer row, Integer column) {
        clicks++;
        if (clicks == 1) {
            x1 = row;
            y1 = column;
        }
        if (clicks == 2) {
            x2 = row;
            y2 = column;
        }
        System.out.println(row + " " + column);
    }

    public void onBtnStartGameClick(MouseEvent mouseEvent) {
        game = new Game(Resources.getInstance().getLoggedPlayer(),
                x1, y1, x2, y2);
        server.startGame(game, mainController);
        btnStartGame.setDisable(true);
        event = mouseEvent;
    }

    public String makePositions(String word) {
        return "_".repeat(word.length());
    }

    @Override
    public void startGameUpdate(GameDTO gameDTO) {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            stage.setTitle("Game window for player " + Resources.getInstance().getLoggedPlayer().getId().toString());
            mainController.initialize(gameDTO);
            stage.setScene(new Scene(mainParent));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });

    }

}
