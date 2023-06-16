package com.example.socnet.controller_fx;

import com.example.socnet.domain.model.Chat;
import com.example.socnet.domain.util.Constants;
import com.example.socnet.resources.Resources;
import com.example.socnet.service.ChatService;
import com.example.socnet.service.CurrentUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainFeedController implements Initializable {

    @FXML public Button btnReports;
    @FXML public Button btnShowFriendRequests;
    @FXML public Button btnSearch;
    @FXML public Button btnEvents;
    @FXML public ListView<Chat> listViewChats;

    private final ObservableList<Chat> chats;
    private final ChatService chatService;

    public MainFeedController() throws SQLException {
        this.chats = FXCollections.observableArrayList();
        this.chatService = Resources.getInstance()
                .getChatSuperService()
                .chatService();
    }

    @FXML
    public void showFriendRequests(ActionEvent event) {
        NavController.navigate(Constants.UI.Scene.REQUESTS, event);
    }

    @FXML
    public void searchUsers(ActionEvent event) {
        NavController.navigate(Constants.UI.Scene.SEARCH, event);
    }


    @Override
    public void initialize(final URL location,
                           final ResourceBundle resources) {
        try {
            this.listViewChats.setItems(this.chats);
            this.chats.addAll(chatService.getAll());
            this.setListeners();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setListeners() {
        this.listViewChats.setOnMouseClicked(event -> {
            final Chat chat = listViewChats
                    .getSelectionModel()
                    .getSelectedItem();
            if (chat != null) {
                navigateToMessages(chat.getId(), event);
            }
        });
    }

    private void navigateToMessages(final int chatId,
                                    final MouseEvent event) {
        CurrentUser.getInstance()
                .setSelectedChatId(chatId);
        NavController.navigate(Constants.UI.Scene.MESSAGES, event);
    }

    @FXML
    public void navigateToReports(final @NotNull ActionEvent event) {
        NavController.navigate(Constants.UI.Scene.REPORTS, event);
    }

    @FXML
    public void navigateToEvents(ActionEvent event) {
        NavController.navigate(Constants.UI.Scene.EVENTS, event);
    }
}
