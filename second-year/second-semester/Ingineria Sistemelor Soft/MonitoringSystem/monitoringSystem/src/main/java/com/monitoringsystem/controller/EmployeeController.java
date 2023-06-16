package com.monitoringsystem.controller;

import com.monitoringsystem.model.Status;
import com.monitoringsystem.model.Task;
import com.monitoringsystem.model.User;
import com.monitoringsystem.service.api.TaskService;
import com.monitoringsystem.service.api.UserService;
import com.monitoringsystem.utils.Constants;
import com.monitoringsystem.utils.Observer;
import com.monitoringsystem.utils.Resources;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable, Observer {
    @FXML Button btnFinishTask;
    @FXML Button btnLogout;
    @FXML public Text errors;
    @FXML TableView<Task> tableView;
    @FXML TableColumn<Task, Long> tableColumnId;
    @FXML TableColumn<Task, String> tableColumnDescription;
    @FXML TableColumn<Task, Status> tableColumnStatus;
    private TaskService taskService;
    private UserService userService;
    private final ObservableList<Task> observableList = FXCollections.observableArrayList();
    private User employee;

    @Override
    public void update() {
        try {
            refreshTasks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            employee = Resources.getInstance().getLastLoggedUser();
            tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tableColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

            taskService = Resources.getInstance().getTaskService();
            userService = Resources.getInstance().getUserService();
            taskService.addObserver(this);
            refreshTasks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void refreshTasks() throws SQLException {
        tableView.getItems().clear();
        observableList.removeAll();
        observableList.addAll(taskService.getSpecificTasks(employee.getId()));
        tableView.setItems(observableList);
    }

    public void onBtnLogoutClick(MouseEvent mouseEvent) throws SQLException {
        Resources.getInstance().deleteUser(new LoggedUser(employee, LocalTime.now()));
        userService.logout();
        NavigationController.navigateTo(employee.getId(), Constants.Scene.LOGIN);
    }

    public void onBtnFinishTaskClick(MouseEvent mouseEvent) throws SQLException {
        Task selectedTask = tableView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            errors.setVisible(false);
            taskService.finishTask(selectedTask.getId());
            refreshTasks();
        }
        else {
            errors.setVisible(true);
        }
    }
}
