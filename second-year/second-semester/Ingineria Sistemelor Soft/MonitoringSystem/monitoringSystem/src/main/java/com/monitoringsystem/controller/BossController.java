package com.monitoringsystem.controller;

import com.monitoringsystem.model.User;
import com.monitoringsystem.service.api.TaskService;
import com.monitoringsystem.service.api.UserService;
import com.monitoringsystem.utils.Constants;
import com.monitoringsystem.utils.Observer;
import com.monitoringsystem.utils.Resources;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BossController implements Initializable, Observer {
    @FXML TableView<LoggedUser> tableView;
    @FXML TableColumn<LoggedUser, Long> tableColumnId;
    @FXML TableColumn<LoggedUser, String> tableColumnUsername;
    @FXML TableColumn<LoggedUser, LocalDate> tableColumnDate;
    @FXML Button btnLogout;
    @FXML Button btnAddTask;
    @FXML Button btnTasks;
    @FXML TextField txtDescription;
    @FXML public Text errors;
    private TaskService taskService;
    private UserService userService;
    private User boss;
    private final ObservableList<LoggedUser> observableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            boss = Resources.getInstance().getBoss();
            tableColumnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
            tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("loginDateString"));

            userService = Resources.getInstance().getUserService();
            taskService = Resources.getInstance().getTaskService();
            userService.addObserver(this);
            refreshUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void refreshUsers() throws SQLException {
        tableView.getItems().clear();
        observableList.removeAll();
        observableList.addAll(Resources.getInstance().getLoggedUsers());
        tableView.setItems(observableList);
    }


    @Override
    public void update() {
        try {
            refreshUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onBtnAddTaskClick(MouseEvent mouseEvent) {
        String description = txtDescription.getText();
        LoggedUser loggedUser = tableView.getSelectionModel().getSelectedItem();
        if (loggedUser != null) {
            errors.setVisible(false);
            taskService.add(description, loggedUser.getId());
        }
        else {
            errors.setVisible(true);
        }
    }

    public void onBtnLogoutClick(MouseEvent mouseEvent) {
        NavigationController.navigateTo(boss.getId(), Constants.Scene.LOGIN);
    }

    public void onBtnTasksClick(MouseEvent mouseEvent) throws SQLException {
        LoggedUser loggedUser = tableView.getSelectionModel().getSelectedItem();
        if (loggedUser != null) {
            Resources.getInstance().setLastLoggedUser(loggedUser);
            Resources.getInstance().setBoss(boss);
            NavigationController.navigateTo(boss.getId(), Constants.Scene.BOSS_TASKS);
        }

    }
}
