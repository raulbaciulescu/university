package controller;


import domain.Game;
import domain.GameDTO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import services.Observer;
import services.Service;
import utils.Resources;

import java.util.List;
import java.util.Objects;

public class MainController implements Observer {

    private final ObservableList<String> observableList = FXCollections.observableArrayList();
    public Text txtTitle;
    public GridPane gridPane;
    public GameDTO gameDTO;
    public Integer round = 1;
    public ListView<String> listView;
    public Button btnLogout;
    public Text txtErrors;
    private boolean isFinished = false;
    private Service server;

    public void setServer(Service server) {
        this.server = server;
    }

    public void initialize(GameDTO gameDTO) {
        txtTitle.setText("Round " + round);

        this.gameDTO = gameDTO;

        gridPane.setGridLinesVisible(true);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Label label = new Label("");
                label.setMinWidth(100);
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
        System.out.println(row.toString() + " " + column.toString());

        //Configuration configuration = gameDTO.getConfiguration();
        // jocul e terminat

//        if (Objects.equals(configuration.getX(), row) && Objects.equals(configuration.getY(), column) && round < 4) {
//            showLabel(row,column,configuration.getValue());
//
//            Game game = gameDTO.getGame();
//            game.setFinish(true);
//            game.setValue(gameDTO.getConfiguration().getValue());
//
//            UpdateObject updateObject = new UpdateObject(game, game.getValue(), game.getFinish());
//            server.updateGame(updateObject);
//            isFinished = true;
//        }
//        else if (round < 4){
//            // calculez distanta euclidiana
//            double distance = Math.pow(row + 1 - configuration.getX(), 2) + Math.pow(column + 1 - configuration.getY(), 2);
//            distance = Math.sqrt(distance);
//
//            showLabel(row, column, distance);
//            Game game = gameDTO.getGame();
//            game.setScore((int) (game.getScore() + distance));
//            if (round == 3) {
//                game.setFinish(true);
//                game.setValue(-1);
//                isFinished = true;
//            }
//            UpdateObject updateObject = new UpdateObject(game, game.getValue(), game.getFinish());
//            server.updateGame(updateObject);
//        }
//
//        round++;
//        txtTitle.setText("Round " + round);
//        if (isFinished) {
//            updateListView();
//        }
    }

    private void updateListView() {
        if (isFinished) {
            //List<Game> games = server.getFinishedGames();
//            for (Game game : games) {
//                observableList.add("Alias: " + game.getAlias() + ", data incepere: " + game.getStartDate() +
//                        " score: " + game.getScore() + " value: " + game.getValue());
//            }
            listView.setItems(observableList);
            txtTitle.setText("Jocul s-a terminat!");
        }
    }

    private void showLabel(int row, int column, double value){
        var children= gridPane.getChildren();
        for(Node child : children){
            Integer rowG = GridPane.getRowIndex(child);
            Integer columnG = GridPane.getColumnIndex(child);

            if (rowG != null && columnG != null && rowG == row && columnG == column) {
                Label label = (Label) child;
                label.setText(String.format("%.3f", value));
            }
        }
    }

    //@Override
    public void updateResults(List<Game> games) {
        Platform.runLater(() -> {
//            if (gameDTO.getGame().getFinish()) {
//                observableList.removeAll();
//                listView.getItems().clear();
//
//                for (Game game : games) {
//                    observableList.add("Alias: " + game.getAlias() + ", data incepere: " + game.getStartDate() +
//                            " score: " + game.getScore() + " value: " + game.getValue());
//                }
//                listView.setItems(observableList);
//            }
        });
    }

    public void onLogoutBtnClick(MouseEvent mouseEvent) {
        try {
            server.logout(Resources.getInstance().getLoggedPlayer(), null);
            Resources.getInstance().setLoggedPlayer(null);
            ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            txtErrors.setVisible(true);
        }
    }

    @Override
    public void startGameUpdate(GameDTO gameDTO) {

    }
}
