package com.example.socialapp.controller;

import com.example.socialapp.domain.FriendshipRequest;
import com.example.socialapp.domain.User;
import com.example.socialapp.service.FriendshipRequestService;
import com.example.socialapp.service.FriendshipService;
import com.example.socialapp.utils.Constants;
import com.example.socialapp.utils.CurrentUser;
import com.example.socialapp.utils.Resources;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FriendshipRequestController implements Initializable {

    @FXML
    public ListView<FriendshipRequest> lvFriendRequests;
    @FXML public Button btnGoBack;
    @FXML public Button btnAccept;

    private final ObservableList<FriendshipRequest> friendshipRequests;
    private final FriendshipRequestService requestService;


    public FriendshipRequestController() throws SQLException {
        this.friendshipRequests = FXCollections.observableArrayList();
        this.requestService = Resources.getInstance().requestService;
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        NavController.navigate(Constants.Scene.MAIN_FEED, event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.lvFriendRequests.setItems(this.friendshipRequests);
        try {
            this.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void init() throws SQLException {
        final long userID = CurrentUser.getInstance().getUser().get().getId();
        friendshipRequests.addAll(requestService.getAllFor(userID));
        lvFriendRequests.setOnMouseClicked(event -> {
            btnAccept.setVisible(true);
        });
    }

    @FXML
    public void acceptRequest() {
        final FriendshipRequest friendshipRequest =
                lvFriendRequests.getSelectionModel().getSelectedItem();
        try {
            final FriendshipService friendshipService =
                    Resources.getInstance().friendshipService;
            final User user = Resources.getInstance().userService
                    .findByID(friendshipRequest.getSenderID())
                    .get();
            friendshipService.add(
                    CurrentUser.getInstance().getUser().get(), user);
            requestService.update(
                    friendshipRequest.getId(), FriendshipRequest.Status.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        final long userID = CurrentUser.getInstance().getUser().get().getId();
        this.friendshipRequests.clear();
        try {
            this.friendshipRequests.addAll(requestService.getAllFor(userID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
