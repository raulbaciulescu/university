package controller;

import domain.Game;
import domain.ProposedWord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import services.Service;
import utils.Resources;

public class ResultsController {

    public ListView<String> listView;
    public Button btnLogout;

    private final ObservableList<String> observableList = FXCollections.observableArrayList();

    private Service server;

    public void setServer(Service server) {
        this.server = server;
    }

    public void initialize(Game game) {
        observableList.add("player 1: " + game.getScore1().toString());
        observableList.add("player 2: " + game.getScore2().toString());
        observableList.add("player 3: " + game.getScore3().toString());
        listView.setItems(observableList);
    }

    public void onLogoutBtnClick(MouseEvent mouseEvent) {
        try {
            server.logout(Resources.getInstance().getLoggedPlayer(), null);
            Resources.getInstance().setLoggedPlayer(null);
            ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            //errors.setVisible(true);
        }
    }


}
