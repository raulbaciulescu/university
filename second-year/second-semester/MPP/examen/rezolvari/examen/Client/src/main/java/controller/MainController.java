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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import services.Observer;
import services.Service;
import utils.Resources;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class MainController implements Observer {
    private final ObservableList<String> observableList = FXCollections.observableArrayList();
    public Text txtTitle;
    public GameDTO gameDTO;
    public Integer round = 1;
    public ListView<String> listView;
    public Button btnLogout;
    public Text txtErrors;
    public Text txtConfiguration;
    public Text txtGhicit;
    public Button btnGhicire;
    public TextField txtFieldWord;
    private boolean isFinished = false;
    private Service server;

    private boolean ghicit1 = false;
    private boolean ghicit2 = false;
    private boolean ghicit3 = false;


    public void setServer(Service server) {
        this.server = server;
    }

    public void initialize(GameDTO gameDTO) {
        txtTitle.setText("Round " + round);
        txtConfiguration.setText("Setul de litere este: " + gameDTO.getConfiguration().getLetters());
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

    @Override
    public void updateResults(List<Game> games) {
        Platform.runLater(() -> {
            if (gameDTO.getGame().getFinish()) {
                observableList.removeAll();
                listView.getItems().clear();
                games.sort(Comparator.comparing(Game::getScore).reversed());

                for (Game game : games) {
                    observableList.add("Alias: " + game.getAlias() + ", data incepere: " + game.getStartDate() +
                            " score: " + game.getScore());
                }
                listView.setItems(observableList);
                System.out.println(games);
            }
        });
    }

    public void onGhicireBtnClick(MouseEvent mouseEvent) {
        boolean ghicit = false;
        String word = txtFieldWord.getText();
        Configuration configuration = gameDTO.getConfiguration();
        Game newGame = new Game(gameDTO.getGame().getScore(),
                gameDTO.getGame().getIdConfiguration(), gameDTO.getGame().getFinish(),
                gameDTO.getGame().getStartDate(), gameDTO.getGame().getAlias());
        newGame.setId(gameDTO.getGame().getId());

        int score = 0;
        if (Objects.equals(word, configuration.getWord1()) && !ghicit1) {
            score += word.length();
            ghicit1 = true;
            ghicit = true;
            txtGhicit.setText("Cuvant ghicit, score = " + score);
        }
        if (Objects.equals(word, configuration.getWord2()) && !ghicit2) {
            score += word.length();
            ghicit2 = true;
            ghicit = true;
            txtGhicit.setText("Cuvant ghicit, score = " + score);
        }
        if (Objects.equals(word, configuration.getWord3()) && !ghicit3) {
            score += word.length();
            ghicit3 = true;
            txtGhicit.setText("Cuvant ghicit, score = " + score);
            ghicit = true;
        }
        int score1 = 0, score2 = 0, score3 = 0;
        for (int i = 0; i < configuration.getWord1().length(); i++) {
            if (i < word.length())
                if (configuration.getWord1().charAt(i) == word.charAt(i) && !ghicit1 && !ghicit)
                    score1++;
        }

        for (int i = 0; i < configuration.getWord2().length(); i++) {
            if (i < word.length())
                if (configuration.getWord2().charAt(i) == word.charAt(i) && !ghicit2 && !ghicit)
                    score2++;
        }

        for (int i = 0; i < configuration.getWord3().length(); i++) {
            if (i < word.length())
                if (configuration.getWord3().charAt(i) == word.charAt(i) && !ghicit3 && !ghicit)
                    score3++;
        }
        if (!ghicit && score1 >= score2 && score1 >= score3) {
            score = score1;
            txtGhicit.setText("Cuvant ghicit partial, score = " + score1);
        }

        if (!ghicit && score2 >= score1 && score2 >= score3) {
            score = score2;
            txtGhicit.setText("Cuvant ghicit partial, score = " + score2);
        }

        if (!ghicit && score3 >= score2 && score3 >= score1) {
            score = score3;
            txtGhicit.setText("Cuvant ghicit partial, score = " + score3);
        }

        if (score == 0) {
            txtGhicit.setText("Nu ai ghicit, Score " + score);
        }
        if (round == 3) {
            newGame.setFinish(true);
            isFinished = true;
        }

        round++;
        txtTitle.setText("Round " + round);
        newGame.setWord(word);
        newGame.setScore(newGame.getScore() + score);
        gameDTO.setGame(newGame);
        server.updateGame(newGame);

        if (isFinished) {
            txtTitle.setText("Jocul s-a terminat!" + " Cuvintele sunt: " +
                    gameDTO.getConfiguration().getWord1() + " " +
                    gameDTO.getConfiguration().getWord2() + " " +
                    gameDTO.getConfiguration().getWord3() +
                    " Scor final " + gameDTO.getGame().getScore());
            btnLogout.setDisable(false);
            btnGhicire.setDisable(true);
        }
    }
}
