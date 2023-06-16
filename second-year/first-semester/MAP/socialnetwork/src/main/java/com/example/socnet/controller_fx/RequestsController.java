package com.example.socnet.controller_fx;

import com.example.socnet.domain.model.FriendshipRequest;
import com.example.socnet.domain.model.User;
import com.example.socnet.domain.util.Constants;
import com.example.socnet.domain.util.Observer;
import com.example.socnet.resources.Resources;
import com.example.socnet.service.CurrentUser;
import com.example.socnet.service.FriendshipService;
import com.example.socnet.service.RequestService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RequestsController implements Initializable, Observer {

    @FXML public ListView<FriendshipRequest> lvFriendRequests;
    @FXML public Button btnGoBack;
    @FXML public Button btnAccept;

    private final ObservableList<FriendshipRequest> friendshipRequests;
    private final RequestService requestService;

    public RequestsController() throws SQLException {
        this.friendshipRequests = FXCollections.observableArrayList();
        this.requestService = Resources.getInstance().getRequestService();
    }

    @FXML
    public void goBack(ActionEvent event) {
        NavController.navigate(Constants.UI.Scene.MAIN_FEED, event);
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
        final long userID = CurrentUser.getInstance().getUser().getId();
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
                    Resources.getInstance()
                            .getFriendshipSuperService()
                            .friendShipService();
            final User user = Resources.getInstance()
                    .getUserService()
                    .findByID(friendshipRequest.getSenderID())
                    .get();
            friendshipService.add(
                    CurrentUser.getInstance().getUser(), user);
            requestService.update(
                    friendshipRequest.getId(), FriendshipRequest.Status.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        final long userID = CurrentUser.getInstance().getUser().getId();
        this.friendshipRequests.clear();
        try {
            this.friendshipRequests.addAll(requestService.getAllFor(userID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
