package com.example.socnet.controller_fx;

import com.example.socnet.domain.model.Message;
import com.example.socnet.domain.util.Constants;
import com.example.socnet.domain.util.Observer;
import com.example.socnet.resources.Resources;
import com.example.socnet.service.CurrentUser;
import com.example.socnet.service.MessageService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MessageController implements Initializable, Observer {

    @FXML public ListView<Message> listViewMessages;
    @FXML public TextField textFieldMessage;
    @FXML public Button btnSendMessage;
    @FXML public Button btnGoBack;
    @FXML public Button btnReadMore;

    private final ObservableList<Message> messages;

    private final MessageService messageService;

    private Integer replyOn;

    public MessageController() throws SQLException {
        this.messages = FXCollections.observableArrayList();
        this.messageService = Resources.getInstance()
                .getChatSuperService()
                .messageService();
        this.replyOn = null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.listViewMessages.setItems(this.messages);
        try {
            this.messages.addAll(this.messageService.getAll(false));
            this.messageService.addObserver(this);
            this.setListeners();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setListeners() {
        listViewMessages.setOnMouseClicked(event ->
                    this.replyOn = listViewMessages
                            .getSelectionModel()
                            .getSelectedItem()
                            .getId()
        );
    }

    @Override
    public void update() {
        try {
            this.messages.clear();
            this.messages.addAll(this.messageService.getAll(false));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onSendMessage() {
        final String messageBody = textFieldMessage.getText();
        if (!messageBody.isBlank()) {
            try {
                messageService.add(
                        CurrentUser.getInstance().getUser().getId(),
                        CurrentUser.getInstance().getSelectedChatId(),
                        messageBody,
                        replyOn
                );
            } catch (final Exception e) {
                e.printStackTrace();
            } finally {
                this.clear();
            }
        }
    }

    private void clear() {
        textFieldMessage.clear();
        replyOn = null;
    }

    @FXML
    public void goBack(ActionEvent event) {
        NavController.navigate(Constants.UI.Scene.MAIN_FEED, event);
    }

    @FXML
    public void readMore() {
        try {
            this.messages.clear();
            this.messages.addAll(this.messageService.getAll(true));
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
