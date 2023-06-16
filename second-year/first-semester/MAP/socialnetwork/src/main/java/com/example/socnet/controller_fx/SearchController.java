package com.example.socnet.controller_fx;

import com.example.socnet.domain.model.User;
import com.example.socnet.domain.util.Constants;
import com.example.socnet.resources.Resources;
import com.example.socnet.service.UserService;
import com.example.socnet.super_service.FriendshipSuperService;
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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchController {
    final UserService userService;
    final FriendshipSuperService friendshipSuperService;
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
        this.userService = Resources.getInstance().getUserService();
        this.friendshipSuperService = Resources.getInstance().getFriendshipSuperService();
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
    public void goBack(ActionEvent event) {
        NavController.navigate(Constants.UI.Scene.MAIN_FEED, event);
    }
}
