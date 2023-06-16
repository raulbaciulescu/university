package com.example.socialapp.controller;

import com.example.socialapp.utils.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MainFeedController {
    @FXML Button btnSearch;
    @FXML Button btnRequests;


    public void onSearchClicked(MouseEvent mouseEvent) throws IOException {
        NavController.navigate(Constants.Scene.SEARCH, mouseEvent);
    }

    public void onRequestsClicked(MouseEvent mouseEvent) throws IOException {
        NavController.navigate(Constants.Scene.REQUESTS, mouseEvent);
    }
}
