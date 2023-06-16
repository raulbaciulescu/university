package controller;

import domain.Game;
import domain.Proposal;
import domain.ProposedWord;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.Observer;
import services.Service;
import utils.Resources;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MainController implements Observer {
    public TextField txtFieldLetter;
    public Button btnSelect;
    private final ObservableList<String> observableList = FXCollections.observableArrayList();
    public ListView<String> listView;
    public TextField txtFieldId;
    public Text txtTitle;
    public Button btnFinish;
    private Service server;

    private ResultsController resultsController;
    private Parent resultsParent;

    private MouseEvent event;

    private Integer round = 1;

    private Game game;

    public void setServer(Service server) {
        this.server = server;
    }

    public void setParent(Parent p){
        resultsParent = p;
    }

    public void setResultsController(ResultsController resultsController) {
        this.resultsController = resultsController;
    }

    public void initialize(List<ProposedWord> proposedWords) {
        txtTitle.setText(Resources.getInstance().getLoggedPlayer().toString() + " " + "Round " + round);
        for (ProposedWord pw : proposedWords) {
            observableList.add(pw.toString() + " score: 0");
        }
        listView.setItems(observableList);
    }

    @Override
    public void startGameUpdate(List<ProposedWord> proposedWords, Game game) {
        Platform.runLater(() -> {
            observableList.removeAll();
            listView.getItems().clear();

            proposedWords.sort(Comparator.comparing(ProposedWord::getPlayerId).reversed());


            System.out.println(proposedWords);
            observableList.add(proposedWords.get(0).toString() + " score: " + game.getScore1());
            observableList.add(proposedWords.get(1).toString() + " score: " + game.getScore2());
            observableList.add(proposedWords.get(2).toString() + " score: " + game.getScore3());
            listView.setItems(observableList);

            round++;
            txtTitle.setText(Resources.getInstance().getLoggedPlayer().toString() + " " + "Round " + round);

            btnSelect.setDisable(false);

            if (round == 4) {
                btnFinish.setDisable(false);
                btnSelect.setDisable(true);
                txtTitle.setText(Resources.getInstance().getLoggedPlayer().toString() + " Click finish button");
            }
            this.game = game;
        });

    }

    public void onSelectBtnClick(MouseEvent mouseEvent) {
        String letterString = txtFieldLetter.getText();
        String idString = txtFieldId.getText();

        char character;
        int id;

        character = letterString.charAt(0);
        id = Integer.parseInt(idString);

        Proposal proposal = new Proposal(id, character, Resources.getInstance().getLoggedPlayer().getId());
        btnSelect.setDisable(true);
        server.move(proposal);
    }

    public void onFinishBtnClick(MouseEvent mouseEvent) {
        Stage stage = new Stage();
        stage.setTitle("Results window");
        stage.setScene(new Scene(resultsParent));
        resultsController.initialize(game);
        stage.show();
        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
    }
}
