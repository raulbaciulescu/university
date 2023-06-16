package exam.client.gui;

import exam.client.RestClient;
import exam.model.Configuration;
import exam.model.Game;
import exam.model.dto.GameDTO;
import exam.services.IExamObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MainController implements Initializable, IExamObserver {

    @FXML
    public Button logoutButton;
    @FXML
    public Label valuesLabel;
    public Label generatedValueLabel;
    public ListView listViewLeaderBoard;
    public Button generateStepButton;
    @FXML
    public GridPane gridPane;
    private RestClient server;
    private GameDTO gameStarted;
    private int turns;
    private int finished;
    Parent mainSwimmingParent;

    private ObservableList<String> observableLeaderBoard = FXCollections.observableArrayList();

    public MainController() {
        System.out.println("Constructor MainController");
    }


    public void setServer(RestClient server) {
        this.server = server;

    }
    public void setGame(GameDTO game){
        this.gameStarted=game;
        turns=0;
        finished=0;
        valuesLabel.setText(gameStarted.getConf().toString());
    }

    public void setParent(Parent p){
        this.mainSwimmingParent=p;
    }



    public void closeMainWindow() {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }


    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listViewLeaderBoard.setItems(observableLeaderBoard);

        gridPane.setGridLinesVisible(true);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Label label=new Label("foo");
                label.setMinWidth(100);
                gridPane.add(label,i,j);
            }
        }
        var children=gridPane.getChildren();
        System.out.println(children.size());
        for(var child :children){
            Integer row=gridPane.getRowIndex(child);
            Integer column=gridPane.getColumnIndex(child);
            //System.out.println(row+column);
            child.setOnMouseClicked(event-> handleClick(row,column));
        }

    }
    private void handleClick(int row,int column){
        generatedValueLabel.setText(row+" "+column);
        if(turns==3 || finished==1)
            return;
        turns++;
        Configuration conf=gameStarted.getConf();
        if(conf.getX()==row+1 && conf.getY()==column+1){
            //ai castigat
            finished=1;
            Game game=gameStarted.getGame();
            game.setPoints(game.getPoints()+conf.getValue());
            game.setValue(conf.getValue());
            game.setFinished(1);
            server.updateGame(game);
            showLabel(row,column,conf.getValue());
            observableLeaderBoard.setAll(Arrays.stream(server.getLeaderBoard()).toList().stream().map(gameDTO -> gameDTO.toString()).toList());
        }
        else
        {
            double dist=Math.pow(row+1-conf.getX(),2)+Math.pow(column+1-conf.getY(),2);
            dist=Math.sqrt(dist);
            //afiseaza distanta pe label
            Game game=gameStarted.getGame();
            game.setPoints((int) (game.getPoints()+dist));
            gameStarted.setGame(server.updateGame(game));
            showLabel(row,column,dist);
        }
        if(turns==3){
            //gata jocu, afiseaza leaderboard
            finished=1;
            observableLeaderBoard.setAll(Arrays.stream(server.getLeaderBoard()).toList().stream().map(gameDTO -> gameDTO.toString()).toList());

        }

    }
    private void showLabel(int row, int column,double value){
        var children=gridPane.getChildren();
        for(Node child :children){
            Integer rowG=gridPane.getRowIndex(child);
            Integer columnG=gridPane.getColumnIndex(child);
            //System.out.println(row+column);
            if(rowG!=null&&columnG!=null&&rowG==row && columnG==column) {
                Label label=(Label) child;
                label.setText(String.valueOf(value));
            }
        }
    }
    void logout() {
        /*try {
            server.logout(gameStarted.getAlias(), this);
        } catch (ExamException e) {
            System.out.println("Logout error " + e);
        }
*/
    }

    public boolean shouldExit=true;
    public void handleLogOut(ActionEvent actionEvent) throws IOException {
        logout();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();

    }
/*
    public void generateRandom(ActionEvent actionEvent) throws ExamException {
        int number=(int)Math.floor(Math.random()*(3-1+1)+1);
        generatedValueLabel.setText(String.valueOf(number));
        turns++;
        int finished=0;
        if(turns==3)
        {finished=1;
            generateStepButton.setDisable(true);
        }
        try {
            gameStarted=server.makeMove(gameStarted,number,finished);
            updateLabels();
        } catch (ExamException e) {
            e.printStackTrace();
        }
        if(finished==1){
            observableLeaderBoard.setAll(server.getLeaderBoard().stream().map(gameDTO -> gameDTO.toString()).toList());
        }

    }
    private void updateLabels(){
        configurationLabel.setText(gameStarted.getState());
    }

     */
}
