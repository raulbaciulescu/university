package com.example.socnet.controller_fx;

import com.example.socnet.domain.model.User;
import com.example.socnet.domain.util.Constants;
import com.example.socnet.domain.util.DateUtil;
import com.example.socnet.resources.Resources;
import com.example.socnet.service.PDFService;
import com.example.socnet.super_service.FriendshipSuperService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {

    @FXML public CheckBox checkBoxIncludeRequest;
    @FXML public ListView<User> listViewFriendsR;
    @FXML public Button btnGenerate;
    @FXML public TextField tfFromDay;
    @FXML public TextField tfToDay;
    @FXML public ComboBox<String> cBoxFromMonth;
    @FXML public ComboBox<String> cBoxToMonth;
    @FXML public Button btnGoBack;

    private final ObservableList<User> friends;

    public ReportsController() {
        this.friends = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(final URL location,
                           final ResourceBundle resources) {
        final ObservableList<String> months =
                FXCollections.observableArrayList(Constants.MONTHS);
        this.cBoxFromMonth.setItems(months);
        this.cBoxToMonth.setItems(months);

        try {
            final FriendshipSuperService friendshipSuperService =
                    Resources.getInstance().getFriendshipSuperService();
            this.friends.addAll(friendshipSuperService.getMyFriends());
            this.listViewFriendsR.setItems(this.friends);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void generateReport() {
        try {
            final int fromDay = Integer.parseInt(tfFromDay.getText());
            final int toDay = Integer.parseInt(tfToDay.getText());
            final String fromMonth =
                    cBoxFromMonth.getSelectionModel().getSelectedItem();
            final String toMonth =
                    cBoxToMonth.getSelectionModel().getSelectedItem();
            final boolean includeFriendRequest =
                    checkBoxIncludeRequest.isSelected();
            final User user = this.listViewFriendsR
                    .getSelectionModel().getSelectedItem();
            final long start = DateUtil.fromDayAndMonth(fromDay, fromMonth);
            final long end = DateUtil.fromDayAndMonth(toDay, toMonth);

            if (includeFriendRequest) {
                PDFService.generateFriendsAndMessagesReport(start, end);
            } else {
                PDFService.generateMessagesReport(user.getId(), start, end);
            }
        } catch (final @NotNull Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goBack(ActionEvent event) {
        NavController.navigate(Constants.UI.Scene.MAIN_FEED, event);
    }
}
