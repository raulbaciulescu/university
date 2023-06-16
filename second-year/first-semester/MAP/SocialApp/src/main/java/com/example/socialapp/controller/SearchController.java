package com.example.socialapp.controller;

import com.example.socialapp.domain.User;
import com.example.socialapp.service.ButtonService;
import com.example.socialapp.service.FriendshipService;
import com.example.socialapp.service.UserService;
import com.example.socialapp.utils.Constants;
import com.example.socialapp.utils.Resources;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchController {
    final UserService userService;
    final FriendshipService friendshipSuperService;
    final ButtonService buttonService;
    ObservableList<UserWithButton> observableList = FXCollections.observableArrayList();

    @FXML public Button btnGoBack;
    @FXML public TextField txtSearch;
    @FXML public Button btnSearch;

    @FXML TableColumn<UserWithButton, String> tableColumnFirstName;
    @FXML TableColumn<UserWithButton, String> tableColumnLastName;
    @FXML TableColumn<UserWithButton, Button> tableColumnAction;
    @FXML TableView<UserWithButton> tableView;


    public SearchController() throws SQLException {
        this.userService = Resources.userService;
        this.friendshipSuperService = Resources.getInstance().friendshipService;
        this.buttonService = new ButtonService();
    }

    private List<User> getUsers() throws SQLException {
        return userService.getAll();
    }

    @FXML
    void initialize() throws SQLException {
        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableColumnAction.setCellValueFactory(new PropertyValueFactory<>("button"));
        for (User user: getUsers()) {
            observableList.add(new UserWithButton(user.getId(), user.getFirstName(), user.getLastName(), buttonService.getButton(user)));
        }
        tableView.setItems(observableList);
        txtSearch.textProperty().addListener(o -> {
            try {
                handleFilter();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void handleFilter() throws SQLException {
        Predicate<User> p1 = n -> n.getFirstName().contains(txtSearch.getText());
        Predicate<User> p2 = n -> n.getLastName().contains(txtSearch.getText());
        List<User> filteredUsers = getUsers()
                .stream()
                .filter(p1.or(p2))
                .toList();

        List<UserWithButton> list = new ArrayList<>();
        for (User user: filteredUsers)
            list.add(new UserWithButton(user.getId(), user.getFirstName(), user.getLastName(), buttonService.getButton(user)));
        observableList.setAll(list);
    }


    public void onSearchBtnClick(MouseEvent mouseEvent) {

    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        NavController.navigate(Constants.Scene.MAIN_FEED, event);
    }
}

