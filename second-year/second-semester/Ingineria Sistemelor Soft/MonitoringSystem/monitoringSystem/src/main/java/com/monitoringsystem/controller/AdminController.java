package com.monitoringsystem.controller;

import com.monitoringsystem.model.User;
import com.monitoringsystem.utils.Constants;
import com.monitoringsystem.utils.Resources;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import com.monitoringsystem.service.api.UserService;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AdminController implements Initializable {
    @FXML TableView<User> tableView;
    @FXML TableColumn<User, Long> tableColumnId;
    @FXML TableColumn<User, String> tableColumnUsername;
    @FXML TableColumn<User, String> tableColumnPassword;
    @FXML TableColumn<User, String> tableColumnFirstName;
    @FXML TableColumn<User, String> tableColumnLastName;
    @FXML TableColumn<User, Boolean> tableColumnIsBoss;
    @FXML Button btnAdd;
    @FXML Button btnDelete;
    private final ObservableList<User> observableList = FXCollections.observableArrayList();
    private final UserService userService;
    private final User admin;

    public AdminController() throws SQLException {
        userService = Resources.getInstance().getUserService();
        admin = Resources.getInstance().getLastLoggedUser();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColumnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tableColumnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableColumnIsBoss.setCellValueFactory(new PropertyValueFactory<>("role"));
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnId.setVisible(false);
        List<User> users = userService.getAll();

        Predicate<User> byRole = user -> user.getRole() != 2;
        observableList.addAll(users.stream().filter(byRole).collect(Collectors.toList()));
        tableView.setItems(observableList);
    }

    public void onDeleteBtnClick(MouseEvent mouseEvent) {
        Long id = tableView.getSelectionModel().getSelectedItems().get(0).getId();
        userService.delete(id);
        tableView.getItems().remove(tableView.getSelectionModel().getSelectedItems().get(0));
    }

    public void onAddBtnClick(MouseEvent mouseEvent) {
        NavigationController.navigateTo(admin.getId(), Constants.Scene.ADD_USER);
    }
}
