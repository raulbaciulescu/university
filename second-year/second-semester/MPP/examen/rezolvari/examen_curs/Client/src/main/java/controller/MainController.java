package controller;


import domain.Configuration;
import domain.Game;
import domain.GameDTO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import services.Observer;
import services.Service;
import utils.Resources;

import java.util.List;
import java.util.Random;

public class MainController implements Observer {

    private final ObservableList<String> observableList = FXCollections.observableArrayList();
    public Text txtTitle;
    public GameDTO gameDTO;
    public Integer round = 1;
    public ListView<String> listView;
    public Button btnLogout;
    public Text txtErrors;
    public Button btnGenerate;
    public Text txtGeneratedButton;
    public Text txtConfiguration;
    private boolean isFinished = false;
    private Service server;

    public void setServer(Service server) {
        this.server = server;
    }

    public void initialize(GameDTO gameDTO) {
        txtTitle.setText("Round " + round);
        txtConfiguration.setText(gameDTO.getConfiguration().getConf1() + " " +
                gameDTO.getConfiguration().getConf2() + " " +
                gameDTO.getConfiguration().getConf3() + " " +
                gameDTO.getConfiguration().getConf4() + " " +
                gameDTO.getConfiguration().getConf5());
        this.gameDTO = gameDTO;
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

    public void onGenerateBtnClick(MouseEvent mouseEvent) {
        Random random = new Random();
        int number = 1 + random.nextInt(2);
        txtGeneratedButton.setText("Score: " + gameDTO.getGame().getScore() + " " + "Generated number is " + number);
        updateGame(number);
    }

    private void updateGame(Integer number) {

        Configuration configuration = gameDTO.getConfiguration();
        Game newGame = new Game(gameDTO.getGame().getScore(),
                gameDTO.getGame().getIdConfiguration(), gameDTO.getGame().getIsFinished(),
                gameDTO.getGame().getStartDate(), gameDTO.getGame().getAlias(),  gameDTO.getGame().getPosition());
        newGame.setId(gameDTO.getGame().getId());

        int position = newGame.getPosition();
        int newPosition = position + number;
        if (newPosition > 5) {
            newGame.setScore(newGame.getScore() + 5);
        }
        newPosition %= 5;
        newGame.setPosition(newPosition);


        // daca nu e vizitat inca
        if (!gameDTO.getVisited().get(newPosition) && newPosition == 1) {
            newGame.setScore(newGame.getScore() - configuration.getConf1());
        }
        if (!gameDTO.getVisited().get(newPosition) && newPosition == 2) {
            newGame.setScore(newGame.getScore() - configuration.getConf2());
        }
        if (!gameDTO.getVisited().get(newPosition) && newPosition == 3) {
            newGame.setScore(newGame.getScore() - configuration.getConf3());
        }
        if (!gameDTO.getVisited().get(newPosition) && newPosition == 4) {
            newGame.setScore(newGame.getScore() - configuration.getConf4());
        }
        if (!gameDTO.getVisited().get(newPosition) && newPosition == 5) {
            newGame.setScore(newGame.getScore() - configuration.getConf5());
        }
        gameDTO.getVisited().set(newPosition, true);


        if (round == 3) {
            newGame.setIsFinished(true);
            isFinished = true;
        }

        gameDTO.setGame(newGame);
        server.updateGame(newGame);

        round++;
        txtTitle.setText("Round " + round);
        if (isFinished) {
            txtTitle.setText("Jocul s-a terminat!");
            btnLogout.setDisable(false);
            btnGenerate.setDisable(true);
        }
    }

    @Override
    public void updateResults(List<Game> games) {
        Platform.runLater(() -> {
            if (gameDTO.getGame().getIsFinished()) {
                observableList.removeAll();
                listView.getItems().clear();

                for (Game game : games) {
                    observableList.add("Alias: " + game.getAlias() + ", data incepere: " + game.getStartDate() +
                            " score: " + game.getScore() + " value: " + game.getScore());
                }
                listView.setItems(observableList);
                System.out.println(games);
            }
        });
    }
}
