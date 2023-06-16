package com.example.marire.controller;

import com.example.marire.domain.Persoana;
import com.example.marire.service.PersoanaService;
import com.example.marire.utils.Constants;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController {

    PersoanaService service;
    @FXML
    public ListView<Persoana> listViewPersoana;


//    public void initialize(URL location, ResourceBundle resources) {
//        this.listViewPersoana.setItems(this.messages);
//        try {
//            this.messages.addAll(this.service.getAll());
//            this.messageService.addObserver(this);
//            this.setListeners();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    private void setListeners() {

        listViewPersoana.setOnMouseClicked(event ->
                this.login(listViewPersoana
                        .getSelectionModel()
                        .getSelectedItem()
                        .getUsername(), (MouseEvent) event)
        );
    }
    private void login(String username, MouseEvent event) {

        NavController.navigate(Constants.UI.Scene.MAIN_FEED, (Event) event);
    }
}
